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
        while (!has_data);
        // single-threaded optimization called reordering possible here scenario #1.
        // below is the possible single threaded optimization that can happen to this above code
        // bool cached = has_data;
        // while(!cached);
    }
    std::thread wr;
    bool has_data = false;
    thread_info()
        :
          wr{&thread_info::writer_thread, this}
    {}
    ~thread_info() {
        wr.join();
        std::cout << "final has_data: " << has_data << std::endl;
    }
};

int main(int argc, const char *argv[]) {
  std::cout << "In main: " << std::hex << std::this_thread::get_id() << "\n";

  thread_info threads;
  std::this_thread::sleep_for(std::chrono::seconds(3));
  threads.has_data = true;

  return 0;
}
