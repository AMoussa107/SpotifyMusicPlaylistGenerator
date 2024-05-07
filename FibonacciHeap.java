import java.util.*;
public class FibonacciHeap{

public class FibonacciHeapNode{
	private int key; 
	private int degree;
	private boolean mark;
	FibonacciHeapNode parent;
	FibonacciHeapNode child;
	FibonacciHeapNode left;
	FibonacciHeapNode right;
	public FibonacciHeapNode(int key){
		this.key = key;
		this.degree=0;
		this.mark=false;
		this.parent=null;
		this.child=null;
		this.left=this;
		this.right=this;
	}

}
 private FibonacciHeapNode min;
    private int size;
    private FibonacciHeapNode rootList;
    public FibonacciHeap() {
        this.min = null;
        this.size = 0;
        this.rootList = null;
    }

    public boolean isEmpty() {
        return min == null;
    }
   public void insert(int key) {
        FibonacciHeapNode node = new FibonacciHeapNode(key);
        if (min == null) {
            //insert the new element as min
            //doubly link the new element to itself
            //circualr doubly linked
            min = node;
            node.right = min;
            node.left = min;
        }
        else {
            //insert the new element between min and its left
            node.right = min;
            node.left = min.left;
            min.left.right = node;
            min.left = node;
            if (node.key < min.key) {
                min = node;
            }
            
        }
        //increaes size
        size += 1;
    }
    
    public int getMin()
    {
    	return 0;
    }
    public void decreaseKey(FibonacciHeapNode node, int newKey)
    {

    }
    public void merge(FibonacciHeap heap) {
       
    }

    public int extractMin()
    {
    	return 0;
    }
    public void delete(int key)
    {
    	
    }
    public void display() {
        if (min == null) {
            System.out.println("The heap is empty.");
        } else {
            System.out.println("The root nodes of Heap are:");
            FibonacciHeapNode temp = min;
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



