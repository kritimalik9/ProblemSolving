#include <iostream>
#include <vector>
#include <iterator>
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ g++ -std=c++2a -fconcepts input_iterator_ex.cpp 
// https://learn.microsoft.com/en-us/cpp/standard-library/iterator-concepts?view=msvc-170
// https://www.geeksforgeeks.org/input-iterators-in-cpp/

// template<class I>
// concept input_iterator =
//     input_or_output_iterator<I> &&
//     indirectly_readable<I> &&
//     requires { typename ITER_CONCEPT(I); } &&
//     derived_from<ITER_CONCEPT(I), input_iterator_tag>;

// input_iterator is a concept

// requires /std:c++20 or later

// NOTE:
// std::boolalpha is a stream function object (also known as a manipulator) 
// that sets the boolalpha flag on a stream. 
// This flag causes boolean values to be written out as "true" or "false" rather than as "1" or "0"
int main()
{
    // Show that a istream_iterator has an input_iterator
    // std::cout << std::boolalpha << std::input_or_output_iterator<std::vector<int>::iterator> << std::endl; // outputs true
    // std::cout << std::boolalpha << std::input_iterator<std::istream_iterator<int>> << std::endl; // outputs true
    std::vector<int> v{1,2,3,4,5};
    decltype(v)::iterator it = v.begin()+3;
    std::cout << std::boolalpha << *it << ", and is vec empty: " << v.empty() << std::endl; // outputs true
    // std::cout << std::boolalpha << std::input_iterator<decltype(v)::iterator> << std::endl; // outputs true
}