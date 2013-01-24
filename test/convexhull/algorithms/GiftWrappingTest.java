/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
        System.out.println("useAlgorithm");
        // input set
        LinkedList input = null;
        try {
            input = Helper.parseFile("testfile");
        } catch (Exception ex) {
            fail("Could not parse input file.");
        }
        // known results
        LinkedList result = null;
        try {
            result = Helper.parseFile("testresult");
        } catch (Exception ex) {
            fail("Could not parse result file.");
        }

        GiftWrapping giftAlgo = new GiftWrapping();

        // algorithm results
        LinkedList algoResult = giftAlgo.useAlgorithm(input);

        if (Helper.setsMatch(result, algoResult)) {
            fail("Algorithm returns wrong result.");
        }
    }
}
