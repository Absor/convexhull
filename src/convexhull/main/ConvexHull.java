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
import java.util.Scanner;

/**
 *
 * @author Heikki Haapala
 */
public class ConvexHull {

    private static long startTime;

    /**
     *
     * @param args Requires arguments in order: infile at/noat algorithm outfile
     */
    public static void main(String[] args) {
        LinkedList points = null;

        Scanner in = new Scanner(System.in);
        String input;
        boolean ok = false;

        if (args.length >= 1) {
            input = args[0];
        } else {
            input = "";
        }

        while (!ok) {
            // parsing
            try {
                points = parseFile(input);
                ok = true;
            } catch (Exception ex) {
                System.out.println("Could not read input file. Filename was: \"" + input + "\"");
                System.out.print("Input a filename to open: ");
                input = in.nextLine();
            }
            System.out.println();
        }

        if (args.length >= 2) {
            input = args[1];
        } else {
            input = "";
        }
        ok = false;

        while (!ok) {
            // Akl-Toussaint heuristic
            if (input.equals("at")) {
                System.out.println("Using Akl-Toussaint heuristic.");
                Algorithm akltoussaint = new AklToussaintHeuristic();
                points = akltoussaint.useAlgorithm(points);
                ok = true;
            } else if (input.equals("noat")) {
                System.out.println("Not using Akl-Toussaint heuristic.");
                ok = true;
            } else {
                System.out.println("Bad argument: \"" + input + "\"");
                System.out.println("Valid arguments:");
                System.out.println("at : use Akl-Toussaint heuristic.");
                System.out.println("noat : don't use Akl-Toussaint heuristic.");
                System.out.print("Input new argument: ");
                input = in.nextLine();
            }
            System.out.println();
        }

        if (args.length >= 3) {
            input = args[2];
        } else {
            input = "";
        }
        ok = false;

        while (!ok) {
            // use the chosen algorithm
            if (input.equals("gift")) {
                System.out.println("Using Gift Wrapping algorithm.");
                Algorithm giftWrapping = new GiftWrapping();
                points = giftWrapping.useAlgorithm(points);
                ok = true;
            } else if (input.equals("quick")) {
                System.out.println("Using QuickHull algorithm.");
                Algorithm quickHull = new QuickHull();
                points = quickHull.useAlgorithm(points);
                ok = true;
            } else if (input.equals("graham")) {
                System.out.println("Using Graham scan algorithm.");
                Algorithm grahamScan = new GrahamScan();
                points = grahamScan.useAlgorithm(points);
                ok = true;
            } else {
                System.out.println("Bad argument: \"" + input + "\"");
                System.out.println("Valid arguments:");
                System.out.println("gift : use Gift Wrapping algorithm.");
                System.out.println("quick : use QuickHull algorithm.");
                System.out.println("graham : use Graham scan algorithm.");
                System.out.print("Input new argument: ");
                input = in.nextLine();
            }
            System.out.println();
        }

        if (args.length >= 4) {
            input = args[3];
        } else {
            input = "";
        }
        ok = false;

        while (!ok) {
            try {
                saveToFile(input, points);
                ok = true;
            } catch (Exception ex) {
                System.out.println("Could not write to output file. Filename was: \"" + input + "\"");
                System.out.print("Input a new filename: ");
                input = in.nextLine();
            }
            System.out.println();
        }
    }

    /**
     *
     * @param filename
     * @return
     * @throws Exception
     */
    private static LinkedList parseFile(String filename) throws Exception {
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
    private static void saveToFile(String filename, LinkedList points) throws Exception {
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
