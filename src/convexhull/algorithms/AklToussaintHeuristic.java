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

        LinkedList octagonPoints = octagonPoints(points);
        System.out.println(octagonPoints.getLength());
        // need at least a triangle to remove points
        if (points.getLength() < 4 || octagonPoints.getLength() < 3) {
            System.out.println("Akl-Toussaint heuristic removed 0 nodes.");
            return points;
        }
        // go through all points to remove points that are inside the octagon
        LinkedList outsideNodes = removePointsInsideOctagon(points, octagonPoints);

        System.out.println("Akl-Toussaint heuristic removed " + (points.getLength() - outsideNodes.getLength()) + " nodes.");

        return outsideNodes;
    }

    // returns points of param points that are not inside the octagon defined by
    // param octagonPoints
    private LinkedList removePointsInsideOctagon(LinkedList points, LinkedList octagonPoints) {
        LinkedList outsideNodes = new LinkedList();
        LinkedListNode pNode = points.getHead();
        while (pNode != null) {
            LinkedListNode oNode = octagonPoints.getHead().getNext();
            boolean inside = true;
            while (oNode != null) {
                if (this.triangleArea(oNode.getPrev().getPoint(), oNode.getPoint(), pNode.getPoint()) >= 0) {
                    inside = false;
                    break;
                }
                oNode = oNode.getNext();
            }
            if (!inside) {
                outsideNodes.insert(pNode.getPoint());
            }
            pNode = pNode.getNext();
        }
        return outsideNodes;
    }

    // Helper method for finding the points needed.
    private LinkedList octagonPoints(LinkedList points) {
        // array for points, indexes:
        // 0 = min(x), 1 = min(x-y), 2 = max(y), 3 = max(x+y)
        // 4 = max(x), 5 = max(x-y), 6 = min(y), 7 = min(x+y)
        Point2D.Double[] octagonPoints = new Point2D.Double[8];
        LinkedListNode node = points.getHead();
        // array indexes to first point
        presetArray(octagonPoints, node.getPoint());
        node = node.getNext();
        while (node != null) {
            Point2D.Double point = node.getPoint();
            // minimum and maximum x value
            octagonPoints[0] = minXPoint(point, octagonPoints[0]);
            octagonPoints[4] = maxXPoint(point, octagonPoints[4]);
            // minimum and maximum y value
            octagonPoints[6] = minYPoint(point, octagonPoints[6]);
            octagonPoints[2] = maxYPoint(point, octagonPoints[2]);;
            // minimum and maximum sum of coordinates
            octagonPoints[7] = minCoordSum(point, octagonPoints[7]);
            octagonPoints[3] = maxCoordSum(point, octagonPoints[3]);
            // minimum and maximum differences of x- and y-coordinates
            octagonPoints[1] = minCoordDiff(point, octagonPoints[1]);
            octagonPoints[5] = maxCoordDiff(point, octagonPoints[5]);
            node = node.getNext();
        }

        // add points to list in order and delete duplicates
        return linkedListFromArray(octagonPoints);
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

    // turns array of points to linked list of points without doubles except the
    // first and the last point
    private LinkedList linkedListFromArray(Point2D.Double[] points) {
        LinkedList newList = new LinkedList();
        newList.insert(points[0]);
        LinkedListNode current = newList.getHead();
        for (int i = 1; i < 8; i++) {
            if (!points[i].equals(current.getPoint())) {
                newList.insert(points[i]);
                current = current.getNext();
            }
        }
        newList.insert(newList.getHead().getPoint());
        return newList;
    }

    // sets all array indexes to given point
    private Point2D.Double[] presetArray(Point2D.Double[] array, Point2D.Double point) {
        for (int i = 0; i < array.length; i++) {
            array[i] = point;
        }
        return array;
    }

    // return the point with lesser x coordinate
    private Point2D.Double minXPoint(Point2D.Double point1, Point2D.Double point2) {
        if (point1.getX() < point2.getX()) {
            return point1;
        }
        return point2;
    }

    // return the point with greater x coordinate
    private Point2D.Double maxXPoint(Point2D.Double point1, Point2D.Double point2) {
        if (point1.getX() > point2.getX()) {
            return point1;
        }
        return point2;
    }

    // return the point with lesser y coordinate
    private Point2D.Double minYPoint(Point2D.Double point1, Point2D.Double point2) {
        if (point1.getY() < point2.getY()) {
            return point1;
        }
        return point2;
    }

    // return the point with greater y coordinate
    private Point2D.Double maxYPoint(Point2D.Double point1, Point2D.Double point2) {
        if (point1.getY() > point2.getY()) {
            return point1;
        }
        return point2;
    }

    // return the point with lesser sum of coordinates
    private Point2D.Double minCoordSum(Point2D.Double point1, Point2D.Double point2) {
        if (point1.getX() + point1.getY() < point2.getX() + point2.getY()) {
            return point1;
        } else {
            return point2;
        }
    }

    // return the point with greater sum of coordinates
    private Point2D.Double maxCoordSum(Point2D.Double point1, Point2D.Double point2) {
        if (point1.getX() + point1.getY() > point2.getX() + point2.getY()) {
            return point1;
        } else {
            return point2;
        }
    }

    // return the point with lesser difference of coordinates
    private Point2D.Double minCoordDiff(Point2D.Double point1, Point2D.Double point2) {
        if (point1.getX() - point1.getY() < point2.getX() - point2.getY()) {
            return point1;
        } else {
            return point2;
        }
    }

    // return the point with greater difference of coordinates
    private Point2D.Double maxCoordDiff(Point2D.Double point1, Point2D.Double point2) {
        if (point1.getX() - point1.getY() > point2.getX() - point2.getY()) {
            return point1;
        } else {
            return point2;
        }
    }
}
