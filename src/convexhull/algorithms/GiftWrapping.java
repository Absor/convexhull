package convexhull.algorithms;

import convexhull.datastructures.LinkedList;
import convexhull.datastructures.LinkedListNode;
import convexhull.main.ConvexHull;
import java.awt.geom.Point2D;

/**
 *
 * @author Heikki Haapala
 */
public class GiftWrapping implements Algorithm {

    /**
     *
     * @param points set of points to run the algorithm on
     * @return convex hull points
     */
    @Override
    public LinkedList useAlgorithm(LinkedList points) {
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
                    if (triangleArea > 0) {
                        newEndPoint = temp;
                    }
                }
                current = current.getNext();
            }
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

    // Area of triangle P0P1P2 is
    // (1/2) * det([[x0, x1, x2], [y0, y1, y2], [1, 1, 1]]).
    // 
    // If the area is positive then the points occur in
    // anti-clockwise order and P2 is to the left of the line P0P1.
    // Since we only care whether the area is positive,
    // we can discard multiplication by 0.5.
    private double triangleArea(Point2D.Double p0, Point2D.Double p1, Point2D.Double p2) {
        double triangleArea =
                (p0.getX() * p1.getY()
                + p1.getX() * p2.getY()
                + p2.getX() * p0.getY()
                - p2.getX() * p1.getY()
                - p1.getX() * p0.getY()
                - p0.getX() * p2.getY());
        return triangleArea;
    }
}
