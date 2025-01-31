#include<chrono>
#include<thread>
#include<mutex>
#include<iostream>
#include<condition_variable>
using namespace std;
struct dat{
    uint32_t a = 0;
    uint32_t b = 0;
};

bool data_ready = false;

void fn1(dat* p_o, mutex* mut, condition_variable& cond){
    auto& m = *mut;
    auto& o = *p_o;
    for(int i=1;i<=5;i++){
        {
            lock_guard<mutex> lk(m);
            cout << "fn1: i = " << i
                << ", o.a = " << o.a
                << ", o.b = " << o.b
                << "\n";
            o.a = i;
            data_ready = true;
            cond.notify_one();
        }
        // this_thread::sleep_for(chrono::microseconds(1));
    }
}
void fn2(dat* p_o, mutex* mut, condition_variable& cond){
    auto& m = *mut;
    auto& o = *p_o;
    for(int i=1;i<=5;i++){
        {
            unique_lock<mutex> lk(m);
            cout << "fn2(before cond.wait): i = " << i
                << ", o.a = " << o.a
                << ", o.b = " << o.b
                << "\n";
            cond.wait(lk, [&](){return data_ready;});
            o.b = (o.b + o.a);
            cout << "fn2(after cond.wait): i = " << i
                << ", o.a = " << o.a
                << ", o.b = " << o.b
                << "\n";
            data_ready = false;
        }
        // this_thread::sleep_for(chrono::microseconds(1));
    }
}

int main(int argc, const char* argv[]){
    mutex mut;
    condition_variable cond;
    dat o;
    thread th1(fn1, &o, &mut, std::ref(cond));
    thread th2(fn2, &o, &mut, std::ref(cond));
    th1.join();
    th2.join();
    cout << "o.a: " << o.a
        << ", o.b: " << o.b
        << endl;
    return 0;
}