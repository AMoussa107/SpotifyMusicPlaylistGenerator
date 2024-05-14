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
| Operation | Description     |
|----|----------|
|insert | We insert a new node to the heap by adding it to the root list|
| min | Returns the minimum value from the root list | 
| union  | Puts the root lists of two Fibonacci heaps together and updates pointers to maintain heap properties | 
| extractMin  | Removes and returns the minimum node from the heap the consolidates the remaining trees to maintain heap structure|
| decreaseKey | Updates the priority value (key) of a certain node and performs necessary cuts and merges to maintain the min-heap properties within the tree |
|delete| Decreases the key of the specified node to Negative infinity and then extracts Minimum value to remove it from the Heap and maintain structure|

## Fibonacci Heap: Analysis
The Fibonacci Heap structure does a great job of minimizing the complexity of the number operations needed to implement and use a priority queue. This is achieved through laziness that leads to amortized constant-time complexity. This structure only does work when it's necessary, and does it in a way that would make future operations easier. The insert method is a great example of what we call a "lazy" operation, wher instead of trying to add a new node to one of the existing heap-trees and placing it correctly, it just adds this new node to the root list creating a new heap-tree of order 0. The time complexity of our Fibonacci Heap operations is listed in the table below:
| Operation | Fibonacci Heap  (amortized)   | Binary Heap|
|----|----------| --------- |
|insert | O(1) | O(log(n))|
| min | O(1) | O(1)|
| union  | O(1) | O(n)|
| extractMin  |O(log(n)| O(log(n))|
| decreaseKey | O(1) | O(log(n))|
|delete|O(log(n)) | O(log(n))|



## Application
We are making use of a priority queue ADT (implemented with the Fibonacci Heap) to generate (queue) songs from our data set into a playlist. The key of each song node that's being added to the heap is determined through input from the user of their preferences and then scoring each song based on how well its attributes match the user preferred attributes. The input from the user is taken through a graphical interface with sliders, drop down menus, checkboxes, and textboxes, and then after submitting their preferences, we extract the first n minimum values from the heap where n is the number of songs the user wants in the playlist. This is how the interface looks like: 

<img width="796" alt="Screenshot 2024-05-14 at 1 35 25 AM" src="https://github.com/AMoussa107/Fibonacci-Heap-CS136-Final-Project/assets/150184302/57a48f31-4082-4bc5-995a-0b0ce1bd24cb">

Each slider in the interface can be set to a value between 0 and 100. The user has 100 priority points to distribute among different attributes and how important they are to the generation of their playlist. These priorities are taken into consideration when scoring the songs. 



