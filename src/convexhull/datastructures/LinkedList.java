package convexhull.datastructures;

import java.awt.geom.Point2D;
import java.util.Comparator;

/**
 * The "container" for a singly linked list that holds Point2D.Double
 * objects.
 * 
 * @author Heikki Haapala
 */
public class LinkedList {

    private LinkedListNode head = null;
    private LinkedListNode tail = null;
    private int length = 0;

    /**
     * Adds a single Point2D.Double to the end of the linked list.
     *
     * @param point     the point to be inserted into the linked list
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
     * @return  the first node in the linked list
     */
    public LinkedListNode getHead() {
        return this.head;
    }

    /**
     * Returns how many nodes are in the linked list.
     * 
     * @return  the number of nodes in the list
     */
    public int getLength() {
        return this.length;
    }
    
    public void sort(Comparator<Point2D.Double> comp) {
        
    }

    private void mergeSort() {
        // no sorting if list is empty or has only one node
        if (this.head == null || this.head.getNext() == null) {
            return;
        }
        //get the middle of the list
        LinkedListNode middle = getMiddle();
        //split the list into two halfs
        LinkedListNode otherHalf = middle.getNext();
        middle.setNext(null);

        // recursive sorting
//        return merge(merge_sort(head), merge_sort(otherHalf));
    }

//    //Merge subroutine to merge two sorted lists
//    private LinkedList merge(Node a, Node b) {
//        Node dummyHead, curr;
//        dummyHead = new Node();
//        curr = dummyHead;
//        while (a != null && b != null) {
//            if (a.info <= b.info) {
//                curr.next = a;
//                a = a.next;
//            } else {
//                curr.next = b;
//                b = b.next;
//            }
//            curr = curr.next;
//        }
//        curr.next = (a == null) ? b : a;
//        return dummyHead.next;
//    }

    // finds the middle element of the list for splitting
    private LinkedListNode getMiddle() {
        if (this.getHead() == null) {
            return this.getHead();
        }
        LinkedListNode slow, fast;
        slow = this.getHead();
        fast = this.getHead();
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }
}
