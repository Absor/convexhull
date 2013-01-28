package convexhull.algorithms;

import convexhull.datastructures.LinkedListNode;
import convexhull.datastructures.LinkedList;
import java.awt.geom.Point2D;

/**
 *
 * Class for QuickHull algo Wikipedia: Quick Hull is a method of computing the
 * convex hull of a finite set of points in the plane. It uses a divide and
 * conquer approach similar to that of QuickSort, which its name derives from.
 * Its average case complexity is considered to be O(n * log(n)), whereas in the
 * worst case it takes O(n2) (quadratic).
 *
 * @author Aleksi Markkanen
 */
public class QuickHull implements Algorithm {

    /**
     * Runs a QuickHull algorithm on the input points.
     *
     * @param points Set of points whose convex hull needs to be computed
     * @return Points that constitute the convex hull of the input set
     */
    @Override
    public LinkedList useAlgorithm(LinkedList points) {
        Point2D.Double minX = null;
        Point2D.Double maxX = null;
        Point2D.Double current = null;
        LinkedListNode node = points.getHead();
        while (node != null) { // Find the points with the smallest and greatest X coordinates
            current = node.getPoint();
            if (minX == null || minX.getX() > current.getX()) {
                minX = current;
            }
            if (maxX == null || maxX.getX() < current.getX()) {
                maxX = current;
            }
            node = node.getNext();
        }

        // Next, split the data into two lists depending on which side of the line (minX,maxX) they lie.
        // Points that lie on the line may be ignored.

        LinkedList positive = new LinkedList();
        LinkedList negative = new LinkedList();

        node = points.getHead();
        double sign;
        positive.insert(minX);
        positive.insert(maxX);
        negative.insert(maxX);
        negative.insert(minX);

        while (node != null) { // iterate over all points and split them
            sign = checkRotation(minX, maxX, node.getPoint());
            if (sign < 0) {
                negative.insert(node.getPoint());

            }
            if (sign > 0) {
                positive.insert(node.getPoint());
            }
            node = node.getNext();
        }


        LinkedList pos = iterate(positive);
        if (pos != null) {
            node = pos.getHead();
        }
        LinkedList neg = iterate(negative);
        while (node != null) {
            neg.insert(node.getPoint());
            node = node.getNext();
        }
        // Insert the first point as the last, thus closing the polygon.
        // This is in compliance with the Matlab output of the convex hull.
        neg.insert(neg.getHead().getPoint());
        return neg;

    }

    /**
     * Takes three points and returns their orientation w.r. each other
     *
     * @param A First point
     * @param B Second point
     * @param P Third point
     * @return Value is negative if points are given in clockwise orientation,
     * positive if counter-clockwise and zero if the points lie on the same line
     */
    private Double checkRotation(Point2D.Double A, Point2D.Double B, Point2D.Double P) {

        return (A.getX() * (B.getY() - P.getY()) + B.getX() * (P.getY() - A.getY()) + P.getX() * (A.getY() - B.getY()));

    }

    /**
     * Method takes as input points. First two, A and B, are parts of the convex
     * hull. Then we find a point P = argmax(dist(AB,P)). P is now a part of the
     * convex hull. Then the data set is split and iterate calls itself
     * recursively until the points are exhausted.
     *
     * @param points Input points
     * @return Convex hull of the inputted list of points
     */
    private LinkedList iterate(LinkedList points) {

        LinkedListNode head = points.getHead();
        // Only one point in the input.
        //It is included in the results list, so we will return only an empty list.

        if (points.getLength() < 2) {
            return new LinkedList();
        }
        // Two points. Second one has to be a part of the hull,
        // and is not included in the results.

        if (points.getLength() == 2) {
            LinkedList res = new LinkedList();
            res.insert(head.getNext().getPoint());
            return res;
        }
        // Three input points. Third point is the only candidate for maximizing the
        // distance from the line going through the first two.
        // It is therefore part of the convex hull and needs to be included in the results.
        if (points.getLength() == 3) {
            LinkedList res = new LinkedList();
            res.insert(head.getNext().getPoint());
            res.insert(head.getNext().getNext().getPoint());
            return res;
        }

        // Aux. variables
        Point2D.Double A = head.getPoint();
        Point2D.Double B = head.getNext().getPoint();
        Point2D.Double tempPoint, P;
        LinkedListNode current;
        LinkedList negative = new LinkedList();
        LinkedList positive = new LinkedList();
        current = head;
        double dist;
        double maxDist = 0;
        double sign;

        // Find a point from the inserted list that has the maximum distance from the line AB
        // We put P = A as a way to initialize P; this bears no actual meaning.
        P = A;
        while (current != null) {
            tempPoint = current.getPoint();
            // This is not the normal Euclidean distance, but linearly equivalent to it.
            dist = Math.abs((tempPoint.getX() - A.getX()) * (B.getY() - A.getY()) - (tempPoint.getY() - A.getY()) * (B.getX() - A.getX()));
            current = current.getNext();
            if (maxDist < dist) {
                maxDist = dist;
                P = tempPoint;
            }
        }

        // Triangle ABP is the "pivot triangle"; lines AP and BP divide the dataset,
        // if extended indefinitely.
        // P is part of the convex hull

        current = head;
        positive.insert(A);
        positive.insert(P);
        negative.insert(P);
        negative.insert(B);

        // We loop through all the inserted points.
        // Let C be our current point. If triangle APC is traversed clockwise,
        // C can contribute to the convex hull. Otherwise, it can be discarded.

        // Same is true for the triangle PBC.
        while (current != null) {
            if (checkRotation(A, P, current.getPoint()) > 0) {
                positive.insert(current.getPoint());
            }
            if (checkRotation(P, B, current.getPoint()) > 0) {
                negative.insert(current.getPoint());
            }
            current = current.getNext();
        }

        // We have divided the dataset with respect to the lines AP and BP.
        // Now we recursively iterate.

        LinkedList res1 = iterate(positive);
        LinkedList res2 = iterate(negative);

        // Merge results.

        LinkedList result = new LinkedList();
        current = res1.getHead();

        while (current != null) {
            result.insert(current.getPoint());
            current = current.getNext();
        }
        current = res2.getHead();
        while (current != null) {
            result.insert(current.getPoint());
            current = current.getNext();
        }

        return result;
    }
}
