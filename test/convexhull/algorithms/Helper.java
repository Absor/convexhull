package convexhull.algorithms;

import convexhull.datastructures.LinkedList;
import convexhull.datastructures.LinkedListNode;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Contains utility methods for tests.
 *
 * @author Heikki Haapala
 */
public class Helper {

    // Same method as in ConvexHull class but public
    public static LinkedList parseFile(String filename) throws Exception {
        File file = new File(filename);
        LinkedList points = new LinkedList();

        // open file
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            // remove leading and trailing white spaces
            line = line.trim();
            // ignore lines that start with # or /
            if (line.charAt(0) != '#' && line.charAt(0) != '/') {
                String[] split = line.split(" ");
                double x = Double.parseDouble(split[0]);
                double y = Double.parseDouble(split[1]);
                Point2D.Double newPoint = new Point2D.Double(x, y);
                // add to list
                points.insert(newPoint);
            }
        }
        // close file
        br.close();

        return points;
    }

    // checks that two linked lists contain same points.
    public static boolean setsMatch(LinkedList result1, LinkedList result2) {
        if (result1.getLength() != result2.getLength()) {
            return false;
        }

        // check that algorithm result set matches the known results
        LinkedListNode node1 = result1.getHead();
        while (node1 != null) {
            Point2D.Double point1 = node1.getPoint();
            LinkedListNode node2 = result2.getHead();
            boolean found = false;
            while (node2 != null) {
                Point2D.Double point2 = node2.getPoint();
                if (point1.equals(point2)) {
                    found = true;
                }
                node2 = node2.getNext();
            }
            if (!found) {
                return false;
            }
            node1 = node1.getNext();
        }
        return true;
    }

    // checks that two lists have the same points and in same order
    public static boolean sameOrder(LinkedList list1, ArrayList<Point2D.Double> list2) {
        // if the lengths don't match
        if (list1.getLength() != list2.size()) {
            return false;
        }

        // go through the lists
        LinkedListNode list1Node = list1.getHead();
        for (Point2D.Double point2 : list2) {
            // compare all points: if not the same, return false
            if (!list1Node.getPoint().equals(point2)) {
                return false;
            }
            list1Node = list1Node.getNext();
        }
        return true;
    }
    
    public static String testAlgorithm(Algorithm algorithm, String inputfile, String resultfile) {
        System.out.println("useAlgorithm");
        // input set
        LinkedList input = null;
        try {
            input = Helper.parseFile(inputfile);
        } catch (Exception ex) {
            return "Could not parse input file.";
        }
        // known results
        LinkedList result = null;
        try {
            result = Helper.parseFile(resultfile);
        } catch (Exception ex) {
            return "Could not parse result file.";
        }

        // algorithm results
        LinkedList algoResult = algorithm.useAlgorithm(input);

        if (!Helper.setsMatch(result, algoResult)) {
            return "Algorithm returns wrong result with: " + inputfile + " and " + resultfile;
        }
        return null;
    }
    
    public static String testAlgorithmCollinearPoints(Algorithm algorithm) {
        System.out.println("useAlgorithm");
        String inputfile = "testmaterial/test4";
        // input set
        LinkedList input = null;
        try {
            input = Helper.parseFile(inputfile);
        } catch (Exception ex) {
            return "Could not parse input file.";
        }

        // algorithm results
        LinkedList algoResult = algorithm.useAlgorithm(input);

        if (!(algoResult.contains(new Point2D.Double(-1.0, -1.0)) && algoResult.contains(new Point2D.Double(3.0, 3.0)))) {
            return "Algorithm returns wrong result with four collinear points.";
        }
        return null;
    }
}
