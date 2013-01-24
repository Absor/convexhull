package convexhull.datastructures;

import java.awt.geom.Point2D;
import java.util.Comparator;

/**
 * The "container" for a singly linked list that holds Point2D.Double objects.
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
            // if the list is not empty add the node as the next of the last
            this.tail.setNext(new LinkedListNode(point));
            // and set the added node as the last
            this.tail = this.tail.getNext();
        }
        this.length++;
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
     * Returns how many nodes are in the linked list.
     *
     * @return the number of nodes in the list
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Sorts the list using recursive merge sort.
     *
     * @param comparator comparator to use when sorting
     */
    public void sort(Comparator<Point2D.Double> comparator) {
        this.comparator = comparator;
        this.head = mergeSort(this.head);
        this.comparator = null;
    }

    private LinkedListNode mergeSort(LinkedListNode head) {
        // no sorting if list is empty or has only one node
        if (head == null || head.getNext() == null) {
            return head;
        }
        //get the middle of the list
        LinkedListNode middle = getMiddle(head);
        //split the list into two halfs
        LinkedListNode otherHalf = middle.getNext();
        middle.setNext(null);

        // recursive sorting
        return merge(mergeSort(head), mergeSort(otherHalf));
    }

    //Merge subroutine to merge two sorted lists
    private LinkedListNode merge(LinkedListNode a, LinkedListNode b) {
        // start node
        LinkedListNode current = null;
        if (comparator.compare(a.getPoint(), b.getPoint()) <= 0) {
            current = a;
            a = a.getNext();
        } else {
            current = b;
            b = b.getNext();
        }
        
        // remember the head
        LinkedListNode first = current;

        // repeat until one of the lists ends
        while (a != null && b != null) {
            if (comparator.compare(a.getPoint(), b.getPoint()) <= 0) {
                current.setNext(a);
                a = a.getNext();
            } else {
                current.setNext(b);
                b = b.getNext();
            }
            current = current.getNext();
        }
        
        // add the rest of the other list to the end
        if (a == null) {
            current.setNext(b);
        } else {
            current.setNext(a);
        }
        
        return first;
    }

    // finds the middle element of the node list for splitting
    private LinkedListNode getMiddle(LinkedListNode head) {
        if (head == null) {
            return head;
        }
        LinkedListNode slow, fast;
        slow = head;
        fast = head;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }
}
