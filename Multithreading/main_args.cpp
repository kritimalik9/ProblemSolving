#include <iostream>
int main(int argc, const char* argv[])
{
    std::cout << "Entered num of elems of integers:" << argc << std::endl;
    for(int i = 0; i < argc; i++)
    {
        std::cout << argv[i] << std::endl;
    }
}