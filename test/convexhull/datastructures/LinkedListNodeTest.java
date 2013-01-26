package convexhull.datastructures;

import java.awt.geom.Point2D.Double;
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
        LinkedListNode instance = null;
        LinkedListNode expResult = null;
        LinkedListNode result = instance.getNext();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNext method, of class LinkedListNode.
     */
    @Test
    public void testSetNext() {
        System.out.println("setNext");
        LinkedListNode next = null;
        LinkedListNode instance = null;
        instance.setNext(next);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPoint method, of class LinkedListNode.
     */
    @Test
    public void testGetPoint() {
        System.out.println("getPoint");
        LinkedListNode instance = null;
        Double expResult = null;
        Double result = instance.getPoint();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrev method, of class LinkedListNode.
     */
    @Test
    public void testGetPrev() {
        System.out.println("getPrev");
        LinkedListNode instance = null;
        LinkedListNode expResult = null;
        LinkedListNode result = instance.getPrev();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrev method, of class LinkedListNode.
     */
    @Test
    public void testSetPrev() {
        System.out.println("setPrev");
        LinkedListNode prev = null;
        LinkedListNode instance = null;
        instance.setPrev(prev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
