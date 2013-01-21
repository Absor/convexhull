/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convexhull.datastructures;

import java.awt.geom.Point2D.Double;
import java.util.Comparator;
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
        Double point = null;
        LinkedList instance = new LinkedList();
        instance.insert(point);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHead method, of class LinkedList.
     */
    @Test
    public void testGetHead() {
        System.out.println("getHead");
        LinkedList instance = new LinkedList();
        LinkedListNode expResult = null;
        LinkedListNode result = instance.getHead();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLength method, of class LinkedList.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        LinkedList instance = new LinkedList();
        int expResult = 0;
        int result = instance.getLength();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mergeSort method, of class LinkedList.
     */
//    @Test
//    public void testMergeSort() {
//        System.out.println("mergeSort");
//        Comparator<Double> comp = null;
//        LinkedList instance = new LinkedList();
//        instance.mergeSort(comp);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
