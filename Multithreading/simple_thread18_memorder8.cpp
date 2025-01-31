#include <chrono>
#include <functional>
#include <iostream>
#include <thread>
#include <atomic>
#include <cassert>

// Synchronization (handled by memory ordering, cond variable etc) is different from data race (handled by atomics)

// memory_order_release - memory_order_acquire:  guarantee order across threads but data variable caching issue persists, so make data relaxed
// memory_order_seq_cst guarantees order in own thread Plus data variable caching issue persists, so make data relaxed
// Despite making data variable relaxed, memory_order_seq_cst would get reader yet into infinite loop for us.


struct thread_info
{
    void writer_thread()
    {
        // single-threaded optimization called reordering possible here scenario #2.
        data = 30; // stayed in cache
        data.store(30, std::memory_order_relaxed);
        has_data.store(true, std::memory_order_seq_cst); // release memory lock here, data assignment will be happen before.
    }
    void reader_thread()
    {
        while(!has_data.load(std::memory_order_seq_cst))
        {
            std::this_thread::yield();
        }
        {
            int x = data.load(std::memory_order_relaxed);
            std::cout << "Cached data: " << x << std::endl;
            // std::this_thread::sleep_for(std::chrono::seconds(1));
            if(x == 5)
            {
                std::cout << "assert failed\n" ;
            }
        }
    }
    std::thread rd;
    std::thread wr;
    std::atomic<int> data{5};
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
        // if(has_data.load(std::memory_order_acquire))
        {
            std::cout << "final data: " << data.load(std::memory_order_relaxed)
                << std::endl;
        }
    }
};

int main(int argc, const char *argv[]) {
  std::cout << "In main: " << std::hex << std::this_thread::get_id() << std::dec << "\n";

  thread_info threads;

  return 0;
}


// INFINITE_LOOP

// (gdb) r
// Starting program: /home/kriti.malik@ds1.deepranalytics.com/repos/ec2/a.out 
// [Thread debugging using libthread_db enabled]
// Using host libthread_db library "/lib64/libthread_db.so.1".
// In main: 7ffff7fe8740
// [New Thread 0x7ffff6eb9700 (LWP 15395)]
// [New Thread 0x7ffff66b8700 (LWP 15396)]
// [Thread 0x7ffff66b8700 (LWP 15396) exited]







// ^C
// Thread 1 "a.out" received signal SIGINT, Interrupt.
// 0x00007ffff7bb86cd in __pthread_timedjoin_ex () from /lib64/libpthread.so.0
// Missing separate debuginfos, use: yum debuginfo-install glibc-2.28-211.el8.x86_64 libgcc-8.5.0-15.el8.x86_64 libstdc++-8.5.0-15.el8.x86_64
// (gdb) bt
// #0  0x00007ffff7bb86cd in __pthread_timedjoin_ex () from /lib64/libpthread.so.0
// #1  0x00007ffff78dce27 in std::thread::join() () from /lib64/libstdc++.so.6
// #2  0x0000000000401434 in thread_info::~thread_info (this=0x7fffffffdb60, __in_chrg=<optimized out>) at simple_thread18_memorder7.cpp:42
// #3  0x0000000000400f2c in main (argc=1, argv=0x7fffffffdc68) at simple_thread18_memorder7.cpp:52
// (gdb) info threads
//   Id   Target Id                                 Frame 
// * 1    Thread 0x7ffff7fe8740 (LWP 15387) "a.out" 0x00007ffff7bb86cd in __pthread_timedjoin_ex () from /lib64/libpthread.so.0
//   2    Thread 0x7ffff6eb9700 (LWP 15395) "a.out" std::operator& (__m=std::memory_order_acquire, __mod=std::__memory_order_mask) at /usr/include/c++/8/bits/atomic_base.h:82
// (gdb) thread 2
// [Switching to thread 2 (Thread 0x7ffff6eb9700 (LWP 15395))]
// #0  std::operator& (__m=std::memory_order_acquire, __mod=std::__memory_order_mask) at /usr/include/c++/8/bits/atomic_base.h:82
// 82          return memory_order(__m & int(__mod));
// (gdb) bt
// #0  std::operator& (__m=std::memory_order_acquire, __mod=std::__memory_order_mask) at /usr/include/c++/8/bits/atomic_base.h:82
// #1  0x00000000004012a6 in std::__atomic_base<bool>::load (__m=std::memory_order_acquire, this=0x7fffffffdb74) at /usr/include/c++/8/bits/atomic_base.h:392
// #2  std::atomic<bool>::load (this=0x7fffffffdb74, __m=std::memory_order_acquire) at /usr/include/c++/8/atomic:111
// #3  0x000000000040130d in thread_info::reader_thread (this=0x7fffffffdb60) at simple_thread18_memorder7.cpp:20
// #4  0x0000000000401819 in std::__invoke_impl<void, void (thread_info::*)(), thread_info*> (
//     __f=@0x6172d0: (void (thread_info::*)(thread_info * const)) 0x4012ec <thread_info::reader_thread()>, __t=@0x6172c8: 0x7fffffffdb60) at /usr/include/c++/8/bits/invoke.h:73
// --Type <RET> for more, q to quit, c to continue without paging--


