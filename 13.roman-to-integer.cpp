/*
 * @lc app=leetcode id=13 lang=cpp
 *
 * [13] Roman to Integer
 */

// @lc code=start
class Solution {
public:
    int romanToInt(string s) {
        uint32_t result = 0;
        std::unordered_map<std::string, uint16_t> romanToIntMap;
        romanToIntMap.insert({"I",  1});
        romanToIntMap.insert({"IV", 4});
        romanToIntMap.insert({"V",  5});
        romanToIntMap.insert({"IX", 9});
        romanToIntMap.insert({"X",  10});
        romanToIntMap.insert({"XL", 40});
        romanToIntMap.insert({"L",  50});
        romanToIntMap.insert({"XC", 90});
        romanToIntMap.insert({"C",  100});
        romanToIntMap.insert({"CD", 400});
        romanToIntMap.insert({"D",  500});
        romanToIntMap.insert({"CM", 900});
        romanToIntMap.insert({"M",  1000});
        for(uint32_t idx = 0; idx < s.length(); idx++)
        {
            auto mapItr = romanToIntMap.find(std::string(s, idx, 1));
            if(mapItr != romanToIntMap.end())
            {
                if(idx + 1 != s.length())
                {
                    auto mapItr_ = romanToIntMap.find(std::string(s, idx, 2));
                    if(mapItr_ != romanToIntMap.end())
                    {
                        result = result + mapItr_->second;
                        idx = idx + 1;
                        continue;
                    }
                }
                result = result + mapItr->second;
            }
        }
        return result;
    }
};
// @lc code=end

