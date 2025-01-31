#include <iostream>
#include <vector>
#include <deque>

typedef std::vector<std::vector<int>> graph_T;
void bfs_traversal(const graph_T& adj,
    int visited[],
    std::deque<int>& q)
{
    while(!q.empty())
    {
        auto node = q.back();
        q.pop_back();
        for(const auto neighbor: adj[node])
        {
            if(!visited[neighbor])
            {
                // std::cout << "Entered for bfs on: " << neighbor << std::endl;
                visited[neighbor] = 1;
                q.push_front(neighbor);
            }
        }
    }
}
int findProvinceCnt(const graph_T& adj)
{
    int visited[adj.size()] = {0};
    int cnt = 0;
    std::deque<int> q;
    for(int node=1; node<=adj.size(); node++)
    {
        if(!visited[node])
        {
            visited[node] = 1;
            q.push_front(node);
            // std::cout << "Entered for node: " << node << std::endl;
            bfs_traversal(adj, visited, q);
            cnt++;
        }
    }
    std::cout << "Province Cnt: " << cnt << std::endl;
    return cnt;
}

int main(int argc, const char* argv[])
{
    int n=0, m=0;
    std::cout << "First enter node count and then enter edge count: " << std::endl;
    std::cin >> n >> m;
    graph_T adj(n+1, std::vector<int>());
    for(int i=0; i<m; i++)
    {
        int u=0,v=0;
        std::cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    std::cout << "Input graph: " << std::endl;
    for(int i=1; i<=n; i++)
    {
        std::cout << i << "|";
        for(const auto neighbor: adj[i])
        {
            std::cout << neighbor << "|";
        }
        std::cout << std::endl;
    }

    return findProvinceCnt(adj);
}

// [kriti.malik@ds1.deepranalytics.com@ip-10-200-9-183 ec2]$ ./a.out 
// First enter node count and then enter edge count: 
// 8 5
// 1 2
// 2 3
// 4 5
// 5 6
// 7 8
// Input graph: 
// 1|2|
// 2|1|3|
// 3|2|
// 4|5|
// 5|4|6|
// 6|5|
// 7|8|
// 8|7|
// Entered for node: 1
// Entered for bfs on: 2
// Entered for bfs on: 3
// Entered for node: 4
// Entered for bfs on: 5
// Entered for bfs on: 6
// Entered for node: 7
// Entered for bfs on: 8
// Province Cnt: 3

// Space Complexity:
// O(n for recursion in dfs or n for queue in bfs, and n for visited) = O(2n) or O(2N)
// Time Complexity
// Outer cnt loop = O(n) or O(N)
// Inner dfs = O(n for recursion + 2E for total of degree of each node)
// Total time complexity = O(N) + O(N + 2E)
// Depending on number of neighbor nodes for any given node which can vary, time complexity can vary