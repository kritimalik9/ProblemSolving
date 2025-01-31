#include <iostream>
#include <thread>

void print_from_global_fn()
{
    std::cout << "Inside global fn new thread, thread id:  " << std::hex << std::this_thread::get_id() << std::endl;
}
struct new_thread_class
{
    void print_from_new_thread()
    {
        std::cout << "Inside new_thread_class, thread id:  " << std::hex << std::this_thread::get_id() << std::endl;
    }
    // Class thread represents individual threads of execution
    std::thread thr1; // The class thread represents a single thread of execution
    std::thread thr3;

    new_thread_class()
    : thr1{&new_thread_class::print_from_new_thread, this},
    thr3{print_from_global_fn}
    {}

    // An initialized thread object represents an active thread of execution

};

struct main_thread_class
{
    void print_from_main_thread()
    {
        std::cout << "Inside main thread, thread id:  " << std::hex << std::this_thread::get_id() << std::endl;
    }
};

int main(int argc, const char* argv[])
{
    std::cout << "In main: " << std::hex << std::this_thread::get_id() << std::endl;
    std::thread thr2(print_from_global_fn); // The class thread represents a single thread of execution
    new_thread_class t; // Threads begin execution immediately upon construction of the associated thread object 
    main_thread_class m;

// std::terminate occurs when a destructor of std::thread is called but the thread represented by the object is still joinable,
// i.e., has neither been joined with or detached from.
    t.thr3.join();
    thr2.join();
    t.thr1.detach();
    return 0;
}