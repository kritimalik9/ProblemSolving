/*
 * @lc app=leetcode id=12 lang=cpp
 *
 * [12] Integer to Roman
 */

// @lc code=start
class Solution {
public:
    static bool lookupCmp(const std::pair<uint16_t, std::string>& val1,
        const std::pair<uint16_t, std::string>& val2)
    {
        if(val1.first <= val2.first)
        {
            return true;
        }
        return false;
    }
    string intToRoman(int num) {
        std::string result{};
        std::vector<std::pair<uint16_t, std::string>> lookup;
        lookup.emplace_back(1,    "I");
        lookup.emplace_back(4,    "IV");
        lookup.emplace_back(5,    "V");
        lookup.emplace_back(9,    "IX");
        lookup.emplace_back(10,   "X");
        lookup.emplace_back(40,   "XL");
        lookup.emplace_back(50,   "L");
        lookup.emplace_back(90,   "XC");
        lookup.emplace_back(100,  "C");
        lookup.emplace_back(400,  "CD");
        lookup.emplace_back(500,  "D");
        lookup.emplace_back(900,  "CM");
        lookup.emplace_back(1000, "M");
        std::sort(lookup.begin(), lookup.end(), lookupCmp);
        intToRomanImpl(num, lookup, result);
        return result;
    }
    void intToRomanImpl(int num,
                        std::vector<std::pair<uint16_t, std::string>>& lookup,
                        std::string& result)
    {
        std::pair<uint16_t, std::string> toFind{num, ""};
        auto closestValItr = std::lower_bound(lookup.begin(), lookup.end(), toFind, lookupCmp);
        auto closestInt = (*(--closestValItr)).first;
        auto quotient = num / closestInt;
        auto remainder = num % closestInt;
        auto closestRoman = (*closestValItr).second;
        uint32_t cnt = 0;
        std::cout << "closestInt: " << closestInt
                  << ", quotient: " << quotient
                  << ", remainder: " << remainder
                  << ", closestRoman: " << closestRoman
                  << ", result: " << result
                  << std::endl;
        while(cnt != quotient)
        {
            result = result + closestRoman;
        // std::cout << "closestInt: " << closestInt
        //           << ", quotient: " << quotient
        //           << ", remainder: " << remainder
        //           << ", closestRoman: " << closestRoman
        //           << ", result: " << result
        //           << std::endl;
            cnt++;
        }
        if(remainder != 0)
        {
            intToRomanImpl(remainder, lookup, result);
        }
    }
};
// @lc code=end

