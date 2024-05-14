# Fibonacci-Heap-CS136-Final-Project
![alt text](https://github.com/AMoussa107/Fibonacci-Heap-CS136-Final-Project/assets/150184302/08cb2218-5c92-48bb-b587-f6b5216890c1)

## Basic Overview
This program is a music playlist generator that will give you a list of songs that best match your musical preferences on Spotify through efficient priority queue implementation that uses the Fibonacci Heap Data Structure.

## Fibonacci Heap: An Overview
A Fibonacci Heap is a data structure typically used for priority queue operations. Fibonacci Heaps are a collection of min-Heap-ordered trees, a tree that maintains the min-heap property. The min-heap property means that for every child node c and parent node p, c.key ≥ p.key. This collection has the following properties: 
- Each node in the tree has four pointers: the parent node, a child, the left node, the right node.
- All siblings are linked together through a doubly linked list.
- The root list of the collection of trees is connected through a doubly linked list.
- Each node has a degree value (number of children), and a boolean value marked (always false unless the node loses a child)
- We also keep a pointer to the minimum value in the root list.

The picture below describes the above structure: 
![alt text](https://github.com/AMoussa107/Fibonacci-Heap-CS136-Final-Project/assets/150184302/d8319681-39d8-451d-83a1-677d6bf41b1f)

## Fibonacci Heap: Operations
Here's a list of elementary operations implemented in our version of the Fibonacci Heap:
**Operation** | **Description**
Insert | We insert a new node to the heap by adding it to the root list


## Data
