/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convexhull.algorithms;

import convexhull.datastructures.LinkedList;
import convexhull.datastructures.LinkedListNode;
import convexhull.main.ConvexHull;
import java.awt.geom.Point2D;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Heikki Haapala
 */
public class GiftWrappingTest {

    /**
     *
     */
    public GiftWrappingTest() {
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
     * Test of useAlgorithm method, of class GiftWrapping.
     */
    @Test
    public void testUseAlgorithm() {
        System.out.println("useAlgorithm");
        // input set
        LinkedList input = null;
        try {
            input = FileHandler.parseFile("testfile");
        } catch (Exception ex) {
            fail("Could not parse input file.");
        }
        // known results
        LinkedList result = null;
        try {
            result = FileHandler.parseFile("testresult");
        } catch (Exception ex) {
            fail("Could not parse result file.");
        }

        GiftWrapping giftAlgo = new GiftWrapping();
        
        // algorithm results
        LinkedList algoResult = giftAlgo.useAlgorithm(input);
        
        if(result.getLength() != algoResult.getLength()) {
            fail("Results differ in length.");
        }

        // check that algorithm result set matches the known results
        LinkedListNode node1 = result.getHead();
        while (node1 != null) {
            Point2D.Double point1 = node1.getPoint();
            LinkedListNode node2 = algoResult.getHead();
            boolean found = false;
            while (node2 != null) {
                Point2D.Double point2 = node2.getPoint();
                if (point1.equals(point2)) {
                    found = true;
                }
                node2 = node2.getNext();
            }
            if (!found) {
                fail("Algorithm returns wrong result.");
            }
            node1 = node1.getNext();
        }
    }
}
