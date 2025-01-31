#include <chrono>
#include <functional>
#include <iostream>
#include <thread>
#include <vector>
#include <mutex>
#include <condition_variable>

// atomic<T>::operator T and atomic<T>::operator= are equivalent to atomic<T>::load and atomic<T>::store respectively

// but the advantage of load()/store() is that they allow to specify memory order. 
// atomic<T>::operator T and atomic<T>::operator= would use the most safe and slow std::memory_order_seq_cst

// The memory order argument specifies ordering constraints between
// atomic and non-atomic memory accesses in different threads.

// std::memory_order specifies how memory accesses,
//  including regular, non-atomic memory accesses, are to be ordered around an atomic operation.

// evaluation A that is "sequenced-before" evaluation B may also "carry a dependency" into B 

int g_ref_val{0};
struct thread_info {
  void writer_thread(int &ref_val) {
    for (int i = 0; i < 10000; i++) {
          std::unique_lock<std::mutex> lo(m); // IMP_NOTE: Automatically locks here unless you defer_lock
          g_ref_val++;
          ref_val++;
    }
    std::cout << "Triggering read thread\n"; // IMP_NOTE: std::endl in multiple threads doesn't show up new line at right point
    cv.notify_one();
  }
  void reader_thread(int &ref_val)
  {
      std::unique_lock<std::mutex> lo(m);// IMP_NOTE: sole purpose to not read cache/corrupted data??
      cv.wait(lo);
      std::cout << "Final values from read thread, ref_val = " << ref_val
          << ", g_ref_val = " << g_ref_val
          << "\n";
  }
  int& m_ref_val;
  std::mutex m;
  std::condition_variable cv;
  std::thread rd;
  std::thread wr;

  thread_info(int &ref_val)
      : m_ref_val{ref_val},
        rd{&thread_info::reader_thread,
            this,
            std::ref(ref_val)},
        wr{&thread_info::writer_thread,
            this,
            std::ref(ref_val)}
  {}
  ~thread_info() {
      rd.join();
      wr.join();
      std::cout << "final m_ref_val: " << m_ref_val
                << ", g_ref_val: " << g_ref_val << std::endl;
  }
};

int main(int argc, const char *argv[]) {
  std::cout << "In main: " << std::hex << std::this_thread::get_id() << "\n";

  int ref_val = 10;

  std::cout << std::dec << "Orig values, ref_val: " << ref_val << "\n";

  thread_info threads(ref_val);

  std::cout << std::dec << "Back to main, ref_val updated as: " << ref_val
      << ", g_ref_val updated as: " << g_ref_val
      << "\n";

  return 0;
}

// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f64f488e740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 10, g_ref_val updated as: 0
// Triggering read thread
// Final values from read thread, ref_val = 10010, g_ref_val = 10000
// final m_ref_val: 10010, g_ref_val: 10000
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ 