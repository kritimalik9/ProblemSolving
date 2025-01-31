#include<chrono>
#include<thread>
#include<mutex>
#include<iostream>
#include<condition_variable>
#include<vector>
#include<atomic>
using namespace std;
struct dat{
    uint32_t a = 0;
    uint64_t b = 0;
};

// when the locked operations take prolonged amount of time, using conditional variables is preferable
// If locked operations  are expected to be "short", Just spinning with mutex will be way more efficient in this case. 

class spin_lock
{
private:
    std::atomic_flag atomic_flag = ATOMIC_FLAG_INIT;
    int retries{0};

    // void backoff() {
    //     const int max_retries = 8;
    //     if (retries < max_retries) {
    //         std::this_thread::yield();
    //     } else {
    //         auto delay = std::chrono::microseconds(1 << (retries - max_retries));
    //         std::this_thread::sleep_for(delay);
    //     }
    // }

public:
    void lock()
    {
        retries = 0;
        while (atomic_flag.test_and_set(std::memory_order_acquire))
        {
            std::this_thread::yield();
        }
     
    }
    void unlock()
    {
        atomic_flag.clear(std::memory_order_release);
    }
};

vector<int> nn_list;

int N = 1000000;
std::atomic<int> data_ready{ 0 };
void fn1(dat* p_o, spin_lock* mut, condition_variable_any& cond){
    auto& m = *mut;
    auto& o = *p_o;
    int cnt = 0;
    for(int i=1;i<=N;i++){
        // cout << "fn1: i = " << i
        //     << ", o.a = " << o.a
        //     << ", o.b = " << o.b
        //     << "\n";
        o.a = i;
        cnt = nn_list.size();
        nn_list.push_back(o.a);
    }
    data_ready.store( 1, std::memory_order_release );
}
void fn2(dat* p_o, spin_lock* mut, condition_variable_any& cond){
    auto& m = *mut;
    auto& o = *p_o;
    vector<int> temp_nn_l;
    int N_cnt = 0;
    while(1){
        while ( data_ready.load( std::memory_order_acquire ) == 0 );
        // cout << "fn2(before cond.wait): o.a = " << o.a
        //     << ", o.b = " << o.b
        //     << "\n";
        nn_list.swap(temp_nn_l);
        for(const auto nn: temp_nn_l){
            o.b = (o.b + nn);N_cnt++;
        }
        // cout << "fn2(after cond.wait): o.a = " << o.a
        //     << ", o.b = " << o.b
        //     << "\n";
        temp_nn_l.clear();
        if(N_cnt == N) break;
    }
}

int main(int argc, const char* argv[]){
    mutex mut;
    spin_lock cust_mut;
    condition_variable_any cond;
    dat o;
    auto start = chrono::high_resolution_clock::now();
    thread th1(fn1, &o, &cust_mut, std::ref(cond));
    thread th2(fn2, &o, &cust_mut, std::ref(cond));
    th1.join();
    th2.join();
    auto end = chrono::high_resolution_clock::now();
    cout << "o.a: " << o.a
        << ", o.b: " << o.b
        << ", T: " << chrono::duration_cast<chrono::microseconds>(end-start).count()
        << endl;
    return 0;
}