Graph Notes:

Path - 
1. contains lot of nodes, and each of them are reachable
2. A node cannot appear twice in a path
3. Adjacent nodes must have a edge in between them

Degree of any vertex in Undirected graph
= number of edges attached to a vertex

Total degree of undirected graph = twice number of edges (because every edge is connected to two nodes)

Degree of any vertex in directed graph
- Indegree - number of incoming edges going inside the vertex
- Outdegree - number of outgoing edges from the vertex

Edge Weight
Any edge can have any weight.
If no wt defined - edge has a unit weight

Graph Input
n = number of nodes
m = number of edges

For a given number of nodes n, there can be any number of edges m.

User Input
First line will hold - n nodes count | m edges count
Starting second line, m lines - which means m rows

Store Graph
1. Matrix form
2. Adjacency list

1. Matrix form 
n nodes count
adj[n+1][n+1] if nodes indexing is starting 1, ex 1,2,3,4,5
if an edge between 4 and 5, Adj[3][4] = 1 and also A[4][3] = 1
Space costly = n^2 space (lot of spaces unused)

2.Adjacency list
Space = O(2E) = O(2m) coz we take every edge twice

Any Traversal Algorithm
int visited[n+1] = {0};
for(i = 1 -> all n nodes)
{
    if(!visited[i])
    {
        Traversal(i);
        Note: 
        these traversal algorithms are designed in such a way that they will traverse ENTIRE connected portions of the graph
    }
}

BFS Traversal
- Traversal changes based on the starting node
- Whenever queue elem is read / popped, that's the time when this elem goes to BFS output struct.

Visited - means it's touched in past - and it's traversed.





