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
    public void insert(int key) {
        Node node = new Node(key);
        node.left = node;
        node.right = node;

        mergeRootList(node);

        if (min == null || node.key < min.key) {
            min = node;
        }
        size++;
    }
    
    public void mergeRootList(Node node) {
        if (root_list == null) {
            this.root_list = node;
            node.right = node;
            node.left = node;
        }
        else {
            node.right = this.root_list;
            node.left = this.root_list.left;
            this.root_list.left.right = node;
            this.root_list.left = node;
        }
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
                    System.out.print("--> ");
                }
            } while (temp != min);
            System.out.println("\nThe heap has " + size + " nodes.");
        }
    }

    private FibonacciHeap union(FibonacciHeap newHeap) {
        //check if newHeap is empty
        if (newHeap == null || newHeap.root_list == null) {
            return this;
        }
        //check if current root is empty
        if (this.root_list == null) {
            this.min = newHeap.min;
            this.root_list = newHeap.root_list;
            this.size = newHeap.size;
            return newHeap;
        }
        Node lastThis = this.root_list.left;
        Node lastNew = newHeap.root_list.left;

        lastThis.right = newHeap.root_list;
        newHeap.root_list.left = lastThis;
        lastNew.right = this.root_list;
        this.root_list.left = lastNew;

        //find the min of current and new heap
        if (this.min.key > newHeap.min.key) {
            this.min = newHeap.min;
        }
        //adjust the union heap's size
        this.size += newHeap.size;
        //return the united heap
        return this;
    }

    public Node extractMin() {
        Node node = this.min;
        if (node != null) {
            if (node.child != null) {
                ArrayList<Node> children = iterate(node.child);
                for (Node ch : children) {
                    mergeRootList(ch);
                    ch.parent = null;
                }
            }
            remove_from_rootList(node);
            //check if min was the only root node in the list
            if (node == node.right) {
                this.min = null;
                this.root_list = null;
            }
            else {
                //temoporarily set min to the next node
                this.min = node.right;
                //rearrange the heap
                //make sure no two roots have the same degree
                //find and assign the new min
                consolidate();
            }
            this.size--;
        }
        return node;
    }
    private ArrayList<Node> iterate(Node first) {
        ArrayList<Node> nodeList = new ArrayList<Node>();
        Node check = first;
        Node stop = first;
        boolean flag = false;

        while (true) {
            if (check == stop && flag) {
                break;
            }
            else if (check == stop) {
                flag = true;
            }
            nodeList.add(check);
            check = check.right;
        }
        return nodeList;
    }
    private void consolidate() {
        //int num = (int) Math.ceil(Math.log(this.size)/Math.log(2) * 2);
        int num = (int) Math.ceil(Math.log(this.size) / Math.log(2));
        Node[] arr = new Node[num];
        
        ArrayList<Node> nodes = iterate(this.root_list);
        for (int i = 0; i < nodes.size(); i++) {
            Node node1 = nodes.get(i);
            int deg = node1.degree;
            while (arr[deg] != null) {
                Node node2 = arr[deg];
                if (node1.key > node2.key) {
                    Node temp = node1;
                    node1 = node2;
                    node2 = temp;
                }
                heap_link(node2, node1);
                arr[deg] = null;
                deg++;
            }
            arr[deg] = node1;
        }
        //Temporarily reset min until finding new min
        min = null;
        for (int j = 0; j < num; j++) {
            if (arr[j] != null) {
                if (min == null || arr[j].key < this.min.key) {
                    this.min = arr[j];  
                }
            }
        }

    }
    private void heap_link(Node node1, Node node2) {
        this.remove_from_rootList(node1);
        node1.left = node1;
        node1.right = node1;
        merge_to_child_list(node2, node1);
        ++node2.degree;
        node1.parent = node2;
        node1.mark = false;
    }
    private void remove_from_rootList(Node node) {
        if (node == this.root_list) {
            this.root_list = node.right;
        }
        //check if node is the only node
        if (node == node.right) {
            this.root_list = null;
        }
        node.left.right = node.right;
        node.right.left = node.left;

    }
    //merge a node to the child list of another node
    private void merge_to_child_list(Node parent, Node node) {
        if (parent.child == null) {
            parent.child = node;
            node.right = node;
            node.left = node;
        }
        else {
            node.right = parent.child.right;
            node.left = parent.child;
            parent.child.right.left = node;
            parent.child.right = node;

        }

    }
    public void display1() {
        if (min == null) {
            System.out.println("The heap is empty.");
            return;
        }

        System.out.println("The root nodes of the heap:");
        Node start = min;
        Node current = start;

        do {
            printNode(current, 0);
            current = current.right;
        } while (current != start);

        System.out.println("The heap has " + size + " nodes.");
    }
    //decrease a key of a node and update the heap as necessary
    public void decreaseKey(Node node1, int key) {
    if (k > node1.key) {
        return; 
    }
    node1.key = key; 
    Node node2 = node1.parent;
    if (node2 != null && node1.key < node2.key) {
        cut(node1, node2);
        cascadingCut(node2);
    }

    if (node1.key < min.key) {
        min = node1; // Update minimum if the decreased node is smaller
    }
}

private void cut(Node node1, Node node2) {
    removeChildFromList(node2, node1);
    node2.degree--; // decrease the degree of the original parent node
    mergeRootList(node1); //link the cut node to the root list
    node1.parent = null;
    node1.mark = false; 
}

private void cascadingCut(Node node2) {
    Node node3 = node2.parent;
    if (node3 != null) {
        if (!node2.mark) {
            node2.mark = true; //since there's a child now removed
        } else {
            cut(node2, node3);
            cascadingCut(node3);
        }
    }
}
private void removeChildFromList(Node parent, Node node) {
    if (parent.child == parent.child.right) {
        parent.child = null;
    } else if (parent.child == node) {
        parent.child = node.right;
        node.right.parent = parent;
    }
    node.left.right = node.right;
    node.right.left = node.left;
}
    private void printNode(Node node, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  "); // Two spaces per level of depth
        }
        System.out.println("Node " + node.key + " Degree: " + node.degree + (node.mark ? " Marked" : " Unmarked"));

        Node child = node.child;
        if (child != null) {
            Node childStart = child;
            do {
                printNode(child, level + 1); // Recursively print children at increased indentation
                child = child.right;
            } while (child != childStart);
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
        //Node node = heap.extractMin();
        //System.out.println(node.key);
        FibonacciHeap heap1 = new FibonacciHeap();
        heap1.insert(11);
        heap1.insert(18);
        heap1.insert(24);
        heap1.insert(72);
        heap1.insert(19);
        heap1.insert(0);
        heap.union(heap1);
        heap.extractMin();
        heap.insert(0);
        System.out.println(heap.min.key);
        heap.consolidate();
        heap.display1(); 

    }
}