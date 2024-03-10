/*
 * @lc app=leetcode id=10 lang=cpp
 *
 * [10] Regular Expression Matching
 */

// @lc code=start
class Solution {
public:
    bool isMatch(string s, string p) {
        // std::unordered_map<uint32_t, bool> pMap;
        std::unordered_map<int32_t, std::unordered_map<int32_t, bool>> spMap;

        spMap.insert({-1, {}});
        spMap[-1].insert({-1, true});
            // std::cout << "spMap[-1][-1] = " << spMap[-1][-1]
            //         << std::endl;

        for(int32_t i=0; i<s.length(); i++)
        {
            spMap.insert({i, {}});
            spMap[i].insert({-1, false});
            // std::cout << "spMap[" << i << "][-1] = " << spMap[i][-1]
            //         << std::endl;
        }

        for(int32_t j=0; j<p.length(); j++)
        {
            spMap[-1].insert({j, false});
            if(p[j] == '*')
            {
                spMap[-1][j] = spMap[-1][j-1];
            }
            // std::cout << "spMap[-1][" << j << "] = " << spMap[-1][j]
            //         << std::endl;
        }

            // std::cout << "spMap starts "
            //         << std::endl;
        for(int32_t i=0; i<s.length(); i++)
        {
            // std::cout << "spMap starts " << i
            //         << std::endl;
            for(int32_t j=0; j<p.length(); j++)
            {
                if(s[i] == p[j] || p[j] == '?')
                {
                    bool prevMatch = false;
                    {
                        prevMatch = spMap[i-1][j-1];
                    }
                    auto spMapItr = spMap.find(i);
                    if(spMapItr != spMap.end())
                    {
                        spMapItr->second.insert({j, prevMatch});
                    }
                    else
                    {
                        spMap.insert({i, {}});
                        spMap[i].insert({j, prevMatch});
                    }
                }
                else
                {
                    if(p[j] == '*')
                    {
                        auto spMapItr = spMap.find(i);
                        bool noChar = false;
                        if(spMapItr != spMap.end())
                        {
                            noChar = spMapItr->second[j-1];
                        }
                        bool oneOrMoreChar = false;
                        if(!noChar)
                        {
                            oneOrMoreChar = spMap[i-1][j];
                        }
                        if(noChar || oneOrMoreChar)
                        {
                            if(spMapItr != spMap.end())
                            {
                                spMapItr->second.insert({j, true});
                            }
                            else
                            {
                                spMap.insert({i, {}});
                                spMap[i].insert({j, true});
                            }
                        }
                    }
                    else
                    {
                        auto spMapItr = spMap.find(i);
                        if(spMapItr != spMap.end())
                        {
                            spMapItr->second.insert({j, false});
                        }
                        else
                        {
                            spMap.insert({i, {}});
                            spMap[i].insert({j, false});
                        }
                    }

                }

            // std::cout << "spMap[" << i << "][" << j << "] = " << spMap[i][j]
            //         << std::endl;
            }
        }
        return spMap[s.length()-1][p.length()-1];
    }
};