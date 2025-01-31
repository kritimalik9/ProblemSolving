#include <bits/stdc++.h>
using namespace std;
int countPaths(vector<vector<int>>& grid, int i, int j)
{
    int r=grid.size();
    int c=grid[0].size();
    if(i==r-1 && j==c-1)
    {
        return 1;
    }
    int di[] = {1, 0};
    int dj[] = {0, 1};
    int paths = 0;
    for(int k=0; k<2; k++)
    {
        int ni = i + di[k];
        int nj = j + dj[k];
        if(ni < r && nj < c && grid[ni][nj] <= grid[i][j])
        {
            paths = paths + countPaths(grid, ni, nj);
        }
    }
    return paths;
}
int main(int argc, const char** argv)
{
    int r, c;
    cout << "Enter r & c:";
    cin >> r >> c;
    vector<vector<int>> grid(r, vector<int>(c));
    for(int i=0; i<r; i++)
    {
        for(int j=0; j<c; j++)
        {
            cin >> grid[i][j]; 
        }
    }
    
    int cnt = countPaths(grid, 0, 0);
    cout << "Number of paths: " << cnt << endl;
    return 0;
}