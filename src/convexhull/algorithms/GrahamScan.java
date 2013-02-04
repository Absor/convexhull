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
        // sort points with merge sort O(n*log n)
        points.sort(new AngleComparator(minYPoint));

        LinkedList hullPoints = new LinkedList();

        LinkedListNode iNode = points.getHead();
        // first two points are hull points
        hullPoints.insert(iNode.getPoint());
        hullPoints.insert(iNode.getNext().getPoint());

        iNode = iNode.getNext().getNext();

        while (iNode != null) {
            Point2D.Double last = hullPoints.getTail().getPoint();
            while (triangleArea(hullPoints.getTail().getPoint(), last, iNode.getPoint()) <= 0) {
                System.out.println(triangleArea(hullPoints.getTail().getPoint(), last, iNode.getPoint()));
                last = hullPoints.getTail().getPoint();
                LinkedListNode newLast = hullPoints.getTail().getPrev();
                newLast.setNext(null);
                hullPoints.getTail().setPrev(null);
                hullPoints.setLength(hullPoints.getLength() - 1);
                hullPoints.setTail(newLast);
            }
            System.out.println("");
            hullPoints.insert(last);
            hullPoints.insert(iNode.getPoint());
            iNode = iNode.getNext();
        }

//        // find index k1 of first point not equal to points[0]
//        int k1;
//        for (k1 = 1; k1 < N; k1++)
//            if (!points[0].equals(points[k1])) break;
//        if (k1 == N) return;        // all points equal
//
//        // find index k2 of first point not collinear with points[0] and points[k1]
//        int k2;
//        for (k2 = k1 + 1; k2 < N; k2++)
//            if (Point2D.ccw(points[0], points[k1], points[k2]) != 0) break;
//        hull.push(points[k2-1]);    // points[k2-1] is second extreme point
//
//        // Graham scan; note that points[N-1] is extreme point different from points[0]
//        for (int i = k2; i < N; i++) {
//            Point2D top = hull.pop();
//            while (Point2D.ccw(hull.peek(), top, points[i]) <= 0) {
//                top = hull.pop();
//            }
//            hull.push(top);
//            hull.push(points[i]);
//        }


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
