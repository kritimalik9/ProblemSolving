#include <iostream>
#include <thread>
#include <functional>
// g++ simple_thread2.cpp -lpthread

void print_from_global_fn()
{
    std::cout << "Inside new thread, thread id:  " << std::this_thread::get_id() << std::endl;
}
std::function<void()> fn(print_from_global_fn);
struct new_thread_class
{
    void print_from_new_thread()
    {
        std::cout << "Inside new thread, thread id:  " << std::this_thread::get_id() << std::endl;
    }
    // Class thread represents individual threads of execution
    // The below two dont work - initialization doesn't work from inside class
    // std::thread thr(print_from_global_fn);
    // std::thread thr1(fn);
    std::thread thr;

    std::thread thr2;

    new_thread_class(std::function<void()>& cb)
    : thr{cb},
      thr2{&new_thread_class::print_from_new_thread, this}
    {}

    // An initialized thread object represents an active thread of execution

};

struct main_thread_class
{
    void print_from_main_thread()
    {
        std::cout << "Inside main thread, thread id:  " << std::this_thread::get_id() << std::endl;
    }
};

int main(int argc, const char* argv[])
{
    std::cout << "In main: " << std::endl;
    // Threads begin execution immediately upon construction of the associated thread object 
    // Below doesnt work either
    // new_thread_class t(print_from_global_fn());
    new_thread_class t(fn);
    main_thread_class m;
    for(int i=0; i<5; i++)
    {
        m.print_from_main_thread();
        t.print_from_new_thread();
    }
    return 0;
}