#include <iostream>
#include <iterator>
#include <vector>
#include <sstream>

// std::istream_iterator is a single-pass input iterator that reads successive objects of type T
//  from the std::basic_istream object for which it was constructed, by calling the appropriate operator>>.

// std::istreambuf_iterator is a single-pass input iterator 
// that reads successive characters from the std::basic_streambuf object for which it was constructed.

// Now, istream_iterator takes a template argument that says what the unformatted string-sequence from the streambuf should be formatted as,
// like istream_iterator<int> will interpret (whitespace-delimited) all incoming text as ints.

// On the other hand, istreambuf_iterator only cares about the raw characters
// and iterates directly over the associated streambuf of the istream that it gets passed.

// Generally, if you're only interested in the raw characters, use an istreambuf_iterator. 
// If you're interested in the formatted input, use an istream_iterator.

int main(int argc, const char* argv[])
{
    std::vector<int> ids;
    std::istringstream sstream_ex{"1 3 4 6 10 15 20 34 0"};
    std::istream_iterator<int> istream_itr1(sstream_ex), istream_end;
    while(istream_itr1 != istream_end) // stream reach end on failure or end of file ?
    {
        ids.push_back(*istream_itr1);
        istream_itr1++;
    }
    for(const auto elem: ids)
    {
        std::cout << elem << " ";
    }
    return 0;
}