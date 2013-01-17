package convexhull.main;

import convexhull.algorithms.AklToussaintHeuristic;
import convexhull.algorithms.Algorithm;
import convexhull.algorithms.GiftWrapping;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Heikki Haapala
 */
public class ConvexHull {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // parsing
        ArrayList<Point2D.Double> points = parseFile(args[0]);
        
        // use akl-toussaint (argument)
//        Algorithm akltoussaint = new AklToussaintHeuristic();
//        points = akltoussaint.useAlgorithm(points);
        
        // use the chosen algorithm (argument)
        
        // gift wrapping
        Algorithm giftWrapping = new GiftWrapping();
        points = giftWrapping.useAlgorithm(points);
        
        // for testing
        System.out.println(points);
    }

    private static ArrayList<Point2D.Double> parseFile(String pathname) {
        File file = new File(pathname);
        ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>();

        try {
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
                    points.add(newPoint);
                }
            }
            // close file
            br.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return points;
    }
}
