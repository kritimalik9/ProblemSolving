#include <chrono>
#include <functional>
#include <iostream>
#include <thread>
#include <vector>
#include <atomic>

// IMP_NOTE: "Read - modify - write" should not be interrupted - rest can be done in parallel
// So, total count will be the expected count across all threads, be it any number of times incremented in each thread

std::atomic<int> g_ref_val{0};
struct thread_pool {
  void print_from_new_thread(int id, int &ref_val) {
    // std::cout << "Inside ENTER thread id:  " << std::hex <<
    // std::this_thread::get_id()
    //     << std::dec
    //     << ", Internal id: " << id
    //     << ", updated ref_val: " << ref_val
    //     << "\n";
    for (int i = 0; i < 10000; i++) { // IMP_NOTE: Increase loop count to see the real impact
      g_ref_val++;
      ref_val++;
    }
    // std::this_thread::sleep_for(std::chrono::seconds(3));
    // std::cout << "Inside EXIT thread id:  " << std::hex <<
    // std::this_thread::get_id()
    //     << std::dec
    //     << ", Internal id: " << id
    //     << ", updated ref_val: " << ref_val
    //   << ", updated g_ref_val: " << g_ref_val
    //     << "\n";
  }
  std::vector<std::thread> th_list;
  int num_threads;
  int &m_ref_val;

  thread_pool(int cnt, int &ref_val) : m_ref_val{ref_val} {
    num_threads = cnt;
    for (int i = 1; i <= cnt; i++) {
      th_list.emplace_back(&thread_pool::print_from_new_thread, this, i,
                           std::ref(ref_val));
    }
  }
  ~thread_pool() {
    for (int i = 0; i < num_threads; i++) {
      // when main fn ends,
      // thread_pool object calls destructor which in turn waits for all threads
      // in th_list to finish before main exits
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
// In main: 7f8357b4c740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 10010, g_ref_val updated as: 10000
// final m_ref_val: 20010, g_ref_val: 20000
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7fe32891e740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 4494, g_ref_val updated as: 4556
// final m_ref_val: 19543, g_ref_val: 20000
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f498e2b2740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 5848, g_ref_val updated as: 5903
// final m_ref_val: 20010, g_ref_val: 20000
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7fed5bea7740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 10, g_ref_val updated as: 0
// final m_ref_val: 16328, g_ref_val: 20000