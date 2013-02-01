package convexhull.datastructures;

import java.awt.geom.Point2D;
import java.util.Comparator;

/**
 * The "container" for a doubly linked list that holds Point2D.Double objects.
 *
 * @author Heikki Haapala
 */
public class LinkedList {

    private LinkedListNode head = null;
    private LinkedListNode tail = null;
    private int length = 0;
    private Comparator<Point2D.Double> comparator = null;

    /**
     * Adds a single Point2D.Double to the end of the linked list.
     *
     * @param point the point to be inserted into the linked list
     */
    public void insert(Point2D.Double point) {
        if (this.tail == null) {
            // if the list is empty make a new node with the point and set it as
            // the head and tail of the list
            this.head = new LinkedListNode(point);
            this.tail = this.head;
        } else {
            // if the list is not empty 
            LinkedListNode newNode = new LinkedListNode(point);
            // add the node as the next of the last
            this.tail.setNext(newNode);
            // tail as the previous of the new node
            newNode.setPrev(this.tail);
            // and set the added node as the last
            this.tail = this.tail.getNext();
        }
        this.length++;
    }
    
    /**
     * Adds all points from another linked list to this list.
     *
     * @param points points to be inserted into the linked list
     */
    public void insertAll(LinkedList points) {
        LinkedListNode node = points.getHead();
        while (node != null) {
            this.insert(node.getPoint());
            node = node.getNext();
        }
    }

    /**
     * Returns the first node of the linked list.
     *
     * @return the first node in the linked list
     */
    public LinkedListNode getHead() {
        return this.head;
    }

    /**
     * Sets a new head for the list. For manual list manipulation.
     *
     * @param head new head node
     */
    public void setHead(LinkedListNode head) {
        this.head = head;
    }

    /**
     * Returns the last node of the linked list.
     *
     * @return the first node in the linked list
     */
    public LinkedListNode getTail() {
        return this.tail;
    }

    /**
     * Sets a new tail for the list. For manual list manipulation.
     *
     * @param tail new tail node
     */
    public void setTail(LinkedListNode tail) {
        this.tail = tail;
    }

    /**
     * Returns how many nodes are in the linked list.
     *
     * @return the number of nodes in the list
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Setter for the length of the list if it needs to be updated manually.
     *
     * @param length the new length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Sorts the list using recursive merge sort.
     *
     * @param comparator comparator to use when sorting
     */
    public void sort(Comparator<Point2D.Double> comparator) {
        this.comparator = comparator;
        LinkedList sortedList = mergeSort(this);
        this.head = sortedList.getHead();
        this.tail = sortedList.getTail();
        this.comparator = null;
    }

    // SORTING HELPER METHODS
    private LinkedList mergeSort(LinkedList list) {
        // no sorting if list is empty or has only one node
        if (list.getHead() == null || list.getHead().getNext() == null) {
            return list;
        }
        //get the middle of the list
        LinkedListNode middle = getMiddle(list);
        //split the list into two halfs
        LinkedListNode otherHalf = middle.getNext();
        middle.setNext(null);
        otherHalf.setPrev(null);
        // mid as the end of the old list
        LinkedListNode oldTail = list.getTail();
        list.setTail(middle);
        // make a new list of the rest of the split
        LinkedList newList = new LinkedList();
        newList.setHead(otherHalf);
        newList.setTail(oldTail);

        // recursive sorting
        return merge(mergeSort(list), mergeSort(newList));
    }

    //Merge subroutine to merge two sorted lists
    private LinkedList merge(LinkedList a, LinkedList b) {
        LinkedListNode aNode = a.getHead();
        LinkedListNode bNode = b.getHead();

        // start node
        LinkedListNode current = null;
        if (comparator.compare(aNode.getPoint(), bNode.getPoint()) <= 0) {
            current = aNode;
            aNode = aNode.getNext();
        } else {
            current = bNode;
            bNode = bNode.getNext();
        }

        // remember the head (reusing a)
        a.setHead(current);

        // repeat until one of the lists ends
        while (aNode != null && bNode != null) {
            if (this.comparator.compare(aNode.getPoint(), bNode.getPoint()) <= 0) {
                current.setNext(aNode);
                aNode = aNode.getNext();
            } else {
                current.setNext(bNode);
                bNode = bNode.getNext();
            }
            // fix the prev-link
            current.getNext().setPrev(current);
            // continue
            current = current.getNext();
        }

        // add the rest of the other list to the end and set the end node
        if (aNode == null) {
            current.setNext(bNode);
            a.setTail(b.getTail());
        } else {
            current.setNext(aNode);
            // no need to change tail
        }
        // fix the prev-link
        current.getNext().setPrev(current);

        return a;
    }

    // finds the middle element of the node list for splitting
    private LinkedListNode getMiddle(LinkedList list) {
        if (list.getHead() == null) {
            return null;
        }
        LinkedListNode slow, fast;
        slow = list.getHead();
        fast = list.getHead();
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        // when fast reaches the end, slow is half way there
        return slow;
    }
}
