#include <chrono>
#include <functional>
#include <iostream>
#include <thread>
#include <atomic>
#include <cassert>

// Synchronization (handled by memory ordering, cond variable etc) is different from data race (handled by atomics)

struct thread_info
{
    void writer_thread()
    {
        // single-threaded optimization called reordering possible here scenario #2.
        data = 30;
        has_data.store(true);
    }
    void reader_thread()
    {
        while(!has_data.load());
        {
            int x = data;
            std::cout << "Cached data: " << x << std::endl;
            // std::this_thread::sleep_for(std::chrono::seconds(1));
            if(data == 5)
            {
                std::cout << "assert failed\n" ;
            }
        }
    }
    std::thread rd;
    std::thread wr;
    int data = 5;
    std::atomic<bool> has_data{false};
    thread_info()
        : rd{&thread_info::reader_thread,
              this},
          wr{&thread_info::writer_thread,
              this}
    {}
    ~thread_info() {
        rd.join();
        wr.join();
        std::cout << "final data: " << data
            << std::endl;
    }
};

int main(int argc, const char *argv[]) {
  std::cout << "In main: " << std::hex << std::this_thread::get_id() << std::dec << "\n";

  thread_info threads;

  return 0;
}

// Different runs different behavior

// Using host libthread_db library "/lib64/libthread_db.so.1".
// In main: 7ffff7fe8740
// [New Thread 0x7ffff6eb9700 (LWP 11890)]
// [New Thread 0x7ffff66b8700 (LWP 11891)]
// Cached data: 30
// assert failed
// final data: 5
// [Thread 0x7ffff66b8700 (LWP 11891) exited]
// [Thread 0x7ffff6eb9700 (LWP 11890) exited]
// [Inferior 1 (process 11879) exited normally]
// Missing separate debuginfos, use: yum debuginfo-install glibc-2.28-211.el8.x86_64 libgcc-8.5.0-15.el8.x86_64 libstdc++-8.5.0-15.el8.x86_64
// (gdb) r
// Starting program: /home/kriti.malik@ds1.deepranalytics.com/repos/ec2/a.out 
// [Thread debugging using libthread_db enabled]
// Using host libthread_db library "/lib64/libthread_db.so.1".
// In main: 7ffff7fe8740
// [New Thread 0x7ffff6eb9700 (LWP 11925)]
// [New Thread 0x7ffff66b8700 (LWP 11926)]
// Cached data: 30
// final data: 5
// [Thread 0x7ffff66b8700 (LWP 11926) exited]
// [Thread 0x7ffff6eb9700 (LWP 11925) exited]
// [Inferior 1 (process 11924) exited normally]
// (gdb) r
// Starting program: /home/kriti.malik@ds1.deepranalytics.com/repos/ec2/a.out 
// [Thread debugging using libthread_db enabled]
// Using host libthread_db library "/lib64/libthread_db.so.1".
// In main: 7ffff7fe8740
// [New Thread 0x7ffff6eb9700 (LWP 11956)]
// [New Thread 0x7ffff66b8700 (LWP 11957)]
// [Thread 0x7ffff66b8700 (LWP 11957) exited]


// //////// Into Infinite loop : has_data gotten true in cache of writer thread #scenario 1.

// Below is backtrace of this infinite loop in writer thread.

// (gdb) thread apply
// Please specify a thread ID list
// (gdb) info threads
//   Id   Target Id                                 Frame 
// * 1    Thread 0x7ffff7fe8740 (LWP 11950) "a.out" 0x00007ffff7bb86cd in __pthread_timedjoin_ex () from /lib64/libpthread.so.0
//   2    Thread 0x7ffff6eb9700 (LWP 11956) "a.out" std::operator& (__m=std::memory_order_seq_cst, __mod=std::__memory_order_mask)
//     at /usr/include/c++/8/bits/atomic_base.h:82
// (gdb) thread 2
// [Switching to thread 2 (Thread 0x7ffff6eb9700 (LWP 11956))]
// #0  std::operator& (__m=std::memory_order_seq_cst, __mod=std::__memory_order_mask) at /usr/include/c++/8/bits/atomic_base.h:82
// 82          return memory_order(__m & int(__mod));
// (gdb) bt
// #0  std::operator& (__m=std::memory_order_seq_cst, __mod=std::__memory_order_mask) at /usr/include/c++/8/bits/atomic_base.h:82
// #1  0x00000000004012a6 in std::__atomic_base<bool>::load (__m=std::memory_order_seq_cst, this=0x7fffffffdb74) at /usr/include/c++/8/bits/atomic_base.h:392
// #2  std::atomic<bool>::load (this=0x7fffffffdb74, __m=std::memory_order_seq_cst) at /usr/include/c++/8/atomic:111
// #3  0x000000000040130d in thread_info::reader_thread (this=0x7fffffffdb60) at simple_thread18_memorder5.cpp:20
// #4  0x0000000000401819 in std::__invoke_impl<void, void (thread_info::*)(), thread_info*> (
//     __f=@0x6172d0: (void (thread_info::*)(thread_info * const)) 0x4012ec <thread_info::reader_thread()>, __t=@0x6172c8: 0x7fffffffdb60)
//     at /usr/include/c++/8/bits/invoke.h:73
// #5  0x00000000004014ca in std::__invoke<void (thread_info::*)(), thread_info*> (
//     __fn=@0x6172d0: (void (thread_info::*)(thread_info * const)) 0x4012ec <thread_info::reader_thread()>) at /usr/include/c++/8/bits/invoke.h:95
// #6  0x0000000000401d53 in std::thread::_Invoker<std::tuple<void (thread_info::*)(), thread_info*> >::_M_invoke<0ul, 1ul> (this=0x6172c8)
//     at /usr/include/c++/8/thread:244
// #7  0x0000000000401d0e in std::thread::_Invoker<std::tuple<void (thread_info::*)(), thread_info*> >::operator() (this=0x6172c8)
//     at /usr/include/c++/8/thread:253
// #8  0x0000000000401cf2 in std::thread::_State_impl<std::thread::_Invoker<std::tuple<void (thread_info::*)(), thread_info*> > >::_M_run (this=0x6172c0)
// --Type <RET> for more, q to quit, c to continue without paging--
//    include/c++/8/thread:196
// #9  0x00007ffff78dcba3 in execute_native_thread_routine () from /lib64/libstdc++.so.6
// #10 0x00007ffff7bb71ca in start_thread () from /lib64/libpthread.so.0
// #11 0x00007ffff6ef3e73 in clone () from /lib64/libc.so.6
// (gdb) p has_data
// No symbol "has_data" in current context.
// (gdb) frame 3
// #3  0x000000000040130d in thread_info::reader_thread (this=0x7fffffffdb60) at simple_thread18_memorder5.cpp:20
// warning: Source file is more recent than executable.
// 20              while(!has_data.load());
// (gdb) p has_data
// $1 = {_M_base = {static _S_alignment = 1, _M_i = false}}
// (gdb) 