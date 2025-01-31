#include <chrono>
#include <functional>
#include <iostream>
#include <thread>
#include <vector>
#include <mutex>
#include <condition_variable>


// Class condition_variable provides a condition variable 
// that can only wait on an object of type unique_lock<mutex>, allowing maximum effciency on some platforms.

// If you want to use a different lock with condition_variable you need to use condition_variable_any

// IMP_NOTE:
// when you call condition_variable::wait, you implicitly release the lock on the mutex. 

int g_ref_val{0};
struct thread_pool {
  void print_from_new_thread(int id, int &ref_val) {
    for (int i = 0; i < 10000; i++) {
          std::unique_lock<std::mutex> lo(m); // IMP_NOTE: Automatically locks here unless you defer_lock
          g_ref_val++;
          ref_val++;
    }
    std::cout << "Triggering read thread\n"; // IMP_NOTE: std::endl in multiple threads doesn't show up new line at right point
    cv.notify_one();
  }
  void read_thread(int id, int &ref_val)
  {
      std::unique_lock<std::mutex> lo(m);
      cv.wait(lo);
      std::cout << "Final values from read thread, ref_val = " << ref_val
          << ", g_ref_val = " << g_ref_val
          << "\n";
  }
  std::vector<std::thread> th_list;
  int num_threads;
  int& m_ref_val;
  std::mutex m;
  std::condition_variable cv;

  thread_pool(int cnt, int &ref_val) : m_ref_val{ref_val} {
    num_threads = cnt+1;
    int i = 1;
    for (; i <=cnt; i++) {
      th_list.emplace_back(&thread_pool::print_from_new_thread, this, i,
                           std::ref(ref_val));
    }
      th_list.emplace_back(&thread_pool::read_thread, this, i,
                           std::ref(ref_val));
  }
  ~thread_pool() {
    for (int i = 0; i < num_threads; i++) {
      th_list[i].join();
    }
    std::cout << "final m_ref_val: " << m_ref_val
              << ", g_ref_val: " << g_ref_val << std::endl;
  }
};

int main(int argc, const char *argv[]) {
  std::cout << "In main: " << std::hex << std::this_thread::get_id() << "\n";

  int ref_val = 10;

  std::cout << std::dec << "Orig values, ref_val: " << ref_val << "\n";

  thread_pool pool(2, ref_val);

  std::cout << std::dec << "Back to main, ref_val updated as: " << ref_val
      << ", g_ref_val updated as: " << g_ref_val
      << "\n";

  return 0;
}

// IMP_NOTE: Final values from read thread always greater than 10000

// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f5d92f37740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 2729, g_ref_val updated as: 2721
// Triggering read thread
// Final values from read thread, ref_val = 13644, g_ref_val = 13634
// Triggering read thread
// final m_ref_val: 20010, g_ref_val: 20000
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f9cc4f41740
// Orig values, ref_val: 10
// Triggering read thread
// Back to main, ref_val updated as: 11635, g_ref_val updated as: 11643
// Triggering read thread
// Final values from read thread, ref_val = 20010, g_ref_val = 20000
// final m_ref_val: 20010, g_ref_val: 20000
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f048913d740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 1498, g_ref_val updated as: 1488
// Triggering read thread
// Final values from read thread, ref_val = 18374, g_ref_val = 18364
// Triggering read thread
// final m_ref_val: 20010, g_ref_val: 20000
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ 