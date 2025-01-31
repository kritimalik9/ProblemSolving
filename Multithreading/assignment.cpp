#include <chrono>
#include <functional>
#include <iostream>
#include <thread>
#include <vector>
#include <mutex>
#include <condition_variable>

std::vector<int> pkt_que;
struct thread_pool {
  void producer(int id) {
    srand(time(0));
    // for (int i = 0; i < 10000; i++) {
    for (int i = 0; i < 100; i++) {
        std::unique_lock<std::mutex> lo(m);    
        pkt_que.push_back(rand());
        std::cout << "producer pushed pkt #" << i+1
          << ", val: " << pkt_que[pkt_que.size()-1] << std::endl;
        cv.notify_one();
    }
  }
  void consumer(int id)
  {
      std::vector<int> swap_pkt_que;
      int read_cnt = 0;
      while(true)
      {
        std::unique_lock<std::mutex> lo(m);
        uint32_t tot_rcvd = tot_rcvd + swap_pkt_que.size();
        swap_pkt_que.clear();
        if(pkt_que.size() != 0) pkt_que.swap(swap_pkt_que);
        else
        {
          cv.wait(lo);
          pkt_que.swap(swap_pkt_que);
        }
        std::cout << "Consumer thread rcvd #" << tot_rcvd+swap_pkt_que.size()
          << " number of packets"
          << "\n";
        std::cout << "Consumer Pkts: ";
        for(auto pkt_val: swap_pkt_que)
        {
          std::cout << pkt_val << " ---> ";
        }
        std::cout << std::endl;
      }      
  }
  std::vector<std::thread> th_list;
  int num_threads;
  std::mutex m;
  std::condition_variable cv;

  thread_pool(int cnt)
  {
    num_threads = cnt+1;
    int i = 1;
    th_list.emplace_back(&thread_pool::producer, this, i++);
    th_list.emplace_back(&thread_pool::consumer, this, i++);
  }
  ~thread_pool() {
    for (int i = 0; i < num_threads; i++) {
      th_list[i].join();
    }
  }
};

int main(int argc, const char *argv[]) {
  std::cout << "In main: " << std::hex << std::this_thread::get_id() << "\n";

  thread_pool pool(2);

  std::cout << std::dec << "Back to main, ref_val updated as: "
      << "\n";

  return 0;
}