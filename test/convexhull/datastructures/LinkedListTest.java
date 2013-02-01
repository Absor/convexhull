package convexhull.datastructures;

import convexhull.algorithms.Helper;
import convexhull.comparators.AngleComparator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
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
public class LinkedListTest {

    public LinkedListTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of insert method, of class LinkedList.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        LinkedList instance = new LinkedList();
        // inserting null point
        instance.insert(null);
        // check if it got in the list
        assertNull(instance.getHead().getPoint());
        // insert a test point
        Point2D.Double testPoint = new Point2D.Double(3, 2);
        instance.insert(testPoint);
        // list should now contain the test point
        assertEquals(testPoint, instance.getHead().getNext().getPoint());
    }

    /**
     * Test of getHead method, of class LinkedList.
     */
    @Test
    public void testGetHead() {
        System.out.println("getHead");
        LinkedList instance = new LinkedList();
        // empty list head should be null
        assertNull(instance.getHead());
        // add test point
        Point2D.Double testPoint = new Point2D.Double(1, 2);
        instance.insert(testPoint);
        // head should now contain the test point
        assertEquals(testPoint, instance.getHead().getPoint());
    }

    /**
     * Test of getLength method, of class LinkedList.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        LinkedList points = new LinkedList();
        // empty list length 0
        assertEquals(0, points.getLength());
        try {
            points = Helper.parseFile("test100");
        } catch (Exception ex) {
            fail("Could not parse input file.");
        }
        // test file has 100 points
        assertEquals(100, points.getLength());
    }

    /**
     * Test of sort method, of class LinkedList.
     */
    @Test
    public void testSort() {
        System.out.println("sort");
        LinkedList points1 = null;
        try {
            points1 = Helper.parseFile("test100");
        } catch (Exception ex) {
            fail("Could not parse input file.");
        }
        // create comparator
        AngleComparator comparator = new AngleComparator(new Point2D.Double(0, 0));
        // use the merge sort
        points1.sort(comparator);

        // make also an arraylist of the same points
        ArrayList<Point2D.Double> points2 = new ArrayList<Point2D.Double>();
        LinkedListNode node = points1.getHead();
        while (node != null) {
            points2.add(node.getPoint());
            node = node.getNext();
        }

        // sort the arraylist using the same comparator
        Collections.sort(points2, comparator);

        // compare the results
        if (!Helper.sameOrder(points1, points2)) {
            fail("Sorting returned the list in wrong order.");
        }
    }

    /**
     * Test of sort method, of class LinkedList.
     */
    @Test
    public void testSort2() {
        System.out.println("sort2");
        LinkedList points = null;
        try {
            points = Helper.parseFile("test100");
        } catch (Exception ex) {
            fail("Could not parse input file.");
        }
        // create comparator with minimum y point
        AngleComparator comparator = new AngleComparator(new Point2D.Double(0.4117650626370341, -2.869145785655391));
        // use the merge sort
        points.sort(comparator);
        
        // check if the list is intanct
        int length = 1;
        LinkedListNode head = points.getHead();
        while (head.getNext() != null) {
            length++;
            head = head.getNext();
        }
        // when we get to the end, we have to be at the tail node
        assertEquals("next-links are not intact after sorting.",
                points.getTail(), head);
        assertEquals("Sorted list length doesn't match.", length, points.getLength());

        length = 1;
        LinkedListNode tail = points.getTail();
        while (tail.getPrev() != null) {
            length++;
            tail = tail.getPrev();
        }
        // when we get to the beginning, we have to be at the head node
        assertEquals("prev-links are not intact after sorting.",
                points.getHead(), tail);
        assertEquals("Sorted list length doesn't match.", length, points.getLength());
    }

    /**
     * Test of setLength method, of class LinkedList.
     */
    @Test
    public void testSetLength() {
        System.out.println("setLength");
        LinkedList instance = new LinkedList();
        // new instance lenght should be zero
        assertEquals(0, instance.getLength());
        instance.setLength(10);
        // should now be 10
        assertEquals(10, instance.getLength());
    }

    /**
     * Test of setHead method, of class LinkedList.
     */
    @Test
    public void testSetHead() {
        System.out.println("setHead");
        LinkedList instance = new LinkedList();
        instance.setHead(new LinkedListNode(new Point2D.Double(1, 2)));
        // check if the new head has the point now
        assertEquals(new Point2D.Double(1, 2), instance.getHead().getPoint());
        
        instance.setHead(null);
        // check for null now
        assertNull(instance.getHead());
    }

    /**
     * Test of getTail method, of class LinkedList.
     */
    @Test
    public void testGetTail() {
        System.out.println("getTail");
        LinkedList instance = new LinkedList();
        // empty list tail should be null
        assertNull(instance.getTail());
        // add test point
        Point2D.Double testPoint = new Point2D.Double(1, 2);
        instance.insert(testPoint);
        // tail should now contain the test point
        assertEquals(testPoint, instance.getTail().getPoint());
    }

    /**
     * Test of setTail method, of class LinkedList.
     */
    @Test
    public void testSetTail() {
        System.out.println("setHead");
        LinkedList instance = new LinkedList();
        instance.setTail(new LinkedListNode(new Point2D.Double(1, 2)));
        // check if the new head has the point now
        assertEquals(new Point2D.Double(1, 2), instance.getTail().getPoint());
        
        instance.setTail(null);
        // check for null now
        assertNull(instance.getHead());
    }
}
