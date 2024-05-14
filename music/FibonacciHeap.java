package music;
import java.util.*;

//Create the node class
class Node {
    public int key;
    public int degree;
    public Node parent;
    public Node left;
    public Node right;
    public Node child;
    public boolean mark;
    public String id;
    public Song song;

    //Node Constructor
    public Node(int key) {
        this.key = key;
        this.degree = 0;
        this.parent = null;
        this.left = this;
        this.right = this;
        this.child = null;
        this.mark = false;
    }
    //Alternative constructor for playlist application
    public Node(int key_num, Song song, String id) {
        this (key_num);
        this.id = id;
        this.song = song;
    }

    //Setter and getter methods for Node instance variables
    public Song get_song() {
        return this.song;
    }
    public String get_Id() {
        return this.id;
    }
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
    private int size; //Number of nodes in the heap
    private Node min; //The node with minimum key
    private boolean trace; //For debugging purposes
    private Node root_list; //the linked list of root nodes
    //Additional data structure: HashMap
    public HashMap<String, Node> nodeHashMap; //Map nodes to song id for quick access

    //Setter for trace
    public void set_trace(boolean t) {
        this.trace = t;
    }
    //Getter for tace
    public boolean get_trace() {
        return this.trace;
    }

    //Constructor method
    public FibonacciHeap() {
        this.size = 0;
        this.min = null;
        this.trace = false;
        this.root_list = null;
        nodeHashMap = new HashMap<>();

    }
    //Check if the heap is empty
    //Returns true if the heap is empty
    public boolean isEmpty() {
        return min == null;
    }
    //Inserts a new node with the given key into the heap
    public void insert(int key) {
        //Create new node keeping with circular link property
        Node node = new Node(key);
        node.left = node;
        node.right = node;

        //Merge the node into the root list
        mergeRootList(node);

        //Update the minimum if new node has a smaller key
        if (min == null || node.key < min.key) {
            min = node;
        }
        //Map the node to node id for easy access
        nodeHashMap.put(node.id, node);
        //Update size
        size++;
    }
    //Alternative insert method for the playlist application
    public void insert(int key, Song song, String id) {
        Node node = new Node(key, song, id);
        node.left = node;
        node.right = node;

        //Merge the node into the root list
        mergeRootList(node);

        //Update the minimum if new node has a smaller key
        if (min == null || node.key < min.key) {
            min = node;
        }
        //Map the node to node id for easy access
        nodeHashMap.put(node.id, node);
        //Update size
        size++;
    }
    //Helper method for insert
    //Adds the new node to the root list
    //maintaining the circular doubly link property
    public void mergeRootList(Node node) {
        //Check if the root list is empty
        if (root_list == null) {
            this.root_list = node;
            node.right = node;
            node.left = node;
        }
        //If not empty, add the node the circular root list
        else {
            node.right = this.root_list;
            node.left = this.root_list.left;
            this.root_list.left.right = node;
            this.root_list.left = node;
        }
    }
    // Merges the heap to a another Fibonnaci heap
    public FibonacciHeap merge(FibonacciHeap newHeap) {
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
        if (this.min == null || this.min.key > newHeap.min.key) {
            this.min = newHeap.min;
        }
        //adjust the merged heap's size
        this.size += newHeap.size;
        //return the united heap
        return this;
    }
    //Find and return the node with smallest key
    public Node extractMin() {
        Node node = this.min;
        //Check if the heap is empty
        //If not empty retrieve all children of min
        //and add them to the root list
        if (node != null) {
            //Check if the min node has children
            if (node.child != null) {
                //Get all children of the min node
                ArrayList<Node> children = iterate(node.child);
                //Insert all the child nodes into the root list
                for (Node ch : children) {
                    mergeRootList(ch);
                    //Remove the each child's link to parent
                    ch.parent = null;
                }
            }
            //Remove the min node from the root list
            remove_from_rootList(node);
            //Check if min was the only root node in the list
            if (node == node.right) {
                this.min = null;
                this.root_list = null;
            }
            //Update the min and the heap structure
            else {
                //Temoporarily set min to the next node
                this.min = node.right;
                //Rearrange the heap
                //Make sure no two roots have the same degree
                //Find and assign the new min
                consolidate();
            }
            //Decrement the heap's size
            this.size--;
        }
        //Return the retreived min node
        return node;
    }
    //Returns an Arraylist of the children of a given node
    private ArrayList<Node> iterate(Node node) {
        ArrayList<Node> nodeList = new ArrayList<Node>();
        //Set the current and target stop node to the given node
        Node current = node;
        Node stop = node;
        //Marker to check if iteration has started
        boolean loopMark = false;
        //Iterate all children of the given node
        while (true) {
            //Break if one full loop is  completed
            if (current == stop && loopMark) {
                break;
            }
            //Mark that iteration has started
            else if (current == stop) {
                loopMark = true;
            }
            //Add the node to the arraylist
            //Move to the next node
            nodeList.add(current);
            current = current.right;
        }
        //return the arraylist of children
        return nodeList;
    }
    //Ensure that no two roots have teh same degree
    //Finds and updates minimum node
    private void consolidate() {
        //the potential maximum degree of the heap based on Fibonacci sequence
        int num = (int) Math.ceil(Math.log(this.size) / Math.log(2)) + 1;
        Node[] arr = new Node[num];
        
        //Get all the nodes in the rootlist
        ArrayList<Node> nodes = iterate(this.root_list);
        //Check if there are two root nodes with same degree
        for (Node node1 : nodes) {
            int deg = node1.degree;

            //If two nodes have the same degreee attach the node with bigger
            //key to the children list of the node with smaller key
            while (arr[deg] != null) {
                //Get the node with the same degree
                Node node2 = arr[deg];
                //Check if the node with the same degree has smaller key
                if (node1.key > node2.key) {
                    Node temp = node1;
                    node1 = node2;
                    node2 = temp;
                }
                //Insert the node with bigger key to the children of 
                //the node with smaller key
                heap_link(node2, node1);
                arr[deg] = null;
                //Update the degree of the node with smaller key
                deg++;
            }
            //If the degree does not match any of the iterated nodes 
            //map the node to its degree
            arr[deg] = node1;
        }
        //Temporarily reset min until finding new min
        min = null;
        //Find the new minimum
        for (Node node : arr) {
            if (node != null) {
                if (min == null || node.key < this.min.key) {
                    this.min = node;  
                }
            }
        }
    }

