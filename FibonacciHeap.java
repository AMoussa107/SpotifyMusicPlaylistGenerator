import java.util.*;
public class FibonacciHeap<T extends Comparable<T>>{

public class FibonacciHeapNode{
	private T key; 
	private int degree;
	private boolean mark;
	FibonacciHeapNode parent;
	FibonacciHeapNode child;
	FibonacciHeapNode left;
	FibonacciHeapNode right;
	public FibonacciHeapNode(T key){
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
    public void insert (T key)
    {
    }
    public T getMin()
    {
    	return null;
    }
    public void decreaseKey(FibonacciHeapNode node, T newKey)
    {

    }
    public void merge(FibonacciHeap heap) {
       
    }

    public T extractMin()
    {
    	return null;
    }
    public void delete(T key)
    {
    	
    }


}