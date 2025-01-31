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
        if(has_data.load())
        {
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

// (gdb) r
// Starting program: /home/kriti.malik@ds1.deepranalytics.com/repos/ec2/a.out 
// [Thread debugging using libthread_db enabled]
// Using host libthread_db library "/lib64/libthread_db.so.1".
// In main: 7ffff7fe8740
// [New Thread 0x7ffff6eb9700 (LWP 9738)]
// [New Thread 0x7fffee6b8700 (LWP 9739)]
// [Thread 0x7ffff6eb9700 (LWP 9738) exited]
// final data: 30
// [Thread 0x7fffee6b8700 (LWP 9739) exited]
// [Inferior 1 (process 9737) exited normally]
// (gdb) r
// Starting program: /home/kriti.malik@ds1.deepranalytics.com/repos/ec2/a.out 
// [Thread debugging using libthread_db enabled]
// Using host libthread_db library "/lib64/libthread_db.so.1".
// In main: 7ffff7fe8740
// [New Thread 0x7ffff6eb9700 (LWP 9741)]
// [Thread 0x7ffff6eb9700 (LWP 9741) exited]
// [New Thread 0x7fffeffff700 (LWP 9742)]
// final data: 5
// [Thread 0x7fffeffff700 (LWP 9742) exited]
// [Inferior 1 (process 9740) exited normally]