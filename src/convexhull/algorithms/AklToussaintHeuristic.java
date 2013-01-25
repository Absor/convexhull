package convexhull.algorithms;

import convexhull.datastructures.LinkedList;
import convexhull.datastructures.LinkedListNode;
import java.awt.geom.Point2D;

/**
 * Class for Akl-Toussaint heuristic
 * <p>
 * Quote from Wikipedia (http://en.wikipedia.org/wiki/Convex_hull_algorithms)
 * <p>
 * "The following simple heuristic is often used as the first step in
 * implementations of convex hull algorithms to improve their
 * performance. It is based on the efficient convex hull algorithm by Selim Akl
 * and G. T. Toussaint, 1978.
 * <p>
 * The idea is to quickly exclude many points that would not be part of the
 * convex hull anyway. This method is based on the following idea. Find the two
 * points with the lowest and highest x-coordinates, and the two points with the
 * lowest and highest y-coordinates. (Each of these operations takes O(n).)
 * These four points form a convex quadrilateral, and all points that lie in
 * this quadrilateral (except for the four initially chosen vertices) are not
 * part of the convex hull. Finding all of these points that lie in this
 * quadrilateral is also O(n), and thus, the entire operation is O(n).
 * Optionally, the points with smallest and largest sums of x- and
 * y-coordinates as well as those with smallest and largest differences of x-
 * and y-coordinates can also be added to the quadrilateral, thus forming an
 * irregular convex octagon, whose insides can be safely discarded. If the
 * points are random variables, then for a wide class of probability density
 * functions, this throw-away pre-processing step will make a convex hull
 * algorithm run in linear expected time, even if the worst-case complexity of
 * the convex hull algorithm is quadratic in n."
 *
 * @author Heikki Haapala & Aleksi Markkanen
 */
public class AklToussaintHeuristic implements Algorithm {

    private Point2D.Double minX = null, maxX = null,
            minY = null, maxY = null,
            minCoordSum = null, maxCoordSum = null,
            minCoordDiff = null, maxCoordDiff = null;

    /**
     * The main method that runs the algorithm described in the class
     * description.
     *
     * @param points set of points to run the algorithm on
     * @return points remaining in set after running the heuristic
     */
    @Override
    public LinkedList useAlgorithm(LinkedList points) {
        this.setPoints(points);

        System.out.println(minX + "\n" + maxX + "\n" + minY + "\n" + maxY + "\n" + minCoordSum + "\n" + maxCoordSum + "\n" + minCoordDiff + "\n" + maxCoordDiff);

        return null;
    }

    // Helper method for finding the points needed.
    private void setPoints(LinkedList points) {
        LinkedListNode node = points.getHead();
        while (node != null) {
            Point2D.Double point = node.getPoint();
            // minimum and maximum x value
            if (minX == null || point.getX() < minX.getX()) {
                minX = point;
            }
            if (maxX == null || point.getX() > maxX.getX()) {
                maxX = point;
            }
            // minimum and maximum y value
            if (minY == null || point.getY() < minY.getY()) {
                minY = point;
            }
            if (maxY == null || point.getY() > maxY.getY()) {
                maxY = point;
            }
            // minimum and maximum sum of coordinates
            if (minCoordSum == null
                    || point.getX() + point.getY() < minCoordSum.getX() + minCoordSum.getY()) {
                minCoordSum = point;
            }
            if (maxCoordSum == null
                    || point.getX() + point.getY() > maxCoordSum.getX() + maxCoordSum.getY()) {
                maxCoordSum = point;
            }
            // minimum and maximum differences of x- and y-coordinates
            // TODO: a-b or |a-b| or ||a|-|b|| or something else?
            if (minCoordDiff == null
                    || point.getX() - point.getY() < minCoordDiff.getX() - minCoordDiff.getY()) {
                minCoordDiff = point;
            }
            if (maxCoordDiff == null
                    || point.getX() - point.getY() > maxCoordDiff.getX() - maxCoordDiff.getY()) {
                maxCoordDiff = point;
            }
            node = node.getNext();
        }
    }
}
