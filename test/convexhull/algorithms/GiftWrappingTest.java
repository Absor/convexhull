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
        // test with 2 points
        testAlgorithm("test2", "result2");
        // test with 3 points
        testAlgorithm("test3", "result3");
        // test with 100 points
        testAlgorithm("test100", "result100");
        // test with 10000 points
        testAlgorithm("test10000", "result10000");
    }
    
    private void testAlgorithm(String inputfile, String resultfile) {
        System.out.println("useAlgorithm");
        // input set
        LinkedList input = null;
        try {
            input = Helper.parseFile(inputfile);
        } catch (Exception ex) {
            fail("Could not parse input file.");
        }
        // known results
        LinkedList result = null;
        try {
            result = Helper.parseFile(resultfile);
        } catch (Exception ex) {
            fail("Could not parse result file.");
        }

        GiftWrapping algorithm = new GiftWrapping();

        // algorithm results
        LinkedList algoResult = algorithm.useAlgorithm(input);

        if (!Helper.setsMatch(result, algoResult)) {
            fail("Algorithm returns wrong result.");
        }
    }
}
