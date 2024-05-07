import java.util.*;

//create the node class

class Node {
	public int key;
	public int degree;
	public Node parent;
	public Node left;
	public Node right;
	public Node child;
	public boolean mark;

	//constructor
	public Node() {
		this.key = Integer.MAX_VALUE;
		this.degree = 0;
		this.parent = null;
		this.left = null;
		this.right = null;
		this.child = null;
	}
	//alternative constructor
	public Node(int key_num) {
		this ();
		this.key = key_num;
	}

	//setter and getter methods for all instance variables
	public void set_key(int k) {
		this.key = k;
	}
	public int get_key() {
		return this.key;
	}
	public void set_degree(int priority) {
		this.degree = priority;
	}
	public int get_degree() {
		return this.degree;
	}
	public void set_parent(Node node) {
		this.parent = node;
	}
	public Node get_parent() {
		return this.parent;
	}
	public void set_left(Node node) {
		this.left = node;
	}
	public Node get_left() {
		return this.left;
	}
	public void set_right(Node node) {
		this.right = node;
	}
	public Node get_right() {
		return this.right;
	}
	public void set_child(Node node) {
		this.child = node;
	}
	public Node get_child() {
		return this.child;
	}

	public void set_mark(boolean m) {
		this.mark = m;
	}
	public boolean get_mark() {
		return this.mark;
	}
 

}

public class FibonacciHeap{
	//instance variables
	private int size;
	private Node min;
	private boolean trace;
	private Node found;
	private Node root_list;

	//setters and getters for instance variables
	public void set_trace(boolean t) {
		this.trace = t;
	}
	public boolean get_trace() {
		return this.trace;
	}

	//create new fibonacci heap
	public static FibonacciHeap new_heap() {
		return new FibonacciHeap();
	}
	//constructor method
	public FibonacciHeap() {
		this.min = null;
		this.root_list = null;
		this.size = 0;
		this.trace = false;
	}
	public boolean isEmpty() {
		return min == null;
	}
	//insert new node in the heap
	
	public void insert(Node node) {
		if (min == null) {
			//insert the new element as min
			//doubly link the new element to itself
			//circualr doubly linked
			min = node;
			node.set_right(min);
			node.set_left(min);
		}
		else {
			//insert the new element between min and its left
			node.set_right(min);
			node.set_left(min.get_left());
			min.get_left().set_right(node);
			min.set_left(node);
			if (node.get_key() < min.get_key()) {
				min = node;
			}
			
		}
		//increaes size
		size += 1;
	}
	

	//alternative insret with integer
	public void insert(int i) {
		insert(new Node(i));
	}
	

	public void display() {
        if (min == null) {
            System.out.println("The heap is empty.");
        } else {
            System.out.println("The root nodes of Heap are:");
            Node temp = min;
            do {
                System.out.print(temp.key + " ");
                temp = temp.right;
                if (temp != min) {
                    System.out.print("-->");
                }
            } while (temp != min);
            System.out.println("\nThe heap has " + size + " nodes.");
        }
    }

	public static void main(String[] args) {
		FibonacciHeap heap = new FibonacciHeap();
		heap.insert(4);
		heap.insert(3);
		heap.insert(7);
		heap.insert(5);
		heap.insert(2);
		heap.insert(1);
		heap.insert(10);

		heap.display();

	}



}