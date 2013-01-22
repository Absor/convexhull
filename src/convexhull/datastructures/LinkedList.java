package convexhull.datastructures;

import java.awt.geom.Point2D;
import java.util.Comparator;

/**
 *
 * @author
 */
public class LinkedList {

    private LinkedListNode head;
    private LinkedListNode tail;
    private int length;

    /**
     *
     */
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    /**
     *
     * @param point
     */
    public void insert(Point2D.Double point) {
        if (this.tail == null) {
            this.head = new LinkedListNode(point);
            this.tail = this.head;
        } else {
            this.tail.setNext(new LinkedListNode(point));
            this.tail = this.tail.getNext();
        }
        length++;
    }

    /**
     *
     * @return
     */
    public LinkedListNode getHead() {
        return this.head;
    }

    /**
     *
     * @return
     */
    public int getLength() {
        return this.length;
    }

//    public void mergeSort(Comparator<Point2D.Double> comp) {
//        // no sorting if list is empty or has only one node
//        if (this.head == null || this.head.getNext() == null) {
//            return;
//        }
//        //get the middle of the list
//        LinkedListNode middle = getMiddle();
//        //split the list into two halfs
//        LinkedListNode otherHalf = middle.getNext();
//        middle.setNext(null);
//
//        // recursive sorting
//        return merge(merge_sort(head), merge_sort(otherHalf));
//    }
//
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
//
//    //Finding the middle element of the list for splitting
//    public LinkedListNode getMiddle() {
//        if (head == null) {
//            return head;
//        }
//        Node slow, fast;
//        slow = fast = head;
//        while (fast.next != null && fast.next.next != null) {
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//        return slow;
//    }
}
