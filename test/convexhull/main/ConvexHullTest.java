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

    /**
     *
     */
    public ConvexHullTest() {
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
     * Test of main method, of class ConvexHull.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = {"testmaterial/test100", "at", "gift", "OUT", "nodraw"};
        ConvexHull.main(args);
        // just should not crash :P
    }
}
