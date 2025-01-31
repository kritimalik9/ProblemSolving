#include <iostream>
#include <thread>

struct new_thread_class
{
    std::thread t;
    void print_from_new_thread()
    {
        std::cout << "Inside new thread, thread id:  " << std::this_thread::get_id() << std::endl;
    }
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
    new_thread_class t;
    main_thread_class m;
    for(int i=0; i<5; i++)
    {
        m.print_from_main_thread();
        t.print_from_new_thread();
    }
    return 0;
}