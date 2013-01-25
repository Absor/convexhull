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
        LinkedListNode node = points.getHead().getNext();
        while (node != null) {
            Point2D.Double point = node.getPoint();
            
            node = node.getNext();
        }

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
                (p0.getX() * p1.getY()
                + p1.getX() * p2.getY()
                + p2.getX() * p0.getY()
                - p2.getX() * p1.getY()
                - p1.getX() * p0.getY()
                - p0.getX() * p2.getY());
        return triangleArea;
    }
}
