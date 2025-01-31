#include <chrono>
#include <functional>
#include <iostream>
#include <thread>
#include <vector>
#include <mutex>

// 1. lock_guard if you need to lock exactly 1 mutex for an entire scope.

// 2. scoped_lock if you need to lock a number of mutexes that is not exactly 1.

// 3. unique_lock if you need to unlock within the scope of the block (which includes use with a condition_variable).

// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ g++ simple_thread11_lock_guard.cpp -lpthread

int g_ref_val{0};
struct thread_pool {
  void print_from_new_thread(int id, int &ref_val) {
    for (int i = 0; i < 10000; i++) {
      g_ref_val++;
      {
          std::lock_guard<std::mutex> lo(m);
          ref_val++;
      }
    }
  }
  std::vector<std::thread> th_list;
  int num_threads;
  int& m_ref_val;
  std::mutex m;

  thread_pool(int cnt, int &ref_val) : m_ref_val{ref_val} {
    num_threads = cnt;
    for (int i = 1; i <= cnt; i++) {
      th_list.emplace_back(&thread_pool::print_from_new_thread, this, i,
                           std::ref(ref_val));
    }
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

// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7ff8b42ff740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 10, g_ref_val updated as: 0
// final m_ref_val: 20010, g_ref_val: 19884
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f81c307a740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 1174, g_ref_val updated as: 1166
// final m_ref_val: 20010, g_ref_val: 18621
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f253f87b740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 1936, g_ref_val updated as: 1954
// final m_ref_val: 20010, g_ref_val: 19666