package convexhull.algorithms;

import convexhull.comparators.AngleComparator;
import convexhull.datastructures.LinkedList;
import convexhull.datastructures.LinkedListNode;
import convexhull.main.ConvexHull;
import java.awt.geom.Point2D;

/**
 *
 * @author Heikki Haapala
 */
public class GrahamScan implements Algorithm {

    /**
     *
     * @param points
     * @return
     */
    @Override
    public LinkedList useAlgorithm(LinkedList points) {
        // run timer
        ConvexHull.startTimer();
        
        // find point with minimum y-coordinate O(n)
        Point2D.Double minYPoint = findMinY(points);
        // create comparator with the min y point as reference
        AngleComparator angleComparator = new AngleComparator(minYPoint);
        // sort points with merge sort O(n*log n)
        points.sort(angleComparator);
        
        //TODO algorithm itself - minYPoint is ready as the first in list
        
        // stop timer
        System.out.println("Graham Scan algorithm ran in " + ConvexHull.stopTimer() + "ms.");
        
        return points;
    }
    
    // Finds the point with minimum y coordinate.
    private Point2D.Double findMinY(LinkedList points) {
        Point2D.Double minYPoint = null;
        LinkedListNode node = points.getHead();
        while (node != null) {
            Point2D.Double point = node.getPoint();
            if (minYPoint == null || point.getY() < minYPoint.getY()) {
                minYPoint = point;
            }
            node = node.getNext();
        }
        return minYPoint;
    }
}
