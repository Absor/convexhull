/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convexhull.algorithms;

import convexhull.datastructures.LinkedList;
import convexhull.datastructures.LinkedListNode;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author heha
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

    // Checks that two linked lists contain same points.
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
}
