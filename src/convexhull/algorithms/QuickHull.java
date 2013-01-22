package convexhull.algorithms;

import convexhull.datastructures.LinkedListNode;
import convexhull.datastructures.LinkedList;
import java.awt.geom.Point2D;

/**
 *
 * @author Aleksi Markkanen
 */
public class QuickHull implements Algorithm {

    /**
     *
     * @param points
     * @return
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
            System.out.println(sign);
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
        //if(true) return pos;
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

        return (A.getX() * (B.getY() - P.getY()) + B.getX() * (P.getY() - A.getY()) + P.getX() * (A.getY() - B.getY()));

    }

    private LinkedList iterate(LinkedList points) {
       //System.out.println(points.getLength());
        LinkedListNode head = points.getHead();
        if (points.getLength() <3 ) { // || head.getNext() == null || head.getNext().getNext() == null) {
            return new LinkedList();
        }
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
        // P = null;

        // Find a point from the inserted list that has the maximum distance from the line AB
        P = A;
        //  System.out.println("A (" + A.getX() + "," + A.getY() + ")  B (" + B.getX() + "," + B.getY() + ")");
        while (current != null) {

            tempPoint = current.getPoint();
            //System.out.println("Iterating over (" + tempPoint.getX() + "," + tempPoint.getY() + ")");
            //double normalLength = Math.hypot(B.getX() - A.getX(), B.getY() - A.getY());
            dist = Math.abs((tempPoint.getX() - A.getX()) * (B.getY() - A.getY()) - (tempPoint.getY() - A.getY()) * (B.getX() - A.getX())); // normalLength;
            current = current.getNext();
            if (maxDist < dist) {
                maxDist = dist;
                P = tempPoint;
            }
        }

        //System.out.println("max dist: " + maxDist + " - P X coordinate: " + P.getX());
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
            if (checkRotation(P, B, current.getPoint()) > 0) {
                negative.insert(current.getPoint());
            }
            current = current.getNext();

        }

        // Recursively iterate..

        LinkedList res1 = iterate(positive);
        LinkedList res2 = iterate(negative);

        // Merge results..
        
        LinkedList result = new LinkedList();
        
        result.insert(A);
        current = res1.getHead();
        while (current != null) {
            result.insert(current.getPoint());
            current = current.getNext();
        }
        
        result.insert(B);
        current = res2.getHead();
        while (current != null) {
            result.insert(current.getPoint());
            current = current.getNext();
        }
        
        return result;
    }
}
