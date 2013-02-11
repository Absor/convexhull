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
public class AklToussaintHeuristicTest {

    /**
     *
     */
    public AklToussaintHeuristicTest() {
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
     * Test of useAlgorithm method, of class AklToussaintHeuristic. Testing with
     * akl-toussaint and gift wrapping algorithm to know if we still get the
     * right results.
     */
    @Test
    public void testUseAlgorithm() {
        // test with 2 points
        testAlgorithm("testmaterial/test2", "testmaterial/result2");
        // test with 3 points
        testAlgorithm("testmaterial/test3", "testmaterial/test3");
        // test with 100 points
        testAlgorithm("testmaterial/test100", "testmaterial/result100");
        // test with 10000 points
        testAlgorithm("testmaterial/test10000", "testmaterial/result10000");
        // test with a ready hull of points
        testAlgorithm("testmaterial/result100", "testmaterial/result100");
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

        AklToussaintHeuristic akltoussaint = new AklToussaintHeuristic();
        Algorithm hullAlgo = new GiftWrapping();

        // first through akl-toussaint and then gift wrapping
        LinkedList aklResult = akltoussaint.useAlgorithm(input);
        LinkedList hull = hullAlgo.useAlgorithm(aklResult);

        // check results
        if (!Helper.setsMatch(result, hull)) {
            fail("Algorithm returns wrong result with: " + inputfile + " and " + resultfile);
        }
    }
}
