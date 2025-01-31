#include <chrono>
#include <functional>
#include <iostream>
#include <thread>
#include <vector>
#include <mutex>

// What is Resource Acquisition is Initialization (RAII) Design Pattern

// RAII is idiom used in several object-oriented, 
// statically typed programming languages to describe a particular language behavior. 

// In RAII, holding a resource is a class invariant, 
// and is tied to object lifetime. 

// Resource allocation (or acquisition) is done during object creation (specifically initialization), by the constructor, 
// while resource deallocation (release) is done during object destruction (specifically finalization), by the destructor. 

// Thus if there are no object leaks, there are no resource leaks.

int g_ref_val{0};
struct thread_pool {
  void print_from_new_thread(int id, int &ref_val) {
    for (int i = 0; i < 10000; i++) {
      {
          std::unique_lock<std::mutex> lo(m); // IMP_NOTE: Automatically locks here unless you defer_lock
          g_ref_val++;
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
// In main: 7fe1a266c740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 704, g_ref_val updated as: 699
// final m_ref_val: 20010, g_ref_val: 20000
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7ff6ed96d740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 532, g_ref_val updated as: 529
// final m_ref_val: 20010, g_ref_val: 20000
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f25ff5a4740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 214, g_ref_val updated as: 209
// final m_ref_val: 20010, g_ref_val: 20000