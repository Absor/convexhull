/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convexhull.algorithms;

import convexhull.datastructures.LinkedList;
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
public class AlgorithmTest {
    
    public AlgorithmTest() {
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
     * Test of useAlgorithm method, of class Algorithm.
     */
    @Test
    public void testUseAlgorithm() {
        System.out.println("useAlgorithm");
        LinkedList points = null;
        Algorithm instance = new AlgorithmImpl();
        LinkedList expResult = null;
        LinkedList result = instance.useAlgorithm(points);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AlgorithmImpl implements Algorithm {

        public LinkedList useAlgorithm(LinkedList points) {
            return null;
        }
    }
}
