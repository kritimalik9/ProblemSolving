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

void fn1(dat* p_o, mutex* mut, condition_variable& cond1, condition_variable& cond2){
    auto& m = *mut;
    auto& o = *p_o;
    for(int i=1;i<=10000;i++){
        {
            unique_lock<mutex> lk(m);
            // cout << "fn1(before cond.wait): i = " << i
            //     << ", o.a = " << o.a
            //     << ", o.b = " << o.b
            //     << "\n";
            cond1.wait(lk, [&](){return !data_ready;});
            o.a = i;
            data_ready = true;
        }
        cond2.notify_one();
    }
}
void fn2(dat* p_o, mutex* mut, condition_variable& cond1, condition_variable& cond2){
    auto& m = *mut;
    auto& o = *p_o;
    for(int i=1;i<=10000;i++){
        {
            unique_lock<mutex> lk(m);
            // cout << "fn2(before cond.wait): i = " << i
            //     << ", o.a = " << o.a
            //     << ", o.b = " << o.b
            //     << "\n";
            cond2.wait(lk, [&](){return data_ready;});
            o.b = (o.b + o.a);
            data_ready = false;
        }
        cond1.notify_one();
        // cout << "fn2(after cond.wait): i = " << i
        //     << ", o.a = " << o.a
        //     << ", o.b = " << o.b
        //     << "\n";
    }
}

int main(int argc, const char* argv[]){
    mutex mut;
    condition_variable cond1;
    condition_variable cond2;
    dat o;
    thread th1(fn1, &o, &mut, std::ref(cond1), std::ref(cond2));
    thread th2(fn2, &o, &mut, std::ref(cond1), std::ref(cond2));
    th1.join();
    th2.join();
    cout << "o.a: " << o.a
        << ", o.b: " << o.b
        << endl;
    return 0;
}