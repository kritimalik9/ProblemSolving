#include <iostream>
#include <vector>

// IMP_NOTE: Stay focussed while code writing, review side by side
typedef std::vector<std::vector<int>> graph_T;

void dfs_traversal(const graph_T& adj,
    int visited[],
    int node)
{
    for(const auto neighbor: adj[node])
    {
        if(!visited[neighbor])
        {
            visited[neighbor] = 1;
            dfs_traversal(adj, visited, neighbor);
        }
    }
}


int countComponentsImpl(const int n, const graph_T& adj)
{
    int cnt = 0;
    // IMP_NOTE: you'd have to use vector instead of array if not a console input 
    // and it's a changing input instead from external module whenever called
    int visited[n] = {0};

    for(int i=0; i<n; i++)
    {
        if(!visited[i])
        {
            visited[i] = 1;
            dfs_traversal(adj, visited, i);
            cnt++;
        }
    }
    
    std::cout << "Component cnt: " << cnt << std::endl;

    return cnt;
}

int main(int argc, const char* argv[])
{
    std::cout << "Enter node count and edge count: "<< std::endl;
    int n=0, m=0;
    std::cin >> n >> m;
    graph_T adj(n, std::vector<int>());
    for(int i=0; i<m; i++)
    {
        int u=0,v=0;
        std::cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    std::cout << "Input graph: " << std::endl;
    for(int i=0; i<n; i++)
    {
        std::cout << i << "->";
        for(const auto neighbor: adj[i])
        {
            std::cout << neighbor << "|";
        }
        std::cout << std::endl;
    }

    auto cnt = countComponentsImpl(n, adj);

    return 0;
}