    // Helper method to link one root node to the 
    // children of the other
    private void heap_link(Node node1, Node node2) {
        // Remove the first noe from the root list
        // Remove its circular links
        this.remove_from_rootList(node1);
        // Link the removed node to the children
        // of the second node
        merge_to_child_list(node2, node1);
        // Assign the second as the parent of the first
        node1.parent = node2;

        // Update the degree of the second node
        node2.degree++;
   
        // Mark that the first flag has not lost a child
        // ever since it was made a child of the second
        node1.mark = false;
    }

    //Remove a node from the heap root list
    private void remove_from_rootList(Node node) {
        // Check if the node is the first in the rootlist
        if (node == this.root_list) {
            // Move the root list pointer to the next node
            this.root_list = node.right;
        }
        // Check if node is the only node in the root list
        if (node == node.right) {
            //Set the root list to empty
            this.root_list = null;
        }
        // Remove the node's links from other nodes in the lsit
        node.left.right = node.right;
        node.right.left = node.left;

    }
    //Merge a node to the child list of another node
    private void merge_to_child_list(Node parent, Node node) {
        // If the parent node is empty, assign the new node as its child
        if (parent.child == null) {
            parent.child = node;
            //node.parent = parent;
            // Remove the new node's link from its left and right nodes
            node.right = node;
            node.left = node;
        }
        // If the parent node has children, link the new node
        // to its children maintaing the circualr double links
        else {
            node.right = parent.child.right;
            node.left = parent.child;
            parent.child.right.left = node;
            parent.child.right = node;
        }
    }

