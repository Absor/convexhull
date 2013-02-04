package convexhull.algorithms;

import convexhull.datastructures.LinkedList;
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
        Algorithm algorithm = new GiftWrapping();
        String result;
        // test with 2 points
        if ((result = Helper.testAlgorithm(algorithm, "test2", "result2")) != null) {
            fail(result);
        }
        // test with 3 points
        if ((result = Helper.testAlgorithm(algorithm, "test3", "result3")) != null ||
                (result = Helper.testAlgorithm(algorithm, "test3", "test3")) != null) {
            fail(result);
        }
        // test with 100 points
        if ((result = Helper.testAlgorithm(algorithm, "test100", "result100")) != null) {
            fail(result);
        }
        // test with 10000 points
        if ((result = Helper.testAlgorithm(algorithm, "test10000", "result10000")) != null) {
            fail(result);
        }
        // test with a ready hull of points
        if ((result = Helper.testAlgorithm(algorithm, "result100", "result100")) != null) {
            fail(result);
        }
    }
}
