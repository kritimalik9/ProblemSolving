#include <chrono>
#include <functional>
#include <iostream>
#include <thread>
#include <vector>
#include <shared_mutex>

// shared_lock is available starting c++17

// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ g++ simple_thread15_shared_lock.cpp -lpthread -std=c++17

// std::unique_lock
// Exclusive Ownership: 
// Only one thread can hold the lock at a time.
// Flexible Lock Management: 
// std::unique_lock provides various constructors and member functions to manage the lock,
// such as deferred locking, timed locking, and lock ownership transfer.
// RAII: 
// The lock follows the RAII (Resource Acquisition Is Initialization) idiom, 
// automatically releasing the mutex when the std::unique_lock object is destroyed
// IMP_NOTE : in print_from_new_thread, 
// std::unique_lock ensures that only one thread can execute the critical section at a time.

// std::shared_lock
// Shared Ownership: Multiple threads can hold the lock at the same time.
// Read-Only Access: Suitable for scenarios where threads only need to read a shared resource.
// Compatibility with std::shared_mutex: Works with std::shared_mutex, which supports both shared and exclusive locking.
// IMP_NOTE:
// Multiple std::shared_lock instances can hold the mutex simultaneously, 
// but it cannot coexist with a std::unique_lock on the same mutex.

// std::unique_lock provides exclusive access to a resource, suitable for write operations, 
// while std::shared_lock allows multiple threads to read a resource concurrently, 
// enhancing performance in read-heavy scenarios

int g_ref_val{0};
struct thread_pool {
  void print_from_new_thread(int id, int &ref_val) {
    std::this_thread::sleep_for(std::chrono::seconds(1));
    for (int i = 0; i < 10000; i++) {
      {
          std::unique_lock<std::shared_mutex> lo(m); // IMP_NOTE: Automatically locks here unless you defer_lock
          g_ref_val++;
          ref_val++;
      }
    }
  }
  void read_thread(int id, int &ref_val)
  {// Like this there can be multiple reader threads each holding it's own shared_lock instance
      std::shared_lock<std::shared_mutex> lo(m);// IMP_NOTE: sole purpose to not read cache/corrupted data??
      std::cout << "Final values from read thread, ref_val = " << ref_val
          << ", g_ref_val = " << g_ref_val
          << "\n";
  }
  std::vector<std::thread> th_list;
  int num_threads;
  int& m_ref_val;
  std::shared_mutex m;

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

// After adding sleep in write threads

// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7fcad2d0c740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 10, g_ref_val updated as: 0
// Final values from read thread, ref_val = 10, g_ref_val = 0
// final m_ref_val: 20010, g_ref_val: 20000
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f2239771740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 10, g_ref_val updated as: 0
// Final values from read thread, ref_val = 10, g_ref_val = 0
// final m_ref_val: 20010, g_ref_val: 20000
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ 