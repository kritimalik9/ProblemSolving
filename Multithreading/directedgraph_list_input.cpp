#include <iostream>
#include <vector>
// Intuition: store only the neighbors
// O(E) - Space complexity
int main(int argc, const char* argv[])
{
    int n = 0, m =0; // n - node count, m = edge count
    std::cin >> n >> m;
    std::vector<std::vector<int>> adj(n+1, std::vector<int>()); // assuming node indexing start from 1 here in this example
    for(int i = 0; i < m; i++)
    {
        // int n1 = 0, n2 = 0;
        int u=0,v=0;
        std::cin >> u >> v;
        adj[u].push_back(v);
        // adj[v].push_back(u);
    }
    return 0;
}