#Final Study Guide

##PRIORITY QUEUES
- A collection Q of comparable elements that supports insert(x) and deleteMin()/deleteMax()
- Standard implementation is a heap
  - Each operation is O(logN) time
  - A heap can be constructed from an unordered array in O(N)

## HEAPS
- A heap is a binary tree stored in an array (in layer order)
- Traversal is easy—leftChild(i) = 2i, rightChild(i) = 2i + 1, parent(i) = floor(i/2)
- Insert(x)
  - Attempt to insert x in the next possible position (empty leftmost leaf on bottom layer)
  - If the heap order property is violated, percolate the value up
- DeleteMin()
  - Delete the root
  - Try to place the last cell (rightmost filled leaf on the bottom layer) into the root
  - If the heap order property is violated, percolate the value down
- BuildHeap algorithm is a bottom-up way to build a heap
  - Starts at floor(N/2) if N is the number of nodes in the binary tree and goes through each parent/children group to percolate down
  - Running time is O(N)

##SORTING ALGORITHMS
- Insertion Sort
  - Comparison-based
  - O(N) at best, O(N<sup>2</sup>) at worst, O(1) space
  - Stable
- Heap Sort
  - Comparison-based
  - O(NlogN) at best and worst, O(1) space
  - Unstable
- Merge Sort
  - Comparison-based
  - O(NlogN) at best and worst, O(N) space
  - Stable
- Quick Sort
  - Comparison-based
  - O(NlogN) at best, O(N<sup>2</sup>) at worst, O(N) space
  - Unstable
- Radix Sort
  - Not comparison-based

##INSERTION SORT
- Perform N - 1 passes through the array
- Assume previous elements of array are already sorted when at position p
- Take element x at position p and repeatedly swap with its left neighbor until it’s in the right position

##HEAP SORT
- Convert an unordered array into a heap in O(N) time
- Perform N deleteMin() operations (each O(logN) in running time) to retrieve the elements in sorted order

##MERGE SORT
- Split the array in half and recursively sort each half
- Merge the two sorted lists
- Example of a divide-and-conquer algorithm

##QUICK SORT
- Pick any pivot element v (i.e., median of three)
- Partition the array into elements less than and greater than v
- Recursively sort the partitions, then concatenate them
- Another divide-and-conquer algorithm

##SELECTION PROBLEM
- Selects the k-smallest element from an unordered array
- Related to sorting (just sort the input first)
- It can do better by keeping track of k-smallest elements on a heap
- Quick Select achieve O(N) best-case running time

##BUCKET SORT
- Assume we know there are M possible values
- Keep an array of length M and use its indexes to keep track of how many times that value appears in the input
- Then iterate through the array and write count[i] copies of i to a new array
- Runs in O(N + M) time

##RADIX SORT
- Assume M contains all base b numbers up to b<sup>p</sup> - 1
- Do p passes over the data, using Bucket Sort for each digit starting from the least significant digit
- Runs in O(p(N + b))

##GRAPHS
- A graph is a pair of two sets
  - V is the set of vertices or nodes
  - E is the set of edges
    - Each edge is a pair (v, w) describing two vertices it connects
- Graphs can be directed or undirected
  - In directed graphs, edge pairs are ordered
- Edges often have a weight or cost associated with them (weighted graphs)
- Can be represented as an N x N adjacency matrix with O(V<sup>2</sup>) space or an adjacency list with O(V + E) space
- Undirected graphs can be modeled in an adjacency list by listing two directed edges for each edge

##GRAPH SEARCH
- In every step of the search, we maintain the part of the graph already explored, the part of the graph not yet explored, and a data structure of next edges adjacent to the explored graph
- DFS uses a stack, BFS uses a queue, Dijkstra’s uses a priority queue, and topological sort is BFS with constraints on the queue’s items

##DEPTH-FIRST SEARCH
- Use a stack and a set visited
- Push s to the stack
- While stack is not empty, pop a value from the stack
- If the value is not in visited, add it to visited and push all adjacent vertices to the stack
- Running time with adjacency lists is O(V + E)

##DEPTH-FIRST SPANNING TREE
- The path used by DFS can be shown as a directed spanning tree, adding a tree edge for every step taking by DFS and a back edge for every skipped edge

##BREADTH-FIRST SEARCH
- Use a queue and a set visited
- Enqueue s and add s to visited
- While the queue is not empty, dequeue a value from the queue
- For each vertex adjacent to that value, if the vertex is not in visited, add it, and then enqueue(v)
- BFS can be used to find the shortest path (unweighted)

##TOPOLOGICAL SORT
- Computes the earliest completion time for overlapping tasks
- Only add vertices to the queue when their indegree becomes 0
- While the queue is not empty, dequeue a vertex, print it, and decrement the indegree of its adjacent nodes; update the earliest completion time for each adjacent node

##DIJIKSTRA’S ALGORITHM
- Use a priority queue q
- For all v, set a cost to infinity and set v.visited to false
- Set s.cost to 0 and s.visited to true, then enqueue
- While q is not empty, for each vertex adjacent to u, if not v.visited, if u.cost + cost(u, v) < v.cost, set that to v.cost and set v.prev (the back pointer) to u; then, insert v to q
- There are E deleteMin() operations, and each insert(x) and getMin() operation takes O(logE) time, so the running time is O(ElogE)

##SPANNING TREES
- Given an undirected, connected graph G, a spanning tree is a tree that connects all vertices in the graph
- Given a weighted undirected graph G, a minimum spanning tree is a spanning tree with the minimum sum of edge weights

##EULER CIRCUIT/PATH
- For an Euler circuit to exist in a graph, all vertices need to have an even degree
- For an Euler path to exist in a graph, exactly 0 or 2 vertices need to have an odd degree