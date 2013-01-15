package convexhull.algorithms;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author Heikki Haapala
 */
public interface Algorithm {

    public ArrayList<Point2D.Double> useAlgorithm(ArrayList<Point2D.Double> points);
}
