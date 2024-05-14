package music;

import java.util.*;

public interface PriorityQueue{

	// Returns true if the heap is empty
	public abstract boolean isEmpty();

	// Inserts a new node with the given key into the heap
	public void insert(int key);

	// Merges the heap to a another Fibonnaci heap
    public FibonacciHeap merge(FibonacciHeap newHeap);

    //Find and return the node with smallest key
    public Node extractMin();

    //Decrease the key of a node and update the heap as necessary
    public void decreaseKey(Node node1, int key);

    // Deletes a node from the heap 
    public Node delete(Node node);


}