// data.store(30);
// also caused infinite loop

// Working when I put data.store(30, std::memory_order_relaxed); <-> volatile
// (gdb) r
// Starting program: /home/kriti.malik@ds1.deepranalytics.com/repos/ec2/a.out 
// [Thread debugging using libthread_db enabled]
// Using host libthread_db library "/lib64/libthread_db.so.1".
// In main: 7ffff7fe8740
// [New Thread 0x7ffff6eb9700 (LWP 18090)]
// [New Thread 0x7ffff66b8700 (LWP 18091)]
// Cached data: 30
// final data: 5
// [Thread 0x7ffff66b8700 (LWP 18091) exited]
// [Thread 0x7ffff6eb9700 (LWP 18090) exited]
// [Inferior 1 (process 18089) exited normally]
// (gdb) r
// Starting program: /home/kriti.malik@ds1.deepranalytics.com/repos/ec2/a.out 
// [Thread debugging using libthread_db enabled]
// Using host libthread_db library "/lib64/libthread_db.so.1".
// In main: 7ffff7fe8740
// [New Thread 0x7ffff6eb9700 (LWP 18102)]
// [New Thread 0x7ffff66b8700 (LWP 18103)]
// Cached data: 30
// final data: 5
// [Thread 0x7ffff66b8700 (LWP 18103) exited]
// [Thread 0x7ffff6eb9700 (LWP 18102) exited]
// [Inferior 1 (process 18101) exited normally]
// (gdb) r
// Starting program: /home/kriti.malik@ds1.deepranalytics.com/repos/ec2/a.out 
// [Thread debugging using libthread_db enabled]
// Using host libthread_db library "/lib64/libthread_db.so.1".
// In main: 7ffff7fe8740
// [New Thread 0x7ffff6eb9700 (LWP 18110)]
// [New Thread 0x7ffff66b8700 (LWP 18111)]
// Cached data: 30
// final data: 5
// [Thread 0x7ffff66b8700 (LWP 18111) exited]
// [Thread 0x7ffff6eb9700 (LWP 18110) exited]
// [Inferior 1 (process 18109) exited normally]
// (gdb) r
// Starting program: /home/kriti.malik@ds1.deepranalytics.com/repos/ec2/a.out 
// [Thread debugging using libthread_db enabled]
// Using host libthread_db library "/lib64/libthread_db.so.1".
// In main: 7ffff7fe8740
// [New Thread 0x7ffff6eb9700 (LWP 18113)]
// [New Thread 0x7ffff66b8700 (LWP 18114)]
// Cached data: 30
// final data: 30
// [Thread 0x7ffff66b8700 (LWP 18114) exited]
// [Thread 0x7ffff6eb9700 (LWP 18113) exited]
// [Inferior 1 (process 18112) exited normally]
// (gdb) r
// Starting program: /home/kriti.malik@ds1.deepranalytics.com/repos/ec2/a.out 
// [Thread debugging using libthread_db enabled]
// Using host libthread_db library "/lib64/libthread_db.so.1".
// In main: 7ffff7fe8740
// [New Thread 0x7ffff6eb9700 (LWP 18116)]
// [New Thread 0x7ffff66b8700 (LWP 18117)]
// Cached data: 30
// [Thread 0x7ffff66b8700 (LWP 18117) exited]
// final data: 5
// [Thread 0x7ffff6eb9700 (LWP 18116) exited]
// [Inferior 1 (process 18115) exited normally]


// Reading symbols from ./a.out...done.
// (gdb) r
// Starting program: /home/kriti.malik@ds1.deepranalytics.com/repos/ec2/a.out 
// [Thread debugging using libthread_db enabled]
// Using host libthread_db library "/lib64/libthread_db.so.1".
// In main: 7ffff7fe8740
// [New Thread 0x7ffff6eb9700 (LWP 20222)]
// [New Thread 0x7ffff66b8700 (LWP 20223)]
// Cached data: 30
// final data: 5
// [Thread 0x7ffff66b8700 (LWP 20223) exited]
// [Thread 0x7ffff6eb9700 (LWP 20222) exited]
// [Inferior 1 (process 20218) exited normally]
// Missing separate debuginfos, use: yum debuginfo-install glibc-2.28-211.el8.x86_64 libgcc-8.5.0-15.el8.x86_64 libstdc++-8.5.0-15.el8.x86_64
// (gdb) r
// Starting program: /home/kriti.malik@ds1.deepranalytics.com/repos/ec2/a.out 
// [Thread debugging using libthread_db enabled]
// Using host libthread_db library "/lib64/libthread_db.so.1".
// In main: 7ffff7fe8740
// [New Thread 0x7ffff6eb9700 (LWP 20230)]
// [New Thread 0x7ffff66b8700 (LWP 20231)]
// [Thread 0x7ffff66b8700 (LWP 20231) exited]
// r

// INFINITE LOOP
