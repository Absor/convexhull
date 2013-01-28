package convexhull.algorithms;

import convexhull.datastructures.LinkedList;
import convexhull.datastructures.LinkedListNode;
import convexhull.main.ConvexHull;
import java.awt.geom.Point2D;

/**
 * Class for Akl-Toussaint heuristic <p> Quote from Wikipedia
 * (http://en.wikipedia.org/wiki/Convex_hull_algorithms) <p> "The following
 * simple heuristic is often used as the first step in implementations of convex
 * hull algorithms to improve their performance. It is based on the efficient
 * convex hull algorithm by Selim Akl and G. T. Toussaint, 1978. <p> The idea is
 * to quickly exclude many points that would not be part of the convex hull
 * anyway. This method is based on the following idea. Find the two points with
 * the lowest and highest x-coordinates, and the two points with the lowest and
 * highest y-coordinates. (Each of these operations takes O(n).) These four
 * points form a convex quadrilateral, and all points that lie in this
 * quadrilateral (except for the four initially chosen vertices) are not part of
 * the convex hull. Finding all of these points that lie in this quadrilateral
 * is also O(n), and thus, the entire operation is O(n). Optionally, the points
 * with smallest and largest sums of x- and y-coordinates as well as those with
 * smallest and largest differences of x- and y-coordinates can also be added to
 * the quadrilateral, thus forming an irregular convex octagon, whose insides
 * can be safely discarded. If the points are random variables, then for a wide
 * class of probability density functions, this throw-away pre-processing step
 * will make a convex hull algorithm run in linear expected time, even if the
 * worst-case complexity of the convex hull algorithm is quadratic in n."
 *
 * @author Heikki Haapala & Aleksi Markkanen
 */
public class AklToussaintHeuristic implements Algorithm {

    /**
     * The main method that runs the algorithm described in the class
     * description.
     *
     * @param points set of points to run the algorithm on
     * @return points remaining in set after running the heuristic
     */
    @Override
    public LinkedList useAlgorithm(LinkedList points) {
        
        ConvexHull.startTimer();
        
        LinkedList octagonPoints = this.setPoints(points);

        // need at least a triangle to remove points
        if (octagonPoints.getLength() < 3) {
            System.out.println("Akl-Toussaint didn't remove any points.");
            return points;
        }

        LinkedList outsideNodes = new LinkedList();
        int removed = 0;

        // go through all points to remove points that are inside the octagon
        LinkedListNode pNode = points.getHead();
        while (pNode != null) {
            LinkedListNode oNode = octagonPoints.getHead().getNext();
            boolean inside = true;
            while (oNode != null) {
                if (this.triangleArea(oNode.getPrev().getPoint(), oNode.getPoint(), pNode.getPoint()) > 0) {
                    inside = false;
                    break;
                }
                oNode = oNode.getNext();
            }
            if (inside) {
                removed++;
            } else {
                outsideNodes.insert(pNode.getPoint());
            }
            pNode = pNode.getNext();
        }

        System.out.println("Akl-Toussaint heuristic removed " + removed + " nodes.");

        // add octagon points to outsideNodes (they are part of the hull)
        LinkedListNode oNode = octagonPoints.getHead();
        while (oNode != null) {
            outsideNodes.insert(oNode.getPoint());
            oNode = oNode.getNext();
        }
        
        System.out.println("Akl-Toussaint heuristic ran in " + ConvexHull.stopTimer() + "ms.");

        return outsideNodes;
    }

    // Helper method for finding the points needed.
    private LinkedList setPoints(LinkedList points) {
        // array for points, indexes:
        // 0 = min(x), 1 = min(x-y), 2 = max(y), 3 = max(x+y)
        // 4 = max(x), 5 = max(x-y), 6 = min(y), 7 = min(x+y)
        Point2D.Double[] octagonPoints = new Point2D.Double[8];
        LinkedListNode node = points.getHead();
        while (node != null) {
            Point2D.Double point = node.getPoint();
            // minimum and maximum x value
            if (octagonPoints[0] == null || point.getX() < octagonPoints[0].getX()) {
                octagonPoints[0] = point;
            }
            if (octagonPoints[4] == null || point.getX() > octagonPoints[4].getX()) {
                octagonPoints[4] = point;
            }
            // minimum and maximum y value
            if (octagonPoints[6] == null || point.getY() < octagonPoints[6].getY()) {
                octagonPoints[6] = point;
            }
            if (octagonPoints[2] == null || point.getY() > octagonPoints[2].getY()) {
                octagonPoints[2] = point;
            }
            // minimum and maximum sum of coordinates
            if (octagonPoints[7] == null
                    || point.getX() + point.getY() < octagonPoints[7].getX() + octagonPoints[7].getY()) {
                octagonPoints[7] = point;
            }
            if (octagonPoints[3] == null
                    || point.getX() + point.getY() > octagonPoints[3].getX() + octagonPoints[3].getY()) {
                octagonPoints[3] = point;
            }
            // minimum and maximum differences of x- and y-coordinates
            if (octagonPoints[1] == null
                    || point.getX() - point.getY() < octagonPoints[1].getX() - octagonPoints[1].getY()) {
                octagonPoints[1] = point;
            }
            if (octagonPoints[5] == null
                    || point.getX() - point.getY() > octagonPoints[5].getX() - octagonPoints[5].getY()) {
                octagonPoints[5] = point;
            }
            node = node.getNext();
        }

        // add points to list in order and delete duplicates
        LinkedList newList = new LinkedList();
        newList.insert(octagonPoints[0]);
        LinkedListNode last = newList.getHead();
        for (int i = 1; i < 8; i++) {
            if (!octagonPoints[i].equals(last.getPoint())) {
                newList.insert(octagonPoints[i]);
                last = last.getNext();
            }
        }
        
        newList.insert(newList.getHead().getPoint());

        return newList;
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
