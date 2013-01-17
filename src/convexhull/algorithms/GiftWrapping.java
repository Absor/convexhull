package convexhull.algorithms;

import convexhull.main.ConvexHull;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author Heikki Haapala
 */
public class GiftWrapping implements Algorithm {

    @Override
    public ArrayList<Point2D.Double> useAlgorithm(ArrayList<Point2D.Double> points) {
        // Find the point with minimum x coordinate: O(n).
        Point2D.Double minXPoint = null;
        for (Point2D.Double point : points) {
            if (minXPoint == null || point.getX() < minXPoint.getX()) {
                minXPoint = point;
            }
        }

        ArrayList<Point2D.Double> hullPoints = new ArrayList<Point2D.Double>();
        hullPoints.add(minXPoint);

        Point2D.Double endPoint = minXPoint;
        Point2D.Double newEndPoint = null;

        // Algorithm run timer
        ConvexHull.startTimer();

        // Do until hull closes, that is, for all hull points: O(h).
        // Inner loop checks every point of the set: O(n).
        // Run time is O(n*h).
        do {
            // initial candidate for new end point
            newEndPoint = points.get(0);
            for (int i = 1; i < points.size(); i++) {
                Point2D.Double temp = points.get(i);
                // Area of triangle P0:endPoint P1:newEndPoint P2:temp is
                // (1/2) * det([[x0, x1, x2], [y0, y1, y2], [1, 1, 1]]).
                // 
                // If the area is positive then the points occur in
                // anti-clockwise order and P2 is to the left of the line P0P1.
                // Since we only care whether the area is positive,
                // we can discard multiplication by 0.5.
                double triangleArea =
                        (endPoint.getX() * newEndPoint.getY()
                        + newEndPoint.getX() * temp.getY()
                        + temp.getX() * endPoint.getY()
                        - temp.getX() * newEndPoint.getY()
                        - newEndPoint.getX() * endPoint.getY()
                        - endPoint.getX() * temp.getY());
                // If temp is left of line from endpoint to candidate,
                // update candidate.
                if (triangleArea > 0) {
                    newEndPoint = temp;
                }
            }
            // Add to hull points and update end point.
            hullPoints.add(newEndPoint);
            endPoint = newEndPoint;
        } while (!endPoint.equals(minXPoint));

        System.out.println("Gift Wrapping algorithm ran in " + ConvexHull.stopTimer() + "ns.");

        return hullPoints;
    }
}
