int main()
{
    std::vector<std::string> getline_str2;// read a string from the standard input - enter a string with spaces, atleast 3 words
    int line = 0;
    std::string line_str;
    while (std::getline(std::cin, line_str))
    {
        getline_str2.push_back(line_str);
        line++;
    }
    std::cout << "Entered getline_str2:" << std::endl;
    for (int i = 0; i < line; i++)
    {
        std::cout << getline_str2[i] << std::endl;
    }
    /*******************************************************************************************************/
    // BEST2
    std::vector<std::string> getline_str3;// read a string from the standard input - enter a string with spaces, atleast 3 words
    std::copy(std::istream_iterator<std::string>(std::cin), std::istream_iterator<std::string>(), std::back_inserter(getline_str3));
    std::cout << "Entered getline_str3:" << std::endl;
    std::copy(getline_str3.begin(), getline_str3.end(), std::ostream_iterator<std::string>(std::cout, "\n"));
    /*******************************************************************************************************/
    // BEST3
    std::vector<std::string> getline_str4;// read a string from the standard input - enter a string with spaces, atleast 3 words
    std::copy(std::istream_iterator<std::string>(std::cin), std::istream_iterator<std::string>(), std::back_inserter(getline_str4));
    std::cout << "Entered getline_str4:" << std::endl;
    std::copy(getline_str4.begin(), getline_str4.end(), std::ostream_iterator<std::string>(std::cout, "\n"));
    /*******************************************************************************************************/
    // BEST4
    std::vector<std::string> getline_str5;// read a string from the standard input - enter a string with spaces, atleast 3 words
    std::copy(std::istream_iterator<std::string>(std::cin), std::istream_iterator<std::string>(), std::back_inserter(getline_str5));
    std::cout << "Entered getline_str5:" << std::endl;
    std::copy(getline_str5.begin(), getline_str5.end(), std::ostream_iterator<std::string>(std::cout, "\n"));
    /*******************************************************************************************************/
    // BEST5
    std::vector<std