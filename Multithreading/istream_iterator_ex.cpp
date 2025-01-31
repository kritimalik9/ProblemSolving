#include <iostream>
#include <iterator>
#include <vector>
// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ g++ -g istream_iterator_ex.cpp 

// When reading characters,
// std::istream_iterator skips whitespace by default (unless disabled with std::noskipws or equivalent), 
// while std::istreambuf_iterator does not. 
// In addition, std::istreambuf_iterator is more efficient, 
// since it avoids the overhead of constructing and destructing the sentry object once per character.

// sentry object 
// An object of class basic_istream::sentry is constructed in local scope at the beginning of 
// each member function of std::basic_istream that performs input (both formatted and unformatted).

int main(int argc, const char* argv[])
{
    std::vector<int> ids;
    std::istream_iterator<int> istream_itr1(std::cin);
   // End-of-stream iterator
    std::istream_iterator<int> EOFintRead;
    uint32_t num_elems = 0;
    num_elems = *istream_itr1;
    istream_itr1++; // blocking here too??
    while(istream_itr1 != EOFintRead) // cin reach end only on failure  ?
    {
        std::cout << "is iteration starts " << num_elems << " " << std::endl;
        ids.push_back(*istream_itr1);
        std::cout << "recorded val: " << *istream_itr1 << " " << std::endl;
        num_elems = num_elems - 1;
        // if(num_elems == 0)
        // {
        //     break;
        // }
        istream_itr1++; // waits / blocks here cin to get a new value??
        std::cout << "is iteration ends " << std::endl;
    }
    for(const auto elem: ids)
    {
        std::cout << elem << " ";
    }
    return 0;
}