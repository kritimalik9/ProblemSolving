class Solution {
public:
    bool repeatedSubstringPattern(string s) {
        int n = s.length();
        for (int i = 1; i <= n / 2; i++) {
            std::cout << "n: " << n
                << ", i: " << i << std::endl;
            if (n % i == 0) {
                string pattern = "";
                for (int j = 0; j < n / i; j++) {
                    pattern += s.substr(0, i);
                }
                    std::cout << "s: " << s
                        << ", p: " << pattern
                        << std::endl;
                if (s == pattern) {
                    // std::cout << "s: " << s
                    //     << ", p: " << pattern
                    //     << std::endl;
                    return true;
                }
            }
        }
        return false;
    }
};