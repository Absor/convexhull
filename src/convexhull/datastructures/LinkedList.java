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

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void insert(Point2D.Double point) {
        if (this.tail == null) {
            this.head = new LinkedListNode(point);
            this.tail = this.head;
        } else {
            this.tail.setNext(new LinkedListNode(point));
            this.tail = this.tail.getNext();
        }
    }

    public LinkedListNode getHead() {
        return head;
    }

    public void mergeSort(Comparator<Point2D.Double> comp) {
    }
}
