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
        data = "hello";
        has_data.store(true);
    }
    void reader_thread()
    {
        if(has_data.load())
        {
            std::string d = data;
            if(d != "hello")
            {
                std::cout << "assert failed\n" ;
            }
        }
    }
    std::thread rd;
    std::thread wr;
    std::string data = "";
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
  std::cout << "In main: " << std::hex << std::this_thread::get_id() << "\n";

  thread_info threads;

  return 0;
}

// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7fd13091f740
// Segmentation fault (core dumped)
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7fba4e16d740
// final data: hello
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f560cc63740
// Segmentation fault (core dumped)
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$

// (gdb) bt
// #0  0x00007ffff6f8a1e5 in __memmove_avx_unaligned_erms () from /lib64/libc.so.6
// #1  0x00007ffff7949e17 in void std::__cxx11::basic_string<char, std::char_traits<char>, std::allocator<char> >::_M_construct<char*>(char*, char*, std::forward_iterator_tag) () from /lib64/libstdc++.so.6
// #2  0x000000000040168d in thread_info::reader_thread (this=0x7fffffffdb40) at simple_thread18_memorder2.cpp:22