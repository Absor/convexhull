package convexhull.datastructures;

import java.awt.geom.Point2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heikki Haapala
 */
public class LinkedListNodeTest {

    /**
     *
     */
    public LinkedListNodeTest() {
    }

    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     *
     */
    @Before
    public void setUp() {
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of getNext method, of class LinkedListNode.
     */
    @Test
    public void testGetNext() {
        System.out.println("getNext");
        LinkedListNode instance = new LinkedListNode(new Point2D.Double(1, 2));
        // next should be null for a new node
        assertNull(instance.getNext());
        // add a node as next and check if it actually got there
        LinkedListNode next = new LinkedListNode(new Point2D.Double(2, 3));
        instance.setNext(next);
        assertEquals(next, instance.getNext());
    }

    /**
     * Test of setNext method, of class LinkedListNode.
     */
    @Test
    public void testSetNext() {
        System.out.println("setNext");
        LinkedListNode instance = new LinkedListNode(new Point2D.Double(1, 2));
        // next should be null for a new node
        assertNull(instance.getNext());
        // add a node as next and check if it actually got there
        LinkedListNode next = new LinkedListNode(new Point2D.Double(2, 3));
        instance.setNext(next);
        assertEquals(next, instance.getNext());
        // null as next and test
        instance.setNext(null);
        assertNull(instance.getNext());
    }

    /**
     * Test of getPoint method, of class LinkedListNode.
     */
    @Test
    public void testGetPoint() {
        System.out.println("getPoint");
        // create a point and a node with the point
        Point2D.Double point = new Point2D.Double(1, 2);
        LinkedListNode instance = new LinkedListNode(point);
        // check if the point is in the node
        assertEquals(point, instance.getPoint());
        // node with null point and assert
        instance = new LinkedListNode(null);
        assertNull(instance.getPoint());
    }

    /**
     * Test of getPrev method, of class LinkedListNode.
     */
    @Test
    public void testGetPrev() {
        System.out.println("getPrev");
        LinkedListNode instance = new LinkedListNode(new Point2D.Double(1, 2));
        // prev should be null for a new node
        assertNull(instance.getPrev());
        // add a node as next and check if it actually got there
        LinkedListNode prev = new LinkedListNode(new Point2D.Double(2, 3));
        instance.setPrev(prev);
        assertEquals(prev, instance.getPrev());
    }

    /**
     * Test of setPrev method, of class LinkedListNode.
     */
    @Test
    public void testSetPrev() {
        System.out.println("setPrev");
        LinkedListNode instance = new LinkedListNode(new Point2D.Double(1, 2));
        // next should be null for a new node
        assertNull(instance.getPrev());
        // add a node as next and check if it actually got there
        LinkedListNode prev = new LinkedListNode(new Point2D.Double(2, 3));
        instance.setPrev(prev);
        assertEquals(prev, instance.getPrev());
        // null as next and test
        instance.setPrev(null);
        assertNull(instance.getPrev());
    }
}
