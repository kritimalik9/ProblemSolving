class Solution {
    
    bool canCross_recursive(vector<int>& stones,
        int pos,
        int k,
        int i)
    {
        if(i >= stones.size())
        {
            return false;
        }
        if((i+1 == stones.size())
           && (pos == stones[i]))
        {
            return true;
        }
        if(pos == 0)
        {
            return canCross_recursive(stones, pos+1, 1, i);
        }
        if(pos < stones[i])
        {
            return false;
        }
        if(pos > stones[i])
        {
            return canCross_recursive(stones, pos, k, i+1);
        }
        int k_minus_1 = k-1;
        int k_plus_1 = k+1;
        auto option1 = canCross_recursive(stones, pos+k_minus_1, k_minus_1, i+1);
        auto option2 = canCross_recursive(stones, pos+k, k, i+1);
        auto option3 = canCross_recursive(stones, pos+k_plus_1, k_plus_1, i+1);

        return (option1 || option2 || option3);
    }
    
    // bool canCross_topdown(vector<int>& stones,
    //     int pos,
    //     int k,
    //     int i,
    //     std::vector<std::optional<bool>>& dp)
    // {
    //     std::cout << "Start Fetched pos: " << pos
    //         << ", has_value: " << dp[pos].has_value()
    //         << ", value: " << (dp[pos].has_value() ? dp[pos].value() : -1)
    //         << std::endl;
    bool canCross_topdown(vector<int>& stones,
        int pos,
        int k,
        int i,
        std::unordered_map<int, unordered_map<int, bool>>& dp)
    {
        // std::cout << "Start Fetched pos: " << pos
        //     << ", has_value: " << dp[pos].has_value()
        //     << ", value: " << (dp[pos].has_value() ? dp[pos].value() : -1)
        //     << std::endl;
        if(i >= stones.size())
        {
            return false;
        }
        // std::cout << "DBG1" << std::endl;
        if((i+1 == stones.size())
           && (pos == stones[i]))
        {
            return true;
        }
        // std::cout << "DBG2" << std::endl;
        if(pos == 0)
        {
            return canCross_topdown(stones, pos+1, 1, i, dp);
        }
        // std::cout << "DBG3" << std::endl;
        auto dp_pos_itr = dp.find(pos);
        if(dp_pos_itr != dp.end())
        {
            auto& pos_map = dp_pos_itr->second;
            auto pos_k_itr = pos_map.find(k);
            if(pos_k_itr != pos_map.end())
            {
                std::cout << "Fetched pos: " << pos
                    << ", k: " << k
                    << ", value: " << dp[pos][k]
                    << std::endl;
                return dp[pos][k];
            }
        }
        // std::cout << "DBG4" << std::endl;
        if(pos < stones[i])
        {
            return false;
            // if(dp.find(pos) != dp.end())
            // {
            //     dp[pos].insert({k, false});
            // }
            // else
            // {
            //     std::unordered_map<int, bool> k_map;
            //     k_map.emplace(k, false);
            //     dp.emplace(pos, k_map);
            // }
            // std::cout << "End0 Fetched pos: " << pos
            //     << ", k: " << k
            //     << ", value: " << dp[pos][k]
            //     << std::endl;
            // return dp[pos][k];
        }
        // std::cout << "DBG5" << std::endl;
        if(pos > stones[i])
        {
            return canCross_topdown(stones, pos, k, i+1, dp);
        }
        // std::cout << "DBG6" << std::endl;
        int k_minus_1 = k-1;
        int k_plus_1 = k+1;
        auto option1 = canCross_topdown(stones, pos+k_minus_1, k_minus_1, i+1, dp);
        auto option2 = canCross_topdown(stones, pos+k, k, i+1, dp);
        auto option3 = canCross_topdown(stones, pos+k_plus_1, k_plus_1, i+1, dp);

        auto result = (option1 || option2 || option3);
        if(dp.find(pos) != dp.end())
        {
            dp[pos].insert({k, result});
        }
        else
        {
            std::unordered_map<int, bool> k_map;
            k_map.emplace(k, result);
            dp.emplace(pos, k_map);
        }
        // std::cout << "End Fetched pos: " << pos
        //     << ", k: " << k
        //     << ", value: " << dp[pos][k]
        //     << std::endl;
        return dp[pos][k];
    }
public:
    bool canCross(vector<int>& stones) {

        int pos = 0; // current frog position
        int k = 0; // last jump
        int i = 0; // stones array index

        // return canCross_recursive(stones, pos, k, i);

        // std::vector<std::optional<bool>> dp(2^31-1, nullopt);
        std::unordered_map<int, unordered_map<int, bool>> dp;
        // bool dp[2^31-1] = {false};
        return canCross_topdown(stones, pos, k, i, dp);
        
    }
};