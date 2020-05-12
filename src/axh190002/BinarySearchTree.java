/** @author 
 *  Binary search tree (starter code)
 **/
/**
 * IDSA Short Project 7
 * Team members:
 * Adarsh Raghupati   axh190002
 * Keerti Keerti      kxk190012
 */
package axh190002;

import java.util.Iterator;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {
    static class Entry<T> {
        T element;
        Entry<T> left, right;

        public Entry(T x, Entry<T> left, Entry<T> right) {
            this.element = x;
	    this.left = left;
	    this.right = right;
        }
    }
    
    Entry<T> root;
    int size;
    Stack<Entry<T>> stck = new Stack<>();
    Entry replaced;
    public BinarySearchTree() {
	root = null;
	size = 0;
    }

    /**
     * Returns the node which contains element x. If there is no node having element x then returns the node where the search ended
     * @param node
     * @param x
     * @return
     */
    public Entry<T> find(Entry<T> node,T x){
        this.stck.push(null);
        if( node.element == x){
            return node;
        }
        while(true){
            if (x.compareTo(node.element) < 0){
                if (null == node.left){
                    break;
                }else{
                    this.stck.push(node);
                    node = node.left;
                }
            }else if (x.compareTo(node.element) == 0){
                break;
            }else{
                if(null == node.right){
                    break;
                }else{
                    this.stck.push(node);
                    node = node.right;
                }
            }
        }
        return node;
    }

    /**
     * Return true if tree has x else return false
     * @param x
     * @return
     */
    public boolean contains(T x) {
        Entry<T> node = this.find(root,x);
        if(null == node || node.element.compareTo(x) != 0) {
            //this.stck.clear();
            //this.stck = new Stack<>();
            return false;
        }else{
            return true;
        }
    }

    /**
     * Returns the node if tree contains x else returns null
     * @param x
     * @return
     */
    public T get(T x) {
        if(this.contains(x)){
            return x;
        }else{
            return null;
        }
    }


    /**
     *  If tree contains a node with same key, replace element by x.
     *  Returns true if x is a new element to be added to tree.
     */
    public boolean add(T x) {
        if(this.size == 0){
            this.root = new Entry<T>(x,null,null);
            this.size++;
            return true;
        }else{
            Entry<T> node = find(root,x);
            if(x.compareTo(node.element) == 0){
                node.element = x;
                return false;
            }else{
                if(x.compareTo(node.element) < 0){
                    node.left = new Entry<T>(x,null,null);
                }else{
                    node.right = new Entry<T>(x,null,null);
                }
                this.size++;
                return true;
            }
        }
    }


    /** Remove x from tree.
     *  Return x if found, otherwise return null
     */
    public T remove(T x) {
        if (this.size == 0){
            return null;
        }else{
            Entry<T> node = find(root,x);
            if(node.element.compareTo(x) != 0){
                return null;
            }
            T removed = node.element;
            if(null == node.left || null == node.right){
                connectChildToParent(node);
            }else{
                this.stck.push(node);
                Entry<T> minRight = find(node.right,x);
                node.element = minRight.element;
                connectChildToParent(minRight);
            }
            this.size--;
            return removed;
        }
    }

    /**
     * Helper method for remove(x)
     * Attaches child to parent
     * @param node
     */
    public void connectChildToParent(Entry<T> node){
        Entry<T> parent = this.stck.peek();
        Entry<T> child = (null == node.left)?node.right:node.left;
        if(null == parent){
            this.root = child;
        }else if(parent.left == node){
            parent.left = child;
        }else{
            parent.right = child;
        }
    }

    /**
     * Returns minimum element present in the tree
     * @return
     */
    public T min() {
        if (this.size == 0){
            return null;
        }
        Entry<T> node = root;
        while(null != node.left){
            node = node.left;
        }
        return node.element;
    }

    /**
     * Returns maximum element present in the tree
     * @return
     */
    public T max() {
        if (this.size == 0){
            return null;
        }
        Entry<T> node = root;
        while(null != node.right){
            node = node.right;
        }
        return node.element;
    }

    /**
     *Store the elements of tree in array according to inorder
     * @return
     */
    public Comparable[] toArray() {
        Comparable[] arr = new Comparable[this.size];
        /* write code to place elements in array here */
        if(this.size == 0){
            System.out.println("The Binary Search Tree is Empty");
            return arr;
        }
        Stack<Entry<T>> s = new Stack<>();
        Entry<T> node = this.root;
        int ind = 0;
        while(null != node || s.size()>0){
            while(null != node){
                s.push(node);
                node=node.left;
            }
            node = s.pop();
            arr[ind++] = node.element;
            node=node.right;
        }
        return arr;
    }


// Start of Optional problem 2

    /** Optional problem 2: Iterate elements in sorted order of keys
	Solve this problem without creating an array using in-order traversal (toArray()).
     */
    public Iterator<T> iterator() {
	return null;
    }

    // Optional problem 2.  Find largest key that is no bigger than x.  Return null if there is no such key.
    public T floor(T x) {
        return null;
    }

    // Optional problem 2.  Find smallest key that is no smaller than x.  Return null if there is no such key.
    public T ceiling(T x) {
        return null;
    }

    // Optional problem 2.  Find predecessor of x.  If x is not in the tree, return floor(x).  Return null if there is no such key.
    public T predecessor(T x) {
        return null;
    }

    // Optional problem 2.  Find successor of x.  If x is not in the tree, return ceiling(x).  Return null if there is no such key.
    public T successor(T x) {
        return null;
    }

// End of Optional problem 2

    public static void main(String[] args) {
	BinarySearchTree<Integer> t = new BinarySearchTree<>();
     t.add(12);
        t.add(22);
        t.add(26);
        t.add(30);
        t.add(32);
        t.add(18);
        t.add(16);
        t.add(20);
        t.add(24);
        t.add(28);
        t.add(5);
        t.add(-5);
        t.add(6);
        t.add(-14);
        t.add(-4);
        t.stck.clear();
        t.remove(26);
        t.printTree();
    /*    Scanner in = new Scanner(System.in);
        System.out.println("Enter positive number to add and negative number to remove element from the tree\n" +
                "Enter 0 to print max and min elements and exit");
        while(in.hasNext()) {
            int x = in.nextInt();
            if(x > 0) {
                System.out.print("Add " + x + " : ");
                t.add(x);
                t.printTree();
            } else if(x < 0) {
                System.out.print("Remove " + x + " : ");
                t.remove(-x);
                t.printTree();
            } else {
                Comparable[] arr = t.toArray();
                System.out.print("Final: ");
                for(int i=0; i<t.size; i++) {
                    System.out.print(arr[i] + " ");
                }
                System.out.println();
                Integer max = t.max();
                Integer min = t.min();
                if(max != null)
                System.out.println("Max element is: "+max.toString()+ " Min element is: "+min.toString());
                return;
            }
        }*/
    }


    public void printTree() {
	System.out.print("[" + size + "]");
	printTree(root);
	System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
	if(node != null) {
	    printTree(node.left);
	    System.out.print(" " + node.element);
	    printTree(node.right);
	 }
   }
}
/*
Sample input:
1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0

Output:
Add 1 : [1] 1
Add 3 : [2] 1 3
Add 5 : [3] 1 3 5
Add 7 : [4] 1 3 5 7
Add 9 : [5] 1 3 5 7 9
Add 2 : [6] 1 2 3 5 7 9
Add 4 : [7] 1 2 3 4 5 7 9
Add 6 : [8] 1 2 3 4 5 6 7 9
Add 8 : [9] 1 2 3 4 5 6 7 8 9
Add 10 : [10] 1 2 3 4 5 6 7 8 9 10
Remove -3 : [9] 1 2 4 5 6 7 8 9 10
Remove -6 : [8] 1 2 4 5 7 8 9 10
Remove -3 : [8] 1 2 4 5 7 8 9 10
Final: 1 2 4 5 7 8 9 10 

*/
