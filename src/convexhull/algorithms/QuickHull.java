package convexhull.algorithms;

import convexhull.datastructures.LinkedListNode;
import convexhull.datastructures.LinkedList;
import convexhull.main.ConvexHull;
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
        ConvexHull.startTimer();
        if (points.getLength() < 4) {
            return points;
        }
        Point2D.Double minX = findMin(points.getHead());
        Point2D.Double maxX = findMax(points.getHead());

        LinkedList positive = new LinkedList();
        LinkedList negative = new LinkedList();

        pruneInapplicablePoints(minX, minX, maxX, points.getHead(), positive, negative);
        return mergeResults(iterate(positive), iterate(negative));
        /*
         System.out.println("Algorithm ran in " + ConvexHull.stopTimer() + "ms");
         */
    }

    /**
     * Finds the point with the maximum x coordinate from the inputted list
     *
     * @param head 1st element of the list
     * @return Pointer to the maximum element
     */
    private Point2D.Double findMax(LinkedListNode head) {
        Point2D.Double max = null;
        for (LinkedListNode current = head; current != null; current = current.getNext()) {
            if (max == null || max.getX() < current.getPoint().getX()) {
                max = current.getPoint();
            }
        }
        return max;
    }

    /**
     * Finds the point with the minimum x coordinate from the inputted list
     *
     * @param head 1st element of the list
     * @return Pointer to the minimum element
     */
    private Point2D.Double findMin(LinkedListNode head) {
        Point2D.Double min = null;
        for (LinkedListNode current = head; current != null; current = current.getNext()) {
            if (min == null || min.getX() > current.getPoint().getX()) {
                min = current.getPoint();
            }

        }
        return min;

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

        // Check for trivial input.
        if (points.getLength() < 4) {
            return trivialHull(points);
        }
        // Aux. variables
        Point2D.Double A = points.getHead().getPoint();
        Point2D.Double B = points.getHead().getNext().getPoint();
        LinkedList negative = new LinkedList();
        LinkedList positive = new LinkedList();

        // Find a point from the inserted list that has the maximum distance from the line AB     
        Point2D.Double P = findPivotPoint(A, B, points.getHead());

        // If no candidates are found, we return only the endpoints of the line AB and
        // terminate the recursion.
        if (P == null) {
            positive.insert(A);
            positive.insert(B);
            return positive;
        }
        // Split the data:
        pruneInapplicablePoints(A, B, P, points.getHead(), positive, negative);

        // We have divided the dataset with respect to the lines AP and BP.
        // Now we recursively iterate.

        LinkedList res1 = iterate(positive);
        LinkedList res2 = iterate(negative);
        return mergeResults(res1, res2);

    }

    /**
     * Merges two lists into one.
     *
     * @param res1 1st list
     * @param res2 2nd list
     * @return concatenation of res1 and res2
     */
    private LinkedList mergeResults(LinkedList res1, LinkedList res2) {
        LinkedList result = new LinkedList();
        LinkedListNode current = null;
        if (res1 != null) {
            current = res1.getHead();

            while (current != null) {
                result.insert(current.getPoint());
                current = current.getNext();
            }
        }
        if (res2 != null) {
            current = res2.getHead();
            while (current != null) {
                result.insert(current.getPoint());
                current = current.getNext();
            }
        }
        return result;
    }

    /**
     * If input for iterate() is trivial, this method is called
     *
     * @param points List of points of length <4.
     * @return Empty list for <1 point, otherwise trivial hull of the input
     * list.
     */
    private LinkedList trivialHull(LinkedList points) {
        LinkedList res = new LinkedList();
        if (points.getLength() < 2) {
            return res;
        } else if (points.getLength() == 2) {
            res.insert(points.getHead().getNext().getPoint());
            return res;
        } else {
            res.insert(points.getHead().getNext().getNext().getPoint());
            res.insert(points.getHead().getNext().getPoint());
            return res;
        }
    }

    /**
     * Finds a point P from the inputted list so that P = argmax(dist(AB,P)). If
     * there is no P so that dist(AB,P) > 0, return null.
     *
     * @param A First point
     * @param B Second point
     * @param head List
     * @return Point P, null if N/A
     */
    private Point2D.Double findPivotPoint(Point2D.Double A, Point2D.Double B, LinkedListNode head) {
        Point2D.Double tempPoint, P;
        double dist;
        double maxDist = 0;
        P = null;
        for (LinkedListNode current = head; current != null; current = current.getNext()) {
            tempPoint = current.getPoint();
            // This is not the normal Euclidean distance, but linearly equivalent to it.
            dist = Math.abs((tempPoint.getX() - A.getX()) * (B.getY() - A.getY()) - (tempPoint.getY() - A.getY()) * (B.getX() - A.getX()));
            if (maxDist < dist) {
                maxDist = dist;
                P = tempPoint;
            }
        }
        return P;
    }

    /**
     *
     * We loop through inputted list. We can disregard points that lie inside
     * the triangle ABP.
     *
     * If a point lies on the other side of the line AP (conversely, line BP)
     * than the interior points of the aforementioned triangle, it is included
     * in the list "positive" (conversely, the list "negative").
     *
     * @param A First point of the triangle
     * @param B 2nd point of the triangle
     * @param P pivot point
     * @param head beginning of the list of points
     * @param positive 1st list
     * @param negative 2nd list
     *
     */
    private void pruneInapplicablePoints(Point2D.Double A, Point2D.Double B, Point2D.Double P, LinkedListNode head, LinkedList positive, LinkedList negative) {
        // Initialize the lists..
        positive.insert(A);
        positive.insert(P);
        negative.insert(P);
        negative.insert(B);
        // Loop throught the points.
        for (LinkedListNode current = head; current != null; current = current.getNext()) {
            if (checkRotation(A, P, current.getPoint()) > 0) {
                positive.insert(current.getPoint());
            }
            if (checkRotation(P, B, current.getPoint()) > 0) {
                negative.insert(current.getPoint());
            }
        }
    }
}
