#include <iostream>
#include <vector>
#include <deque>


typedef std::vector<std::vector<int>> graph_t;
void dfs_traversal(const graph_t& adj,
    int nodeid,
    int visited[],
    std::vector<int>& dfs)
{
    for(const auto neighbor: adj[nodeid])
    {
        if(!visited[neighbor])
        {
            visited[neighbor] = 1;
            dfs.push_back(neighbor);
            dfs_traversal(adj, neighbor, visited, dfs);
        }
    }
}

int main(int argc, const char* argv[])
{
    std::cout << "node cnt: " << std::endl;
    int n=0,m=0;
    std:: cin >> n;
    std::cout << "edge cnt: " << std::endl;
    std:: cin >> m;
    graph_t adj(n+1, std::vector<int>());
    for(int i=0; i<m; i++)
    {
        int u=0,v=0;
        std::cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    std::cout << "Input Graph: " << std::endl;
    int node_id = 0;
    for(const auto& node: adj)
    {
        if(node_id == 0)
        {
            node_id++;
            continue;
        }
        std::cout << node_id << " -> ";
        for(const auto neighbor: node)
        {
            std::cout << neighbor << "|";
        }
        std::cout << std::endl;
        node_id++;
    }
    int start_nodeid = 0;
    std::cout << "dfs start node id: " << std::endl;
    std::cin >> start_nodeid;

    int visited[n+1] = {0};
    // std::deque<int> st; // IMP_NOTE: hidden recursive stack will be used in dfs
    std::vector<int> dfs;
    
    visited[start_nodeid] = 1;
    dfs.push_back(start_nodeid);
    dfs_traversal(adj, start_nodeid, visited, dfs);

    std::cout << "Dfs traversal: "; 
    for(const auto dfs_node: dfs)
    {
        std::cout << dfs_node << "|";
    }
    std::cout << std::endl;

    return 0;
}

// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// node cnt: 
// 8
// edge cnt: 
// 8
// 1 2
// 1 3
// 2 5
// 2 6
// 3 4
// 3 7
// 4 8
// 7 8
// Input Graph: 
// 1 -> 2|3|
// 2 -> 1|5|6|
// 3 -> 1|4|7|
// 4 -> 3|8|
// 5 -> 2|
// 6 -> 2|
// 7 -> 3|8|
// 8 -> 4|7|
// dfs start node id: 
// 1
// Dfs traversal: 1|2|5|6|3|4|8|7|


// Space complexity: O(n for visited vector, n for dfs stored output, n for recursion stack space) 
// = O(3n) = O(n)

// Time complexity
// 1. dfs fn gets called for each node recursively = n or N, 
// 2. and independently, each node has neighbors / degree of node - so, degree of all nodes = 2m or 2E
// Total time complexity = O(N + 2E)