    //Decrease the key of a node and update the heap as necessary
    public void decreaseKey(Node node1, int key) {
        // New key must be smaller than current key
        // Check if new key is smaller
        if (key > node1.key) {
            // Do nothing if new key is greater 
            return; 
        }
        // Update the key
        node1.key = key; 
        Node node2 = node1.parent;
        // If the node's key is smaller than its parent now
        // Update the heap using cut and cascading cut
        if (node2 != null && node1.key < node2.key) {
            cut(node1, node2);
            cascadingCut(node2);
        }
        // If the new key is maller than the minimum key
        // update the min
        if (node1.key < min.key) {
            min = node1; // Update minimum if the decreased node is smaller
        }
    }
    // Cuts a node from its parent and adds it to the root list
    private void cut(Node child, Node parent) {
        // Remove node from parent
        removeChildFromList(parent, child);
        parent.degree--; // Decrease the degree of the original parent node
        // Link the cut node to the root list
        mergeRootList(child); 
        child.parent = null;
        child.mark = false; 
    }

    // Removes child nodes from their parent nodes and
    // adds them to the root list
    private void cascadingCut(Node node2) {
        Node node3 = node2.parent;
        // Check if node2 is not a root node (in root list)
        if (node3 != null) {
            if (!node2.mark) {
                node2.mark = true; // Since node2 has lost a child now
            } else {
                // Recursively cut nodes until a root node is reached 
                cut(node2, node3);
                cascadingCut(node3);
            }
        }
    }

    // Removes a node from its parent's child list
    private void removeChildFromList(Node parent, Node node) {
        // If node is the only child, then set parent's child to null
        if (node== node.right) {
            parent.child = null;
        } 
        // If node is the first child, move the child pointer to the next node
        else {
            if (parent.child == node) {
                parent.child = node.right;
            }
            // Remove the node from the lsit by removing its links from the nodes in list
            node.right.left = node.left;
            node.left.right = node.right;
        }
    }

    // Deletes a node from the heap 
    public Node delete(Node node) {
        // Decrease the key of the ndoe to smallest possible number
        // making it the new minimum
        decreaseKey(node, Integer.MIN_VALUE );
        // Extract the node as the minimum
        return extractMin();
    }

    // Update a node key
    public void updateScore(String nodeId, int newKey) {
        Node node = this.nodeHashMap.get(nodeId);
        // Decrease key if the new key is smaller than the current key
        // Check if the node and new score are valid
        if (node != null && newKey != node.key) {
            if (newKey < node.key) {
                decreaseKey(node, newKey);
            }
            // Update score if node is greater the key
            // Update heap structure
            else {
                node.key = newKey;
                consolidate();
            }
        }
    }

    // Prints a node and its children based on a give depth
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

    

    // Debugging method to display the heap in detail.
    public void display1() {
        if (min == null) {
            System.out.println("The heap is empty.");
            return;
        }

        System.out.println("The root nodes of the heap:");
        Node start = min; // Start from the minimum node.
        Node current = start;

        do {
            printNode(current, 0); // Print each node and its children recursively.
            current = current.right; // Move to the next node.
        } while (current != start);

        System.out.println("The heap has " + size + " nodes."); // Print the total number of nodes.
    }

    // Method to display the heap's nodes.
    public void display() {
        if (min == null) {
            System.out.println("The heap is empty.");
        } else {
            System.out.println("The root nodes of Heap are:");
            Node temp = min; // Start from the min node.
            do {
                System.out.print(temp.key + " "); // Print each node's key.
                temp = temp.right; // Move to the next node.
                if (temp != min) {
                    System.out.print("--> "); // Print separator for nodes.
                }
            } while (temp != min);
            System.out.println("\nThe heap has " + size + " nodes."); // Print the total number of nodes.
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
        System.out.println("First Heap");
        heap.display();
        //Node node = heap.extractMin();
        //System.out.println(node.key);
        FibonacciHeap heap1 = new FibonacciHeap();
        heap1.insert(11);
        heap1.insert(18);
        heap1.insert(24);
        heap1.insert(72);
        heap1.insert(19);
        heap1.insert(0);
        System.out.println("Second Heap");
        heap1.display();
        heap.merge(heap1);
        System.out.println("Merged Heap");
        heap.display();
        System.out.println("Extracted Minimum");
        System.out.println( heap.extractMin().get_key());
        System.out.println( heap.extractMin().get_key());
        heap.display();
        heap.insert(0);
        System.out.println( heap.extractMin().get_key());
        heap.display();
        System.out.println(heap.min.key);
        heap.consolidate();
         System.out.println("Representation after Consolidation");
        heap.display1(); 
        

    }
}