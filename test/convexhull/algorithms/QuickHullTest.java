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
public class QuickHullTest {

    /**
     *
     */
    public QuickHullTest() {
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
     * Test of useAlgorithm method, of class QuickHull.
     */
    @Test
    public void testUseAlgorithm() {
        Algorithm algorithm = new QuickHull();
        String result;
        // test with 2 points
        if ((result = Helper.testAlgorithm(algorithm, "testmaterial/test2", "testmaterial/result2")) != null) {
            fail(result);
        }
        // test with 3 points
        if (!((result = Helper.testAlgorithm(algorithm, "testmaterial/test3", "testmaterial/result3")) == null ||
                (result = Helper.testAlgorithm(algorithm, "testmaterial/test3", "testmaterial/test3")) == null)) {
            fail(result);
        }
        // test with 4 collinear points
        if ((result = Helper.testAlgorithmCollinearPoints(algorithm)) != null) {
            fail(result);
        }
        // test with 100 points
        if ((result = Helper.testAlgorithm(algorithm, "testmaterial/test100", "testmaterial/result100")) != null) {
            fail(result);
        }
        // test with 10000 points
        if ((result = Helper.testAlgorithm(algorithm, "testmaterial/test10000", "testmaterial/result10000")) != null) {
            fail(result);
        }
        // test with a ready hull of points
        if ((result = Helper.testAlgorithm(algorithm, "testmaterial/result100", "testmaterial/result100")) != null) {
            fail(result);
        }
    }
}
