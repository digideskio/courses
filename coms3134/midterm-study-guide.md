#Midterm Study Guide

##SERIES AND PROOFS
- Types of proofs include proof by induction, proof by contradiction, and proof by counterexample

##ALGORITHM ANALYSIS
- Goals
  - Does the algorithm terminate?
  - Does the algorithm solve the problem?
  - What resources does the algorithm use? Time? Space?
- Big-Oh notation
  - T(N) = O(f(N)) if there are positive constants c and n<sub>0</sub> such that T(N) <= cf(N) when N >= n<sub>0</sub>
  - Lower bound
    - T(N) = Ω(f(N)) if there are positive constants c and n<sub>0</sub> such that T(N) >= cf(N) when N >= n<sub>0</sub>
  - Tight bound
    - T(N) and f(N) grow at the same rate
    - T(N) = Θ(f(N)) if T(N) = Ω(f(N) and T(N) = O(f(N))
  - Strict upper bound
    - T(N) = o(f(N)) if for all positive constants c there is some n<sub>0</sub> such that T(N) < cf(N) when N > n<sub>0</sub>
  - Typical growth rates include 2<sup>N</sup> (exponential), N<sup>3</sup> (cubic), N<sup>2</sup> (quadratic), NlogN, N, log<sup>2</sup>(N) (log-square), logN (logarithmic), and c (constant)

##DATA STRUCTURES
- List
  - Array
  - Simple linked list
  - Doubly linked list
- Queue (FIFO)
  - Linked Lists as Queue
  - Circular Array Queue
- Stack (LIFO)
  - Linked Lists as Stack
  - Array Stack
- Tree
  - Binary, N-Ary Tree (fixed number of children)
  - Sibling List Representation
- Search Tree
  - Binary Search Tree
  - N-Ary Search Tree
- Balanced Search Tree
  - AVL Tree
  - B-Tree

##THE LIST ADT
- A list L is a sequence of N objects A<sub>0</sub>, A<sub>1</sub>, A<sub>2</sub>, …, A<sub>N - 1</sub>
- N is the length/size of the list
  - Lists with length N = 0 are called empty lists
- Ai follows A<sub>i - 1</sub> for i > 0
- Ai precedes A<sub>i + 1</sub> for i < N
- Worst-case running times for operations
  - printList: O(N)
  - find(x): O(N)
  - findKth(k): O(1)
  - insert(x, k): O(N)
  - remove(x): O(N)

##SIMPLE LINKED LISTS
- A linked list is a sequences of nodes linked by “next” pointers
- Worst-case running times for operations
  - printList: O(N)
  - find(x): O(N)
  - findKth(k): O(N)
    - In many applications, we can use an iterator instead of findKth(k)
  - insert(x, k): O(N)
  - remove(k): O(N)
  - next(): O(1)

##DOUBLY LINKED LISTS
- A doubly linked list is a sequences of nodes linked by “next” and “prev” pointers, with a head and a tail
- Worst-case running times for operations (in practice, faster, since we only have to search up to half of the list)
  - printList: O(N)
  - find(x): O(N)
  - findKth(k): O(N)
  - insert(x, k): O(N)
  - remove(k): O(N)
  - next(): O(1)

##THE STACK ADT
- LIFO, meaning last in, first out
- Worst-case running times for operations
  - push(x): O(1)
  - pop(): O(1)
  - peek(): O(1)
  - empty(): O(1)
- Applications include method call stacks, evaluations of postfix expressions, conversions from infix to postfix notation, constructions of expression trees from postfix expressions, tree traversal without recursion, implementation of queue, rearrangement of subway cars

##THE QUEUE ADT
- FIFO, meaning first in, first out
- Worst-case running times for operations
  - enqueue(x): O(1)
  - dequeue(): O(1)
  - empty(): O(1)
- Circular array implementation of queue
  - Problem: In naive array implementation, dequeues cause empty space at the beginning of the array
  - Circular array reuses empty space by allowing the back-pointer to wrap to the front

##THE TREE ADT
- A tree T consists of a root node r and zero or more nonempty subtrees (T<sub>1</sub>, T<sub>2</sub>, …, T<sub>N</sub>) each connected by a directed edge from r
- Trees support typical collection operations: size, get, set, add, remove, find...
- Two options for representation
  - Organize siblings as a linked list
    - Problem: Takes longer to find a node from the root
  - Every node has a fixed number of references to children
    - Problem: Only reasonable for small or constant number of children
- M-ary Trees
  - Each node can have M subnodes
  - Height of a complete M-ary tree is log<sub>M</sub>N
  - For binary trees, the number of children is at most two
    - They’re common in data structures and algorithms and convenient to analyze

##BINARY SEARCH TREES
- BST properties
  - For all nodes s in T<sub>l</sub>, s<sub>item</sub> < r<sub>item</sub>
  - For all notes T in T<sub>l</sub>, t<sub>item</sub> < r<sub>item</sub>
- Worst-case running times for operations
  - contains(x): O(height(T))
  - insert(x): O(height(T))
  - findMin(): O(height(T))
  - findMax(): O(height(T))
  - remove(): O(height(T))
- Worst and best case height of a BST with N nodes
  - Worst-case: T does not branch
    - height(T) = N
  - Best case
    - height(T) = logN
- An AVL tree is a BST in which, for every node, the height of the left and right subtree differs by at most 1
  - To maintain balance in an AVL tree, assume the tree is balanced
  - After each insertion, find the lowest node k that violates the balance condition (if any)
  - Perform rotation to re-balance the tree
  - Rotation maintains original height of subtree under k before the insertion, and no further rotations are needed

##B-TREES
- A B-Tree is a M-ary search tree, often used to store large tables on hard disk drives
- Every internal node, except for the root, has ceil(M / 2) <= d <= M children and contains d - 1 values
- All leaves contain ceil(L / 2) <= d <= L values (usually L = M - 1)
- All leaves have the same depth

##THE ORDEREDSET ADT
- An ordered set is a set with a total order defined on the items (all pairs of items are in a “>” or “<“ relation to each other)
- Supported operations include all Set operations and findMin() and findMax()

##THE SET ADT
- A set is a collection of data that does not allow duplicates
- Supported operations include insert(x), remove(x), contains(x), isEmpty(), size(), addAll(s) or union(s), removeAll(s), and retainAll(s) or intersection(s)

##THE MAP ADT
- A map is a collection of (key, value) pairs
- Keys are unique (a set), but values don’t need to be
- Two operations include get(key), which returns the value associated with a key, and put(key, value), which overwrites existing keys

##HASH TABLES
- Define a table (an array) of some length TableSize
- Define a function hash(key) that maps key objects to an integer index in the range 0 … TableSize - 1
  - Assuming hash(key) takes constant time, get and put run in O(1)
- Separate chaining
  - Keep all items with the same hash value on a linked list
  - Slow if the load factor becomes > 1
- When a collision occurs, use probing to put item in an empty cell of the hash table itself
  - Linear probing (f(i) = i)
    - It can always find an alternative cell is there is still space
    - Search becomes slow because of primary clustering
  - Quadratic probing (f(i) = i<sup>2</sup>)
    - No primary clustering
    - If table size is not prime or table is more than half full, it is possible that no empty cell can be found for a key, even if the table still has empty cells
- Double hashing
  - f(i) = i * hash<sub>2</sub>(x)
  - Compute a second hash function to determine a linear offset for this key