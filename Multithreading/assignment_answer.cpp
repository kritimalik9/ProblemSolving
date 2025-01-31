#include <bits/stdc++.h>

using namespace std;

struct th_pool
{
  void writer(int id)
  {
    cout << "writer th id: " << id << "\n";
    int cnt = 0;
    for(int i=0; i<10; i++)
    {
      {
        lock_guard<mutex> lg(mut);
        cnt = pkts.size();
        pkts.push_back(rand());
      }
      std::cout << "writer pushed pkt num #" << i << ", val: " << pkts[pkts.size()-1] << "..\n";
      if(cnt == 0)
      {
        cond.notify_all();
      }
      else
      {
        cout << "pkts sz = " << pkts.size() << "\n";
      }
    }
  }

  void reader(int id)
  {
    cout << "reader th id: " << id << "\n";
    vector<int> swap_q;
    uint32_t tot_rcvd = 0;
    while(1)
    {
      {
        unique_lock<mutex> ul(mut);
        std::cout << "reader thread rcvd #" << tot_rcvd
          << " number of packets"
          << "\n";
        int cnt = pkts.size();
        if(cnt == 0) cond.wait(ul);
        pkts.swap(swap_q);
      }
      // // now do all operations on swap_q outside locking
      tot_rcvd = tot_rcvd + swap_q.size();
      // std::cout << "reader thread rcvd #" << tot_rcvd
      //   << " number of packets"
      //   << "\n";
      // std::cout << "Consumer Pkts: ";

      // //Alex note: if at this point mut is locked, so no benefit of swapping
      // for(auto pkt_val: swap_q)
      // {
      //   std::cout << pkt_val << " ---> ";
      // }
      // std::cout << std::endl;
      swap_q.clear();
    }

  }

  th_pool() = delete;
  th_pool(int num_threads)
  {
    int i=0;
    for(;i<num_threads; i++)
    {
      threads.push_back(thread(&th_pool::writer, this, i));
    }
    threads.push_back(thread(&th_pool::reader, this, i));
  }
  ~th_pool()
  {
    int i=0;
    for(;i<threads.size(); i++)
    {
      threads[i].join();
    }
    cout << "main ending \n";
  }
private:
  vector<int> pkts;
  vector<thread> threads;
  mutex mut;
  condition_variable cond;
};

int main(int argc, const char* argv[])
{
    th_pool pool(1);
}