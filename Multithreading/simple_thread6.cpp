#include <iostream>
#include <thread>
#include <chrono>
#include <functional>

void print_from_global_fn(const char* thread_name, int id)
{
    std::this_thread::sleep_for (std::chrono::seconds(2));
    std::cout << "Inside global fn new thread, thread id:  " << std::hex << std::this_thread::get_id()
        << ", Thread name: " << thread_name
        << ", Internal id: " << id << std::endl;
}
int add(int a, int b)
{
    std::cout << std::endl;
    std::cout << "Rcvd a: " << a
        << ", Rcvd b: " << b
        << std::endl;
    return a+b;
}
struct new_thread_class
{
    // The arguments to the thread function are moved or copied by value. 
    // If a reference argument needs to be passed to the thread function, it has to be wrapped (e.g. with std::ref or std::cref).
    void print_from_new_thread(int id, int& ref_val, int* ref_val_dynamic, std::function<int(int)> f)
    {
        std::cout << "thread entry values, ref_val: " << ref_val
            << ", ref_val_dynamic: " << *(ref_val_dynamic)
            << std::endl;
        ref_val = 10;
        *(ref_val_dynamic) = (*ref_val_dynamic) - 20;
        std::this_thread::sleep_for (std::chrono::seconds(2));
        std::cout << "Inside new_thread_class, thread id:  " << std::hex << std::this_thread::get_id()
            << std::dec 
            << ", Internal id: " << id
            << ", ref_val: " << ref_val
            << ", ref_val-f: " << f(ref_val)
            << ", ref_val_dynamic: " << *(ref_val_dynamic)
            << ", ref_val_dynamic-f: " << f(*(ref_val_dynamic)) << std::endl;
    }
    // Class thread represents individual threads of execution
    std::thread thr1; // The class thread represents a single thread of execution
    std::thread thr3;

    new_thread_class(int thr1_id, int thr3_id, int& ref_val, int* ref_val_dynamic, std::function<int(int)>& f)
    : thr1{&new_thread_class::print_from_new_thread, this, thr1_id, std::ref(ref_val), ref_val_dynamic, f},
    thr3{print_from_global_fn, "th3", thr3_id}
    {
            thr3.join(); // print_from_global_fn
            thr1.join();        
    }

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
    int id = 0;
    std::thread thr2(print_from_global_fn, "mmm", ++id); // The class thread represents a single thread of execution
    int ref_val = 7;
    int ref_val2 = 45;
    int* p_ref_val2 = &ref_val2;
    int* ref_val_dynamic = new int(75);
    (*ref_val_dynamic) = 85;
    std::function<int(int, int)> add_fn(add);
    std::function<int(int)> f = std::bind(add_fn, std::placeholders::_1, 41);
    std::cout << std::dec << "Orig values, ref_val: " << ref_val
        << ", ref_val_dynamic: " << *(ref_val_dynamic)
        << ", p_ref_val2: " << *p_ref_val2
        << std::endl;
    new_thread_class t(++id, ++id, ref_val, ref_val_dynamic, f); // Threads begin execution immediately upon construction of the associated thread object 
    main_thread_class m; // this is not a thread unless initialized
    std::cout << std::dec << "Back to main, ref_val updated as: " << ref_val
        << ", ref_val_dynamic updated as: " << int(*ref_val_dynamic) << std::endl;
    
    thr2.join(); // print_from_global_fn

    delete ref_val_dynamic;

    return 0;
}

// The new keyword is the C++ way of doing it, and it will ensure that your type will have its constructor called. The new keyword is also more type-safe whereas malloc is not type-safe at all.

// The only way I could think that would be beneficial to use malloc would be if you needed to change the size of your buffer of data. The new keyword does not have an analogous way like realloc. The realloc function might be able to extend the size of a chunk of memory for you more efficiently.

// It is worth mentioning that you cannot mix new/free and malloc/delete.

// Note: Some answers in this question are invalid.

// int* p_scalar = new int(5);  // Does not create 5 elements, but initializes to 5
// int* p_array  = new int[5];  // Creates 5 elements



// https://stackoverflow.com/questions/6610046/stdfunction-and-stdbind-what-are-they-and-when-should-they-be-used