#include <iostream>
#include <vector>
// Intuition: store only the neighbors
// O(2E) - Space complexity, 2 implies edge u to v and edge v to u
int main(int argc, const char* argv[])
{
    int n = 0, m =0; // n - node count, m = edge count
    std::cin >> n >> m;
    typedef std::pair<int, int> ValueT;
    std::vector<std::vector<ValueT>> adj(n+1, std::vector<ValueT>()); // assuming node indexing start from 1 here in this example
    for(int i = 0; i < m; i++)
    {
        // int n1 = 0, n2 = 0;
        int u=0,v=0,w=0;
        std::cin >> u >> v;
        adj[u].emplace_back(v, w);
        adj[v].emplace_back(u, w);
    }
    return 0;
}