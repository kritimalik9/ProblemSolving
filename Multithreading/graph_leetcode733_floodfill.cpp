class Solution {
public:

    void bfs(vector<vector<int>>& image,
        vector<vector<int>>& visited,
        const int color,
        std::deque<std::pair<int, int>>& q,
        int starting_color)
    {
        while(!q.empty())
        {
            auto [row, col] = q.front();
            q.pop_front();
            std::cout << "popped, row: " << row
                << ", col: " << col << std::endl;
            if(row-1 >= 0 && !visited[row-1][col])
            {
                visited[row-1][col] = 1;
                if(starting_color == image[row-1][col])
                {
                    image[row-1][col] = color;
                    q.push_back({row-1, col});
                }
            }
            if(col-1 >= 0 && !visited[row][col-1])
            {
                visited[row][col-1] = 1;
                if(starting_color == image[row][col-1])
                {
                    image[row][col-1] = color;
                    q.push_back({row, col-1});
                }
            }
            if(row+1 < image.size() && !visited[row+1][col])
            {
                visited[row+1][col] = 1;
                if(starting_color == image[row+1][col])
                {
                    image[row+1][col] = color;
                    q.push_back({row+1, col});
                }
            }
            if(col+1 < image[row].size() && !visited[row][col+1])
            {
                visited[row][col+1] = 1;
                if(starting_color == image[row][col+1])
                {
                    image[row][col+1] = color;
                    q.push_back({row, col+1});
                }
            }
        }
    }
    vector<vector<int>> floodFill(vector<vector<int>>& image, int sr, int sc, int color)
    {
        int m=image.size(); // number of nodes here
        // if(!image[sr][sc])
        // {
        //     return image;
        // }

        std::vector<std::vector<int>> visited;
        for(int i=0; i<m; i++)
        {
            int n=image[i].size(); // number of edges here
            visited.push_back(std::vector<int>(n, 0));
            // for(int j=0; j<n; j++)
            // {
            //     if(!image[i][j])
            //     {
            //         visited[i][j] = 1;
            //     };
            // }
        }
        std::deque<pair<int, int>> q;

        visited[sr][sc] = 1;
        int starting_color = image[sr][sc];
        image[sr][sc] = color;
        q.push_back({sr, sc});
        bfs(image, visited, color, q, starting_color);
        return image;
    }
};