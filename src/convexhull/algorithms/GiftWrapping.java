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

//        pointOnHull = leftmost point in S
//   i = 0
//   repeat
//      P[i] = pointOnHull
//      endpoint = S[0]         // initial endpoint for a candidate edge on the hull
//      for j from 1 to |S|-1
//         if (endpoint == pointOnHull) or (S[j] is on left of line from P[i] to endpoint)
//            endpoint = S[j]   // found greater left turn, update endpoint
//      i = i+1
//      pointOnHull = endpoint
//   until endpoint == P[0]
        
        return null;
    }
}
