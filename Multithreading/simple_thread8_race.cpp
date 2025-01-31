#include <iostream>
#include <thread>
#include <chrono>
#include <functional>

struct student
{
    int add_marks(int a, int b, int c)
    {
        std::cout << std::endl;
        std::cout << "Rcvd a: " << a
            << ", Rcvd b: " << b
            << ", Rcvd c: " << c
            << std::endl;
        return a+b+c;
    }
};

struct thread1
{
    void print_from_new_thread(int id, int& ref_val, int* ref_val_dynamic, std::function<int(int,int)> f)
    {
        std::cout << "t1 thread entry values, ref_val: " << ref_val
            << ", ref_val_dynamic: " << *(ref_val_dynamic)
            << std::endl;
        for(int i=0; i<100; i++)
        {
            ref_val++;
            (*ref_val_dynamic)++;
        }
        std::cout << "Inside t1, thread id:  " << std::hex << std::this_thread::get_id()
            << std::dec 
            << ", Internal id: " << id
            << ", ref_val: " << ref_val
            << ", ref_val_dynamic: " << *(ref_val_dynamic)
            << ", ref_val-f: " << f(ref_val, *(ref_val_dynamic))
            << std::endl;
    }
    std::thread t1;

    thread1(int thr1_id, int& ref_val, int* ref_val_dynamic, std::function<int(int,int)>& f)
    : t1{&thread1::print_from_new_thread, this, thr1_id, std::ref(ref_val), ref_val_dynamic, f}
    {
            // DONT PUT JOIN HERE UNLESS YOU INTEND TO FINISH THIS THREAD t1 BEFORE RETURNING BACK TO MAIN THREAD
            // t1.join();  
    }
    ~thread1()
    {
        t1.join(); // when main fn ends, thread1 object calls destructor which in turn waits for thread t1 to finish before main exits
    }

};

struct thread2
{
    void print_from_new_thread(int id, int& ref_val, int* ref_val_dynamic, std::function<int(int,int)> f)
    {
        std::cout << "t2 thread entry values, ref_val: " << ref_val
            << ", ref_val_dynamic: " << *(ref_val_dynamic)
            << std::endl;
        for(int i=0; i<100; i++)
        {
            ref_val++;
            (*ref_val_dynamic)++;
        }
        std::cout << "Inside t2, thread id:  " << std::hex << std::this_thread::get_id()
            << std::dec 
            << ", Internal id: " << id
            << ", ref_val: " << ref_val
            << ", ref_val_dynamic: " << *(ref_val_dynamic)
            << ", ref_val-f: " << f(ref_val, *(ref_val_dynamic))
            << std::endl;
    }
    std::thread t2;

    thread2(int thr1_id, int& ref_val, int* ref_val_dynamic, std::function<int(int,int)>& f)
    : t2{&thread2::print_from_new_thread, this, thr1_id, std::ref(ref_val), ref_val_dynamic, f}
    {
            // DONT PUT JOIN HERE UNLESS YOU INTEND TO FINISH THIS THREAD t2 BEFORE RETURNING BACK TO MAIN THREAD
            // t2.join();  
    }
    ~thread2()
    {
        t2.join(); // when main fn ends, thread1 object calls destructor which in turn waits for thread t1 to finish before main exits
    }
};


int main(int argc, const char* argv[])
{
    std::cout << "In main: " << std::hex << std::this_thread::get_id() << std::endl;
    int id = 0;

    int ref_val = 10;
    int* ref_val_dynamic = new int(20);
    student s;
    std::function<int(int, int)> f = std::bind(&student::add_marks, &s, std::placeholders::_2, 100, std::placeholders::_1);
    
    std::cout << std::dec << "Orig values, ref_val: " << ref_val
        << ", ref_val_dynamic: " << *(ref_val_dynamic)
        << std::endl;
    
    thread1 t1( ++id, ref_val, ref_val_dynamic, f);
    // std::this_thread::sleep_for (std::chrono::seconds(5));
    thread2 t2( ++id, ref_val, ref_val_dynamic, f);


    // t1.t1.join();
    // t2.t2.join();

    std::cout << std::dec << "Back to main, ref_val updated as: " << ref_val
        << ", ref_val_dynamic updated as: " << int(*ref_val_dynamic) << std::endl;
    
    delete ref_val_dynamic;

    return 0;
}


// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f5039159740
// Orig values, ref_val: 10, ref_val_dynamic: 20
// t1 thread entry values, ref_val: 10, ref_val_dynamic: 20
// Inside t1, thread id:  7f5038024700, Internal id: 1, ref_val: 110, ref_val_dynamic: 120, ref_val-f: 
// Rcvd a: 120, Rcvd b: 100, Rcvd c: 110
// 330
// Back to main, ref_val updated as: 110, ref_val_dynamic updated as: 120
// t2 thread entry values, ref_val: 110, ref_val_dynamic: 0
// Inside t2, thread id:  7f5037823700, Internal id: 2, ref_val: 210, ref_val_dynamic: 100, ref_val-f: 
// Rcvd a: 100, Rcvd b: 100, Rcvd c: 210
// 410
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7fa84152a740
// Orig values, ref_val: 10, ref_val_dynamic: 20
// t1 thread entry values, ref_val: 10, ref_val_dynamic: 20
// Inside t1, thread id:  7fa8403f5700, Internal id: 1, ref_val: 110, ref_val_dynamic: 120, ref_val-f: 
// Rcvd a: 120, Rcvd b: 100, Rcvd c: 110
// 330
// Back to main, ref_val updated as: 110, ref_val_dynamic updated as: 120
// t2 thread entry values, ref_val: 110, ref_val_dynamic: 0
// Inside t2, thread id:  7fa83fbf4700, Internal id: 2, ref_val: 210, ref_val_dynamic: 100, ref_val-f: 
// Rcvd a: 100, Rcvd b: 100, Rcvd c: 210
// 410
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f5167290740
// Orig values, ref_val: 10, ref_val_dynamic: 20
// Back to main, ref_val updated as: 10, ref_val_dynamic updated as: 20
// t2 thread entry values, ref_val: 10, ref_val_dynamic: 0
// Inside t2, thread id:  7f516595a700, Internal id: 2, ref_val: 110, ref_val_dynamic: 100, ref_val-f: 
// Rcvd a: 100, Rcvd b: 100, Rcvd c: 110
// 310
// t1 thread entry values, ref_val: 110, ref_val_dynamic: 100
// Inside t1, thread id:  7f516615b700, Internal id: 1, ref_val: 210, ref_val_dynamic: 200, ref_val-f: 
// Rcvd a: 200, Rcvd b: 100, Rcvd c: 210
// 510
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// In main: 7f8a815f2740
// Orig values, ref_val: 10, ref_val_dynamic: 20
// t1 thread entry values, ref_val: 10, ref_val_dynamic: 20
// Inside t1, thread id:  7f8a804bd700, Internal id: 1, ref_val: 110, ref_val_dynamic: 120, ref_val-f: 
// Back to main, ref_val updated as: t2 thread entry values, ref_val: Rcvd a: 120, Rcvd b: 100, Rcvd c: 110110
// 330
// , ref_val_dynamic: 120
// Inside t2, thread id:  7f8a7fcbc700, Internal id: 2, ref_val: 210, ref_val_dynamic: 220, ref_val-f: 
// Rcvd a: 220, Rcvd b: 100, Rcvd c: 210
// 530
// 110, ref_val_dynamic updated as: 220