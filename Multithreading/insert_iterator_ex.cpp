#include <iostream>
#include <vector>
#include <iterator>
#include <list>

int main()
{
    std::vector<int> v{1, 2, 3, 4, 5};
    std::list<int> l{-1, -2, -3};
    std::copy(v.begin(), v.end(), std::insert_iterator<std::list<int>>(l, std::next(l.begin())));
    for(const auto elem: l)
    {
        std::cout << elem << " ";
    }
    std::cout << std::endl;
    std::vector<int> v1{1, 2, 3, 4, 5};
    std::list<int> l1{-1, -2, -3};
    std::copy(l1.begin(), l1.end(), std::insert_iterator<std::vector<int>>(v1, v1.begin() + 2));
    for(const auto elem: v1)
    {
        std::cout << elem << " ";
    }
    std::cout << std::endl;
    std::copy(l1.begin(), l1.end(), std::back_inserter<std::vector<int>>(v1));
    for(const auto elem: v1)
    {
        std::cout << elem << " ";
    }
}