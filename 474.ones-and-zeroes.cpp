/*
 * @lc app=leetcode id=474 lang=cpp
 *
 * [474] Ones and Zeroes
 */

// @lc code=start
class Solution {
public:
    int findMaxForm(vector<string>& strs, int m, int n) {

#if 0
// Output
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# str = 1
# str = 0
# is_m_min = 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 0, unsorted n= 1 -> Don't include, OR Include this string -  DP problem.
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 1, unsorted n= 0
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
#MID1 max_so_far= 59
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 0, unsorted n= 1
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
#MID1 max_so_far= 59
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# unsorted m= 0, unsorted n= 1
# unsorted m= 1, unsorted n= 0
# max_so_far= 59
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
#MID2 max_so_far= 59
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
#MID2 max_so_far= 59
# sorted m= 0, sorted n= 1
# sorted m= 0, sorted n= 1
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
#MID2 max_so_far= 59
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
#MID2 max_so_far= 59
# sorted m= 1, sorted n= 0
# sorted m= 1, sorted n= 0
# max_so_far= 59
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
#MID3 max_so_far= 59
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
#MID3 max_so_far= 59
# sortedxx m= 1, sorted n= 0
# sortedxx m= 1, sorted n= 0
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
#MID3 max_so_far= 59
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
#MID3 max_so_far= 59
# sortedxx m= 0, sorted n= 1
# sortedxx m= 0, sorted n= 1
# max_so_far= 59
#endif
////////////////////////
        
    }
};
// @lc code=end

