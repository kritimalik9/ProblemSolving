/*
 * @lc app=leetcode id=11 lang=cpp
 *
 * [11] Container With Most Water
 */
#include <cstdlib>

// @lc code=start
class Solution {
public:
    typedef std::pair<uint32_t, uint32_t> MinHtToIdxPair;
    typedef std::vector<MinHtToIdxPair> MinHtToIdxVec;
    static bool sortFn(const MinHtToIdxPair& val1,
        const MinHtToIdxPair& val2)
    {
        if(val1.first == val2.first)
        {
            return (val1.second < val2.first);
        }
        return val1.first < val2.first;
    }
    int maxArea(vector<int>& height) {

        uint32_t result = 0;
        MinHtToIdxVec minHtToIdxVec;
        uint32_t htIdx = 0;
        for(auto& ht: height)
        {
            minHtToIdxVec.emplace_back(ht, htIdx);
            htIdx++;
        }
        std::sort(minHtToIdxVec.begin(), minHtToIdxVec.end(), sortFn);

        uint32_t loopId = 0;
        uint32_t startIdx = 0;
        uint32_t endIdx = height.size() - 1;
        std::unordered_set<uint32_t> idxDone;
        for(const auto& minHtIdxPair: minHtToIdxVec)
        {
            // std::cout << "loop#" << loopId
            //     << ", first = " << minHtIdxPair.first
            //     << ", second = " << minHtIdxPair.second
            //     << std::endl;
            uint32_t minHt = minHtIdxPair.first;
            uint32_t minHtIdx = minHtIdxPair.second;
            while(startIdx < minHtIdx)
            {
                if(idxDone.find(startIdx) != idxDone.end())
                {
                    startIdx++;
                    continue;
                }
                result = std::max(result, (minHt * (minHtIdx - startIdx)));
                if(height[startIdx] >= minHt)
                {
                    break;
                }
                startIdx++;
            }
            while(endIdx > minHtIdx)
            {
                if(idxDone.find(endIdx) != idxDone.end())
                {
                    endIdx--;
                    continue;
                }
                result = std::max(result, (minHt * (endIdx - minHtIdx)));
                if(height[endIdx] >= minHt)
                {
                    break;
                }
                endIdx--;
            }
            idxDone.insert(minHtIdx);
            loopId++;
        }
        return result;
    }
    int maxAreaSlow(vector<int>& height) {

        uint32_t result = 0;

        for(uint32_t x2=1; x2<height.size(); x2++)
        {
            for(uint32_t x1=0; x1<x2; x1++)
            {
                auto area = (x2-x1) * std::min(height[x2], height[x1]);
                result = std::max(area, result);
            }
        }
        return result;
    }
};
// @lc code=end

