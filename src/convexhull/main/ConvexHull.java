package convexhull.main;

import convexhull.algorithms.AklToussaintHeuristic;
import convexhull.algorithms.Algorithm;
import convexhull.algorithms.GiftWrapping;
import convexhull.algorithms.GrahamScan;
import convexhull.algorithms.QuickHull;
import convexhull.datastructures.LinkedList;
import convexhull.datastructures.LinkedListNode;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Heikki Haapala
 */
public class ConvexHull {

    private static long startTime;

    /**
     *
     * @param args Requires arguments in order: filename at/noat algorithm
     */
    public static void main(String[] args) {
        // parsing
        LinkedList points;
        try {
            points = parseFile(args[0]);
        } catch (Exception ex) {
            System.out.println("Error handling input file: " + ex);
            return;
        }

        // Akl-Toussaint heuristic
        if (args[1].equals("at")) {
            System.out.println("Using Akl-Toussaint.");
            Algorithm akltoussaint = new AklToussaintHeuristic();
            points = akltoussaint.useAlgorithm(points);
        } else if (args[1].equals("noat")) {
            System.out.println("Not using Akl-Toussaint.");
        } else {
            System.out.println("Bad argument: " + args[1]);
            return;
        }

        // use the chosen algorithm
        if (args[2].equals("gift")) {
            System.out.println("Using Gift Wrapping algorithm.");
            Algorithm giftWrapping = new GiftWrapping();
            points = giftWrapping.useAlgorithm(points);
        } else if (args[2].equals("quick")) {
            System.out.println("Using QuickHull algorithm.");
            Algorithm quickHull = new QuickHull();
            points = quickHull.useAlgorithm(points);
        } else if (args[2].equals("graham")) {
            System.out.println("Using Graham Scan algorithm.");
            Algorithm grahamScan = new GrahamScan();
            points = grahamScan.useAlgorithm(points);
        } else {
            System.out.println("Bad argument: " + args[2]);
            return;
        }

        try {
            saveToFile(args[3], points);
        } catch (Exception ex) {
            System.out.println("Error handling output file: " + ex);
        }
    }

    /**
     *
     * @param filename
     * @return
     * @throws Exception
     */
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

    /**
     *
     * @param filename
     * @param points
     * @throws Exception
     */
    public static void saveToFile(String filename, LinkedList points) throws Exception {
        File file = new File(filename);

        // open file
        BufferedWriter vw = new BufferedWriter(new FileWriter(file));

        // write to file
        LinkedListNode node = points.getHead();
        while (node != null) {
            Point2D.Double point = node.getPoint();
            vw.append(point.getX() + " " + point.getY());
            vw.newLine();
            node = node.getNext();
        }
        
        // close file
        vw.close();
    }

    /**
     *
     */
    public static void startTimer() {
        startTime = System.currentTimeMillis();
    }

    /**
     *
     * @return
     */
    public static long stopTimer() {
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
