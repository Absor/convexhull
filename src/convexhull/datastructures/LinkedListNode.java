package convexhull.datastructures;

import java.awt.geom.Point2D;

/**
 *
 * @author
 */
public class LinkedListNode {

    private LinkedListNode next;
    private Point2D.Double point;

    LinkedListNode(Point2D.Double point) {
        this.point = point;
        this.next = null;
    }

    /**
     *
     * @return
     */
    public LinkedListNode getNext() {
        return this.next;
    }

    /**
     *
     * @param next
     */
    public void setNext(LinkedListNode next) {
        this.next = next;
    }

    /**
     *
     * @return
     */
    public Point2D.Double getPoint() {
        return this.point;
    }
}
