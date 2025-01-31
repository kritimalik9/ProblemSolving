#include <chrono>
#include <functional>
#include <iostream>
#include <thread>
#include <vector>

int g_ref_val = 0;
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
            << "\n";

  return 0;
}

// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f97e77c7740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 10
// final m_ref_val: 20010, g_ref_val: 20000
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f599df50740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 7612
// final m_ref_val: 20010, g_ref_val: 20000
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7fe16050f740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 10010
// final m_ref_val: 20010, g_ref_val: 20000

// kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f67a1e5d740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 8853
// final m_ref_val: 20010, g_ref_val: 20000
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f57c3a82740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 10
// final m_ref_val: 20010, g_ref_val: 20000
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f9fe60a2740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 1999
// final m_ref_val: 16666, g_ref_val: 16651

// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f01593a2740
// Orig values, ref_val: 10
// Back to main, ref_val updated as: 5971
// final m_ref_val: 19365, g_ref_val: 19266