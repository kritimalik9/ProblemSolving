#include <iostream>
#include <vector>
#include <iterator>
int main()
{
#if 0
    std::cout << "Hello, World!" << std::endl;

    /*******************************************************************************************************/

    std::string cin_str1; 
    std::cin >> cin_str1; // read a string from the standard input - enter a string with spaces, atleast 3 words
    // cin skips over and ignores any leading whitespace, cin stops when it gets to the next whitespace character.
    std::cout << "Entered cin_str1:" << cin_str1 << std::endl;

    /*******************************************************************************************************/
    
    // This cin starts where the previous cin left off, so it will read the rest of the line until next whitespace character.
    std::string cin_str2;
    std::cin >> cin_str2; 
    std::cout << "Entered cin_str2:" << cin_str2 << std::endl;

    /*******************************************************************************************************/

    // This cin starts where the previous cin left off, so it will read the rest of the line until next whitespace character.
    std::string cin_str3;
    std::cin >> cin_str3; 
    std::cout << "Entered cin_str3:" << cin_str3 << std::endl;
    
    /*******************************************************************************************************/

    // The getline() function will read an entire line 
    // including the leading, embedded, and trailing spaces up to the '\n' character and store it in the string object
    std::string getline_str1;// read a string from the standard input - enter a string with spaces, atleast 3 words
    // getline(cin >> getline_str1, getline_str1); // ws is a stream manipulator that skips leading whitespace
    std::getline(std::cin, getline_str1); 
    std::cout << "Entered getline_str1:" << getline_str1 << std::endl;

    /*******************************************************************************************************/

    /*above code output example:
    [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
    Hello, World!
    om shreem brzee namah om namah shivay
    Entered cin_str1:om
    Entered cin_str2:shreem
    Entered cin_str3:brzee
    Entered getline_str1: namah om namah shivay
    */

#endif
    /*******************************************************************************************************/

    //If successful, getline() reads the number of characters,
    //including the newline character,
    //but not including the terminating null byte (‘\0’)
    //std::basic_istream<char>& getline( std::basic_istream<char>& input, std::basic_string<char>& str );
    // input - 	the stream to get data from
    // Return value - 	input
    // fail - checks if an error has occurred
    // BEST1
    std::vector<std::string> getline_str2;// read a string from the standard input - enter a string with spaces, atleast 3 words
    int line = 0;
    std::string tmp;
    std::cout << "Enter num of lines of strings:" << std::endl;
    getline(std::cin, tmp);
    auto num_lines = std::stoi(tmp);
    while(getline(std::cin, tmp).fail() == false)
    {
        line++;
        getline_str2.emplace_back(tmp);
        std::cout << "Line " << line
            << ": Entered getline_str2:" << getline_str2[getline_str2.size()-1] << std::endl;
        num_lines--;
        if(num_lines == 0)
        {
            break;
        }
    }
    std::cout << "getline_str2.size():" << getline_str2.size() << std::endl;
    std::cout << "Entered getline_str2:" << std::endl;
    for(const auto& line : getline_str2)
    {
        std::cout << line << " " ;
    }
    std::cout << std::endl;

#if 0
    /*******************************************************************************************************/
    // DO PUT SPACE AFTER THE LAST INTEGER IN THE INPUT LINE TO GET THE CORRECT OUTPUT
    std::string tmp2;
    std::vector<int> getline_int_list;
    int int_elem = 0;
    std::cout << "Enter num of elems of integers:" << std::endl;
    std::getline(std::cin, tmp2);
    auto num_ints = std::stoi(tmp2);
    while(getline(std::cin, tmp2, ' ').fail() == false)
    {
        int_elem++;
        getline_int_list.emplace_back(stoi(tmp2));
        std::cout << "Elem " << int_elem
            << ": Entered getline_str2:" << getline_int_list[getline_int_list.size()-1]
            << ", num_ints: " << num_ints
            << std::endl;
        num_ints--;
        if(num_ints == 0)
        {
            break;
        }
    }
    std::cout << "getline_int_list.size():" << getline_int_list.size() << std::endl;
    std::cout << "Entered getline_int_list:" << std::endl;
    for(const auto& elem : getline_int_list)
    {
        std::cout << elem << " " ;
    }
    std::cout << std::endl;
    /*******************************************************************************************************/
#endif

    // BEST2
    int cin_elem_cnt = 0;
    std::cout << "Enter cin_elem_cnt:"<< std::endl;
    std::cin >> cin_elem_cnt;
    do 
    {
        std::cout<<"Enter a number, or numbers separated by a space, between 1 and 1000."<< std::endl;
        std::cin >> tmp; // considers both space and newline as delimiters
        std::cout << "Entered elem in loop: " << tmp << std::endl;
        cin_elem_cnt--;
        if(cin_elem_cnt == 0)
        {
            break;
        }
    }while (true); // or some condition
    /*******************************************************************************************************/

    // BEST3
    // std::cin >> std::noskipws >> tmp; // noskipws - do not skip whitespace
    // std::cout << "noskipws ex: " << tmp << std::endl;
    // noskipws not working
    std::cin >> tmp;
    std::cout << "noskipws ex: " << tmp << std::endl;

    return 0;
}