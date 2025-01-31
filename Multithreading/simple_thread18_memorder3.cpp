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
        p_data = new std::string("hello");
        has_data.store(true);
    }
    void reader_thread()
    {
        if(has_data.load())
        {
            if(p_data == nullptr)
            {
                std::cout << "assert failed\n" ;
            }
        }
    }
    std::thread rd;
    std::thread wr;
    std::string* p_data = nullptr;
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
        std::cout << "final p_data: " << *p_data
            << std::endl;
    }
};

int main(int argc, const char *argv[]) {
  std::cout << "In main: " << std::hex << std::this_thread::get_id() << "\n";

  thread_info threads;

  return 0;
}

// In main: 7ffff7fe8740
// [New Thread 0x7ffff6eb9700 (LWP 8725)]
// [New Thread 0x7ffff66b8700 (LWP 8726)]
// [Thread 0x7ffff6eb9700 (LWP 8725) exited]
// [Thread 0x7ffff66b8700 (LWP 8726) exited]
// final p_data: hello

// or

// Thread 1 "a.out" received signal SIGSEGV, Segmentation fault.
// 0x00007ffff794a1a4 in std::basic_ostream<char, std::char_traits<char> >& std::operator<< <char, std::char_traits<char>, std::allocator<char> >(std::basic_ostream<char, std::char_traits<char> >&, std::__cxx11::basic_string<char, std::char_traits<char>, std::allocator<char> > const&) () from /lib64/libstdc++.so.6
// (gdb) bt
// #0  0x00007ffff794a1a4 in std::basic_ostream<char, std::char_traits<char> >& std::operator<< <char, std::char_traits<char>, std::allocator<char> >(std::basic_ostream<char, std::char_traits<char> >&, std::__cxx11::basic_string<char, std::char_traits<char>, std::allocator<char> > const&) () from /lib64/libstdc++.so.6
// #1  0x000000000040160b in thread_info::~thread_info (this=0x7fffffffdb60, __in_chrg=<optimized out>) at simple_thread18_memorder2.cpp:41
// #2  0x00000000004010bf in main (argc=1, argv=0x7fffffffdc68) at simple_thread18_memorder2.cpp:49