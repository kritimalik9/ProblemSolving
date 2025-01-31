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
        has_data.store(true, std::memory_order_seq_cst); // maybe in cache but in order execution
    }
    void reader_thread()
    {
        while(!has_data.load(std::memory_order_seq_cst)); // but at times reader thread not pushed it to main memory from cache
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


// (gdb) br 27
// Breakpoint 1 at 0x401358: file simple_thread18_memorder6.cpp, line 27.
// (gdb) r
// Starting program: /home/kriti.malik@ds1.deepranalytics.com/repos/ec2/a.out 
// [Thread debugging using libthread_db enabled]
// Using host libthread_db library "/lib64/libthread_db.so.1".
// In main: 7ffff7fe8740
// [New Thread 0x7ffff6eb9700 (LWP 14492)]
// [New Thread 0x7ffff66b8700 (LWP 14493)]
// Cached data: 30
// [Thread 0x7ffff66b8700 (LWP 14493) exited]
// [Thread 0x7ffff6eb9700 (LWP 14492) exited]
// final data: 5
// [Inferior 1 (process 14486) exited normally]
// (gdb) r
// Starting program: /home/kriti.malik@ds1.deepranalytics.com/repos/ec2/a.out 
// [Thread debugging using libthread_db enabled]
// Using host libthread_db library "/lib64/libthread_db.so.1".
// In main: 7ffff7fe8740
// [New Thread 0x7ffff6eb9700 (LWP 14547)]
// [New Thread 0x7ffff66b8700 (LWP 14548)]
// Cached data: 30
// [Thread 0x7ffff66b8700 (LWP 14548) exited]
// [Switching to Thread 0x7ffff6eb9700 (LWP 14547)]

// Thread 2 "a.out" hit Breakpoint 1, thread_info::reader_thread (this=0x7fffffffdb60) at simple_thread18_memorder6.cpp:27
// 27                      std::cout << "assert failed\n" ;
// (gdb) p x
// $1 = 30
// (gdb) p data
// $2 = 5
// (gdb) p has_data
// $3 = {_M_base = {static _S_alignment = 1, _M_i = false}}
// (gdb) p has_data.load()
// Too few arguments in function call.
// (gdb) p has_data.load(std::memory_order_seq_cst)
// $4 = false
// (gdb) thread info
// Invalid thread ID: info
// (gdb) info threads
//   Id   Target Id                                 Frame 
//   1    Thread 0x7ffff7fe8740 (LWP 14546) "a.out" 0x00007ffff7bb86cd in __pthread_timedjoin_ex () from /lib64/libpthread.so.0
// * 2    Thread 0x7ffff6eb9700 (LWP 14547) "a.out" thread_info::reader_thread (this=0x7fffffffdb60) at simple_thread18_memorder6.cpp:27