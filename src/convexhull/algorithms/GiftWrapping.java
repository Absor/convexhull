package convexhull.algorithms;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author
 */
public class GiftWrapping implements Algorithm {

    @Override
    public ArrayList<Point2D.Double> useAlgorithm(ArrayList<Point2D.Double> points) {
        // find point with minimum x coordinate - O(n)
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

        do {
            // initial candidate for new end point
            newEndPoint = points.get(0);
            for (int i = 1; i < points.size(); i++) {
                Point2D.Double temp = points.get(i);
                // area of triangle P0:endPoint P1:newEndPoint P2:temp
                // (1/2) * determinant of [[x0, x1, x2], [y0, y1, y2], [1, 1, 1]]
                // if the area is positive then the points occur in
                // anti-clockwise order and P2 is to the left of the line P0P1
                double triangleArea =
                        0.5 * (endPoint.getX() * newEndPoint.getY()
                        + newEndPoint.getX() * temp.getY()
                        + temp.getX() * endPoint.getY()
                        - temp.getX() * newEndPoint.getY()
                        - newEndPoint.getX() * endPoint.getY()
                        - endPoint.getX() * temp.getY());
                // if temp is left of line from endpoint to candidate, update candidate
                if (triangleArea > 0) {
                    newEndPoint = temp;
                }
            }
            // add to hull points and update end point
            hullPoints.add(newEndPoint);
            endPoint = newEndPoint;
        } while (!endPoint.equals(minXPoint)); // do until hull closes

        return hullPoints;
    }
}
