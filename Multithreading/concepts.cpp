// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ g++ -std=c++2a -fconcepts concepts.cpp
#include <iostream>
template<class T>
class divide
{
public:
    T divide_fn(T a, T b)
    {
        return a/b;
    }
};
int main_first()
{
    divide<int> div;
    std::cout << "divide<int> gets initialized, and div.divide_fn(10, 2): " << div.divide_fn(10, 2) << std::endl;
    divide<const char*> div2;
    std::cout << "Initialization divide<const char*> works" << std::endl;
    return 0;
    // std::cout << "divide<int> gets initialized, and div.divide_fn of divide<const char*> outputs: " << div2.divide_fn("hi", "bye") << std::endl;
}

// To fail divide<const char*> do this below

template <typename T>
concept dividable = requires (T a, T b)
{
    a/b;
};
template<class T> requires dividable<T>
class divide_concept
{
public:
    T divide_fn(T a, T b)
    {
        return a/b;
    }
};
int main()
{
    divide_concept<int> div;
    std::cout << "divide<int> gets initialized, and div.divide_fn(10, 2): " << div.divide_fn(10, 2) << std::endl;
    divide_concept<const char*> div2;
    std::cout << "Initialization divide<const char*> works here? NO!" << std::endl;
    return 0;
}

// OUTPUT:
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ g++ -std=c++2a -fconcepts concepts.cpp 
// concepts.cpp: In function ‘int main()’:
// concepts.cpp:43:31: error: template constraint failure
//      divide_concept<const char*> div2;
//                                ^
// concepts.cpp:43:31: note:   constraints not satisfied
// concepts.cpp:26:9: note: within ‘template<class T> concept const bool dividable<T> [with T = const char*]’
//  concept dividable = requires (T a, T b)
//          ^~~~~~~~~
// concepts.cpp:26:9: note:     with ‘const char* a’
// concepts.cpp:26:9: note:     with ‘const char* b’
// concepts.cpp:26:9: note: the required expression ‘(a / b)’ would be ill-formed