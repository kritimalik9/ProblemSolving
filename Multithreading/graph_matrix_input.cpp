#include <iostream>

int main(int argc, const char* argv[])
{
    int n = 0, m =0; // n - node count, m = edge count
    std::cin >> n >> m;
    int adj[n+1][n+1] = {0}; // assuming node indexing start from 1 here in this example
    for(int e = 0; e < m; e++)
    {
        // int n1 = 0, n2 = 0;
        // std::cin >> n1 >> n2;
        // adj_matrix[n1][n2] = 1;
        // adj_matrix[n2][n1] = 1;
        int u=0,v=0;
        std::cin >> u >> v;
        adj[u][v] = 1;
        adj[v][u] = 1;
    }
    return 0;
}