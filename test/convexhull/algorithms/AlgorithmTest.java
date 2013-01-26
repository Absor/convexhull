package convexhull.algorithms;

import convexhull.datastructures.LinkedList;
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
public class AlgorithmTest {

    /**
     *
     */
    public AlgorithmTest() {
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
     * Test of useAlgorithm method, of class Algorithm.
     */
    @Test
    public void testUseAlgorithm() {
        System.out.println("useAlgorithm");
        Algorithm instance = new AlgorithmImpl();
        LinkedList points = new LinkedList();
        points.insert(new Point2D.Double(1, 2));
        LinkedList result = instance.useAlgorithm(points);
        // first implementation should return null
        assertNull(result);
    }
    
    /**
     * Test of useAlgorithm method, of class Algorithm.
     */
    @Test
    public void testUseAlgorithm2() {
        System.out.println("useAlgorithm");
        Algorithm instance = new AlgorithmImpl2();
        LinkedList result = instance.useAlgorithm(null);
        // second implementation should return an empty list
        assertNull(result.getHead());
        assertEquals(0, result.getLength());
    }

    /**
     *
     */
    public class AlgorithmImpl implements Algorithm {

        /**
         *
         * @param points
         * @return
         */
        public LinkedList useAlgorithm(LinkedList points) {
            return null;
        }
    }
    
    /**
     *
     */
    public class AlgorithmImpl2 implements Algorithm {

        /**
         *
         * @param points
         * @return
         */
        public LinkedList useAlgorithm(LinkedList points) {
            return new LinkedList();
        }
    }
}
