package convexhull.algorithms;

import convexhull.datastructures.LinkedListNode;
import convexhull.datastructures.LinkedList;
import java.awt.geom.Point2D;

/**
 *
 * @author Aleksi Markkanen
 */
public class QuickHull implements Algorithm {

    @Override
    public LinkedList useAlgorithm(LinkedList points) {
        Point2D.Double minX = null;
        Point2D.Double maxX = null;
        Point2D.Double current = null;
        LinkedListNode node = points.getHead();
        while (node != null) { // Find the points with the smallest and greatest X coordinates
            current = node.getPoint();
            if (minX == null || minX.getX() < current.getX()) {
                minX = current;
            }
            if (maxX == null || maxX.getX() < current.getX()) {
                maxX = current;
            }
            node = node.getNext();
        }

        // Next, split the data into two lists depending on which side of the line minX - maxX they lie.
        // Points that lie on the line may be ignored.

        LinkedList positive = new LinkedList();
        LinkedList negative = new LinkedList();

        node = points.getHead();
        double sign;
        positive.insert(minX);
        positive.insert(maxX);
        negative.insert(maxX);
        negative.insert(minX);
        System.out.println("Found Min and Max X.");
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
        System.out.println("Iterating..");
        LinkedList pos = iterate(positive);
        if (pos != null) {
            node = pos.getHead();
        }
        LinkedList neg = iterate(negative);
        while (node != null) {
            neg.insert(node.getPoint());
            node = node.getNext();
        }
        return neg;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Double checkRotation(Point2D.Double A, Point2D.Double B, Point2D.Double P) {

        return (A.getX() * B.getY()
                + B.getX() * P.getY()
                + P.getX() * B.getY()
                - P.getX() * A.getY()
                - A.getX() * B.getY()
                - B.getX() * P.getY());
    }

    private LinkedList iterate(LinkedList points) {
        LinkedListNode head = points.getHead();
        if (head == null || head.getNext() == null || head.getNext().getNext() == null) {
            return null;
        }
        Point2D.Double A = head.getPoint();
        Point2D.Double B = head.getNext().getPoint();
        Point2D.Double tempPoint, P;
        LinkedListNode current;
        LinkedList negative = new LinkedList();
        LinkedList positive = new LinkedList();
        LinkedList result = new LinkedList();
        current = head;
        double dist;
        double maxDist = 0;
        double sign;
       // P = null;

        // Find a point from the inserted list that has the maximum distance from the line AB
        P = A;
        while (current != null) {
            tempPoint = current.getPoint();
            double normalLength = Math.hypot(B.getX() - A.getX(), B.getY() - A.getY());
            dist = Math.abs((tempPoint.getX() - A.getX()) * (B.getY() - A.getY()) - (tempPoint.getY() - A.getY()) * (B.getX() - A.getX())) / normalLength;
            current = current.getNext();
            if (maxDist < dist) {
                maxDist = dist;
                P = tempPoint;
            }
        }
        System.out.println("max dist: " + maxDist + " - P X coordinate: " + P.getX());
        // Triangle ABP is the pivot triangle; lines AP and BP divide the dataset
        // P is part of the convex hull

        current = head;
        positive.insert(A);
        positive.insert(P);
        negative.insert(P);
        negative.insert(B);

        while (current != null) {
            if (checkRotation(A, P, current.getPoint()) > 0) {
                positive.insert(current.getPoint());
            }
            if (checkRotation(B, P, current.getPoint()) > 0) {
                negative.insert(current.getPoint());
            }
            current = current.getNext();

        }

        // Recursively iterate..

        LinkedList res1 = iterate(positive);
        LinkedList res2 = iterate(negative);

        // Merge results..

        result.insert(A);
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
        result.insert(B);
        return result;
    }
}
