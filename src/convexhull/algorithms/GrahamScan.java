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
     * Graham scan algorithm.
     *
     * Quote from Wikipedia: http://en.wikipedia.org/wiki/Graham_scan
     *
     * The first step in this algorithm is to find the point with the lowest
     * y-coordinate. If the lowest y-coordinate exists in more than one point in
     * the set, the point with the lowest x-coordinate out of the candidates
     * should be chosen. Call this point P. This step takes O(n), where n is the
     * number of points in question.
     *
     * Next, the set of points must be sorted in increasing order of the angle
     * they and the point P make with the x-axis. Any general-purpose sorting
     * algorithm is appropriate for this, for example heapsort (which is O(n log
     * n)). The algorithm proceeds by considering each of the points in the
     * sorted array in sequence. For each point, it is determined whether moving
     * from the two previously considered points to this point is a "left turn"
     * or a "right turn". If it is a "right turn", this means that the
     * second-to-last point is not part of the convex hull and should be removed
     * from consideration. This process is continued for as long as the set of
     * the last three points is a "right turn". As soon as a "left turn" is
     * encountered, the algorithm moves on to the next point in the sorted
     * array. (If at any stage the three points are collinear, one may opt
     * either to discard or to report it, since in some applications it is
     * required to find all points on the boundary of the convex hull.)
     *
     * @param points set of points to find the convex hull for
     * @return linked list of convex hull points
     */
    @Override
    public LinkedList useAlgorithm(LinkedList points) {
        if (points.getLength() < 3) {
            return points;
        }
        // run timer
        ConvexHull.startTimer();

        // find point with minimum y-coordinate O(n)
        Point2D.Double minYPoint = findMinY(points);
        // create comparator with the min y point as reference
        // sort points with merge sort O(n*log n)
        points.sort(new AngleComparator(minYPoint));

        LinkedList hullPoints = new LinkedList();

        LinkedListNode iNode = points.getHead();
        // first two points are hull points
        hullPoints.insert(iNode.getPoint());
        hullPoints.insert(iNode.getNext().getPoint());

        iNode = iNode.getNext().getNext();

        // Graham scan
        while (iNode != null) {
            Point2D.Double last = hullPoints.getTail().getPoint();
            while (triangleArea(hullPoints.getTail().getPoint(), last, iNode.getPoint()) < 0) {
                System.out.println(triangleArea(hullPoints.getTail().getPoint(), last, iNode.getPoint()));
                last = hullPoints.getTail().getPoint();
                LinkedListNode newLast = hullPoints.getTail().getPrev();
                newLast.setNext(null);
                hullPoints.getTail().setPrev(null);
                hullPoints.setLength(hullPoints.getLength() - 1);
                hullPoints.setTail(newLast);
            }
            hullPoints.insert(last);
            hullPoints.insert(iNode.getPoint());
            iNode = iNode.getNext();
        }

        hullPoints.insert(hullPoints.getHead().getPoint());

        // stop timer
        System.out.println("Graham Scan algorithm ran in " + ConvexHull.stopTimer() + "ms.");

        return hullPoints;
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
                (p1.getX() - p0.getX()) * (p2.getY() - p0.getY())
                - (p1.getY() - p0.getY()) * (p2.getX() - p0.getX());
        return triangleArea;
    }
}
