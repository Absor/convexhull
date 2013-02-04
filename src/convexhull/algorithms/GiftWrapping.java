package convexhull.algorithms;

import convexhull.datastructures.LinkedList;
import convexhull.datastructures.LinkedListNode;
import convexhull.main.ConvexHull;
import java.awt.geom.Point2D;

/**
 * Class for Gift Wrapping (aka Jarvis's march) algorithm. Wikipedia article:
 * http://en.wikipedia.org/wiki/Gift_wrapping_algorithm <p> Run time: O(n*h)
 * where n is the number of input points and h is the number of hull points.
 * This makes this algorithm an output-sensitive algorithm. <p> Wikipedia quote:
 * "The gift wrapping algorithm begins with i=0 and a point p_0 known to be on
 * the convex hull, e.g., the leftmost point, and selects the point p_(i+1) such
 * that all points are to the right of the line pi pi+1. This point may be found
 * in O(n) time by comparing polar angles of all points with respect to point pi
 * taken for the center of polar coordinates. Letting i=i+1, and repeating with
 * until one reaches p_h=p_0 again yields the convex hull in h steps. In two
 * dimensions, the gift wrapping algorithm is similar to the process of winding
 * a string (or wrapping paper) around the set of points."
 *
 * @author Heikki Haapala
 */
public class GiftWrapping implements Algorithm {

    /**
     * Processes point set using Gift Wrapping algorithm and returns the points
     * of the convex hull of the input set.
     *
     * @param points set of points to run the algorithm on
     * @return convex hull points
     */
    @Override
    public LinkedList useAlgorithm(LinkedList points) {
        if (points.getLength() < 3) {
            return points;
        }
        
        // Algorithm run timer
        ConvexHull.startTimer();

        // Find the point with minimum x coordinate: O(n).
        Point2D.Double minXPoint = findMinX(points);

        LinkedList hullPoints = new LinkedList();
        hullPoints.insert(minXPoint);

        Point2D.Double endPoint = minXPoint;
        Point2D.Double newEndPoint = null;

        // Do until hull closes, that is, for all hull points: O(h).
        // Inner loop checks every point of the set: O(n).
        // Run time is O(n*h).
        do {
            // initial candidate for new end point
            LinkedListNode current = points.getHead();
            newEndPoint = current.getPoint();
            current = current.getNext();
            while (current != null) {
                Point2D.Double temp = current.getPoint();
                // ignore if temp is the current end point
                if (!temp.equals(endPoint)) {
                    double triangleArea = triangleArea(endPoint, newEndPoint, temp);
                    // If temp is left of line from endpoint to candidate
                    // (area positive), update candidate.
                    if (triangleArea >= 0) {
                        newEndPoint = temp;
                    }
                }
                current = current.getNext();
            }
            System.out.println(endPoint);
            // Add to hull points and update end point.
            hullPoints.insert(newEndPoint);
            endPoint = newEndPoint;
        } while (!endPoint.equals(minXPoint));

        System.out.println("Gift Wrapping algorithm ran in " + ConvexHull.stopTimer() + "ms.");

        return hullPoints;
    }

    // Finds the point with minimum x coordinate.
    private Point2D.Double findMinX(LinkedList points) {
        Point2D.Double minXPoint = null;
        LinkedListNode node = points.getHead();
        while (node != null) {
            Point2D.Double point = node.getPoint();
            if (minXPoint == null || point.getX() < minXPoint.getX()) {
                minXPoint = point;
            }
            node = node.getNext();
        }
        return minXPoint;
    }

    /*
     * Area of triangle P0P1P2 is
     * (1/2) * det([[x0, x1, x2], [y0, y1, y2], [1, 1, 1]]).
     * 
     * If the area is positive then the points occur in
     * anti-clockwise order and P2 is to the left of the line P0P1.
     * Since we only care whether the area is positive,
     * we can discard multiplication by 0.5.
     */
    private double triangleArea(Point2D.Double p0, Point2D.Double p1, Point2D.Double p2) {
        double triangleArea =
                (p1.getX() - p0.getX()) * (p2.getY() - p0.getY())
                - (p1.getY() - p0.getY()) * (p2.getX() - p0.getX());
        return triangleArea;
    }
}
