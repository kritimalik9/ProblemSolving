#include <iostream>
#include <vector>
#include <queue>
#include <unordered_set>

// Examples:
// Enter node count: 
// 9
// Enter edge count: 
// 9
// Enter edges: 
// 1 2
// 1 6
// 2 3
// 2 4
// 6 7
// 6 9
// 4 5
// 7 8
// 8 5

// IMP_NOTE:Take REFERENCES here
typedef std::vector<std::vector<int>> graph_t;
void bfs_traversal(const graph_t& adj,
    std::unordered_set<int>& visited,
    std::deque<int>& q)
{
    while(!q.empty())
    {
        auto node = q.front();
        std::cout << node << "|";
        q.pop_front();
        for(const auto& neighbor: adj[node])
        {
            // IMP_NOTE: VERY IMP CONDITION
            if(visited.find(neighbor) == visited.end())
            {
                visited.insert(neighbor);
                q.push_back(neighbor);
            }
        }
    }
    std::cout << std::endl;
}
int main(int argc, const char* argv[])
{
    std::cout << "Enter node count: " << std::endl;
    int n=0,m=0;
    std::cin >> n;
    std::cout << "Enter edge count: " << std::endl;
    std::cin >> m;
    graph_t adj(n+1, std::vector<int>());
    std::cout << "Enter edges: " << std::endl;
    for(int i=0; i<m; i++)
    {
        int u=0,v=0;
        std::cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    std::cout << "Graph: " << std::endl;
    int node_idx = 0;
    for(const auto& node: adj)
    {
        if(node_idx == 0)
        {
            node_idx++;
            continue;
        }
        std::cout << node_idx << "->";
        for(const auto neighbor: node)
        {
            std::cout << neighbor << "|";
        }
        std::cout << std::endl;
        node_idx++;
    }
    int node_id = 0;
    std::cout << "Enter starting node for Bfs traversal: " << std::endl;
    std::cin >> node_id;

    std::deque<int> q;
    // IMP_NOTE:visited can be an array here - *int visited[n+1] = {0}* for indexing from 1
    std::unordered_set<int> visited;
    
    q.push_back(node_id);
    visited.insert(node_id);

    // IMP_NOTE:Can save bfs - "std::vector<int> bfs"
    std::cout << "Bfs: " << std::endl;
    
    bfs_traversal(adj, visited, q);
    return 0;
}
// Space Complexity: O(n for q, n for visited, n for output) = O(n)
// Time complexity :
// A node runs on all it's degrees / neighbors
// Outer while loop - O(n)
// and independently, Inner loop - O(2m) in total for all edges
// So, total time complexity = O(n + 2m) = O(N + 2E)
