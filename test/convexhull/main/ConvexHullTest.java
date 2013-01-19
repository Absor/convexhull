/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convexhull.main;

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
public class ConvexHullTest {
    
    public ConvexHullTest() {
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
     * Test of main method, of class ConvexHull.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ConvexHull.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startTimer method, of class ConvexHull.
     */
    @Test
    public void testStartTimer() {
        System.out.println("startTimer");
        ConvexHull.startTimer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stopTimer method, of class ConvexHull.
     */
    @Test
    public void testStopTimer() {
        System.out.println("stopTimer");
        long expResult = 0L;
        long result = ConvexHull.stopTimer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
