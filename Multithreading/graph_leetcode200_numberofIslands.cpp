 
// Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

// An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
// Example 1:

// Input: grid = [
//   ["1","1","1","1","0"],
//   ["1","1","0","1","0"],
//   ["1","1","0","0","0"],
//   ["0","0","0","0","0"]
// ]
// Output: 1
// Example 2:

// Input: grid = [
//   ["1","1","0","0","0"],
//   ["1","1","0","0","0"],
//   ["0","0","1","0","0"],
//   ["0","0","0","1","1"]
// ]
// Output: 3
class Solution {

    void dfs(const std::vector<vector<char>>& grid,
        std::vector<std::vector<int>>& visited,
        std::pair<int, int> node)
    {
        auto [i, j] = node;
        // std::cout << "visited on node, i: " << i
        //     << ", j: " << j
        //     << std::endl;
        // if(i-1 >= 0 && j-1 >= 0)
        // {
        //     if(!visited[i-1][j-1])
        //     {
        //         visited[i-1][j-1] = 1;
        //         dfs(grid, visited, {i-1, j-1});
        //     }
        // }
        if(i-1 >= 0)
        {
            if(!visited[i-1][j])
            {
                visited[i-1][j] = 1;
                dfs(grid, visited, {i-1, j});
            }
        }
        // if(i-1 >= 0 && (j+1 < grid[i-1].size()))
        // {
        //     if(!visited[i-1][j+1])
        //     {
        //         visited[i-1][j+1] = 1;
        //         dfs(grid, visited, {i-1, j+1});
        //     }
        // }
        if(j+1 < grid[i].size())
        {
            if(!visited[i][j+1])
            {
                visited[i][j+1] = 1;
                dfs(grid, visited, {i, j+1});
            }
        }
        // if((i+1 < grid.size()) && (j+1 < grid[i+1].size()))
        // {
        //     if(!visited[i+1][j+1])
        //     {
        //         visited[i+1][j+1] = 1;
        //         dfs(grid, visited, {i+1, j+1});
        //     }
        // }
        if(i+1 < grid.size())
        {
            if(!visited[i+1][j])
            {
                visited[i+1][j] = 1;
                dfs(grid, visited, {i+1, j});
            }
        }
        // if((i+1 < grid.size()) && (j-1 >= 0))
        // {
        //     if(!visited[i+1][j-1])
        //     {
        //         visited[i+1][j-1] = 1;
        //         dfs(grid, visited, {i+1, j-1});
        //     }
        // }
        if(j-1 >= 0)
        {
            if(!visited[i][j-1])
            {
                visited[i][j-1] = 1;
                dfs(grid, visited, {i, j-1});
            }
        }
    }
public:
    int numIslands(vector<vector<char>>& grid) {
        int cnt = 0;
        int n = grid.size();
        std::vector<std::vector<int>> visited;
        for(int i=0; i<n; i++) // num of nodes
        {
            int m = grid[i].size();
            visited.push_back(std::vector(m, 0));
            for(int j=0; j<m; j++) // make 1 for water in visited 
            {
                if(grid[i][j] == '0')
                {
                    // std::cout << "set visited for i: " << i
                    //     << ", j:" << j << std::endl;
                    visited[i][j] = 1;
                }
            }
        }
        for(int i=0; i<n; i++) // num of nodes
        {
            int m = grid[i].size();
            for(int j=0; j<m; j++)
            {
                if(!visited[i][j])
                {
                    dfs(grid, visited, {i, j});
                    cnt++;
                }
            }
        }
        return cnt;
    }
};
// // if island mean no land interconnected- all nodes which are horizontal, vertical, ALSO diagonal connected, form 1 Island
//         for(int delta_i=-1; delta_i<=1; delta_i++)
//         {
//             for(int delta_j=-1; delta_j<=1; delta_j++)
//             {
//                 int i_ = i+delta_i;
//                 int j_ = j+delta_j;
//                 if(i_ >= 0
//                    && i_ < grid.size()
//                    && j_ >= 0
//                    && j_ < grid[i_].size()
//                    && !visited[i_][j_])
//                 {
//                     visited[i_][j_] = 1;
//                     dfs(grid, visited, {i_, j_});
//                 }
//             }
//         }
// // Space complexity
// O(n^2 for visited and n^2 for queue/stack)
// // Time complexity of this commented out case - just above mentioned one case PLUS when visited is not set to 1 for water at init - this is video case complexity
// O(n^2 for outermost loop + 9 * n^2 for dfs)