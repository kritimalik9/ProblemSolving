#include <iostream>
#include <vector>
#include <iterator>

class student
{
    std::string name;
    int id;
    double percentage;
    int toycnt;
public:

    // Friendship isn't mutual
    // Friendship isn't inherited

    // A friend function is a special function in C++ 
    // that in spite of not being a member function of a class
    // has the privilege to access the private and protected data of a class.

    // you'll be writing to this student s obj
    friend std::istream& operator >> (std::istream& is, student& s);
    friend std::ostream& operator << (std::ostream& os, const student& s);
    friend int& operator >> (int& toy_new_cnt, student& s);

    int operator+(const student& s) const
    {
        return this->toycnt + s.toycnt;
    }
 
};

// 1) cout is an object of ostream class and cin is an object of istream class 
// 2) These operators must be overloaded as a global function. 
//    And if we want to allow them to access private data members of the class, we must make them friend.
std::istream& operator >> (std::istream& is, student& s)
{
    is >> s.name >> s.id >> s.percentage;
    return is;
}
int& operator >> (int& toy_new_cnt, student& s)
{
    toy_new_cnt = toy_new_cnt * s.toycnt;
    return toy_new_cnt;
}
std::ostream& operator << (std::ostream& os, const student& s)
{
    os << s.name << " " << s.id << " " << s.percentage;
    return os;
}
int main(int argc, const char* argv[])
{
    std::vector<student> student_list;
    std::istream_iterator<student> student_stream(std::cin), student_end;

    // In a console, there are specific commands to trigger the EOF:
    // In UNIX systems it is Ctrl+D(or triggered by an error), in Windows Ctrl+Z.
    // std::copy(student_stream, student_end, std::back_inserter(student_list));
    while(student_stream != student_end)
    {
        student_list.emplace_back((*student_stream));
        student_stream++;
    }

    for(const auto& s: student_list)
    {
        std::cout << s << std::endl;
    }
    std::cout << std::endl;

    return 0;
}
