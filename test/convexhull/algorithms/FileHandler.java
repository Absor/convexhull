/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convexhull.algorithms;

import convexhull.datastructures.LinkedList;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author heha
 */
public class FileHandler {
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
}
