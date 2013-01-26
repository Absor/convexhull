package convexhull.datastructures;

import java.awt.geom.Point2D;

/**
 * Class for presenting a node in the doubly linked list. Contains one
 * Point2D.Double object.
 *
 * @author Heikki Haapala
 */
public class LinkedListNode {

    private LinkedListNode prev = null;
    private LinkedListNode next = null;
    private Point2D.Double point;

    /**
     * Constructor for the node.
     *
     * @param point point to be added in the node
     */
    LinkedListNode(Point2D.Double point) {
        this.point = point;
    }

    /**
     * Returns the node before this node in the doubly linked list.
     *
     * @return the node before this node
     */
    public LinkedListNode getPrev() {
        return this.prev;
    }

    /**
     * Sets the node that comes before this node in the doubly linked list.
     *
     * @param prev the node to be set as the previous node
     */
    public void setPrev(LinkedListNode prev) {
        this.prev = prev;
    }

    /**
     * Returns the node after this node in the doubly linked list.
     *
     * @return the node after this one
     */
    public LinkedListNode getNext() {
        return this.next;
    }

    /**
     * Sets the node that comes after this node in the doubly linked list.
     *
     * @param next the node to be set as the next
     */
    public void setNext(LinkedListNode next) {
        this.next = next;
    }

    /**
     * Returns the Point2D.Double object contained.
     *
     * @return the point that this node holds
     */
    public Point2D.Double getPoint() {
        return this.point;
    }
}
