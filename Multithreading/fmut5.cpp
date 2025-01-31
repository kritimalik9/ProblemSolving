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

class spin_lock {
public:
    explicit spin_lock( std::atomic<bool> *mutex ) noexcept :
        _mutex{ mutex }
    {}
    void lock()
    {
        bool flag = false;
        while ( !_mutex->compare_exchange_weak( flag, true ) )
            flag = false;  // must reset
    }

    void unlock() {
        _mutex->store( false );
    }
    ~spin_lock() noexcept {
        _mutex->store( false );
    }

private:
    std::atomic<bool> *const _mutex;
};

bool data_ready = false;
vector<int> nn_list;

int N = 1000000;

void fn1(dat* p_o, spin_lock* mut, condition_variable_any& cond){
    auto& m = *mut;
    auto& o = *p_o;
    int cnt = 0;
    for(int i=1;i<=N;i++){
        {
            lock_guard<spin_lock> lk(m);
            // cout << "fn1: i = " << i
            //     << ", o.a = " << o.a
            //     << ", o.b = " << o.b
            //     << "\n";
            o.a = i;
            cnt = nn_list.size();
            nn_list.push_back(o.a);
        }
        if(cnt == 0) 
            cond.notify_one();
    }
}
void fn2(dat* p_o, spin_lock* mut, condition_variable_any& cond){
    auto& m = *mut;
    auto& o = *p_o;
    vector<int> temp_nn_l;
    int N_cnt = 0;
    while(1){
        {
            unique_lock<spin_lock> lk(m);
            // cout << "fn2(before cond.wait): o.a = " << o.a
            //     << ", o.b = " << o.b
            //     << "\n";
            cond.wait(lk, [&](){return nn_list.size() != 0;});
            nn_list.swap(temp_nn_l);
        }
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
    std::atomic<bool> bool_mutex;
    spin_lock cust_mut{&bool_mutex};
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