package convexhull.algorithms;

import convexhull.datastructures.LinkedList;
import convexhull.datastructures.LinkedListNode;
import java.awt.geom.Point2D;

public class AklToussaintHeuristic implements Algorithm {

    @Override
    public LinkedList useAlgorithm(LinkedList points) {
        Point2D.Double minX = null, maxX = null,
                minY = null, maxY = null,
                minCoordSum = null, maxCoordSum = null,
                minCoordDiff = null, maxCoordDiff = null;

        LinkedListNode node = points.getHead();
        while(node != null) {
            Point2D.Double point = node.getPoint();
            // minimum and maximum x value
            if (minX == null || point.getX() < minX.getX()) {
                minX = point;
            }
            if (maxX == null || point.getX() > maxX.getX()) {
                maxX = point;
            }
            // minimum and maximum y value
            if (minY == null || point.getY() < minY.getY()) {
                minY = point;
            }
            if (maxY == null || point.getY() > maxY.getY()) {
                maxY = point;
            }
            // minimum and maximum sum of coordinates
            if (minCoordSum == null
                    || point.getX() + point.getY() < minCoordSum.getX() + minCoordSum.getY()) {
                minCoordSum = point;
            }
            if (maxCoordSum == null
                    || point.getX() + point.getY() > maxCoordSum.getX() + maxCoordSum.getY()) {
                maxCoordSum = point;
            }
            // minimum and maximum differences of x- and y-coordinates
            // TODO: a-b or |a-b| or ||a|-|b|| or something else?
            if (minCoordDiff == null
                    || point.getX() - point.getY() < minCoordDiff.getX() - minCoordDiff.getY()) {
                minCoordDiff = point;
            }
            if (maxCoordDiff == null
                    || point.getX() - point.getY() > maxCoordDiff.getX() - maxCoordDiff.getY()) {
                maxCoordDiff = point;
            }
            node = node.getNext();
        }
        
        System.out.println(minX + "\n" + maxX + "\n" + minY + "\n" + maxY + "\n" + minCoordSum + "\n" + maxCoordSum + "\n" + minCoordDiff + "\n" + maxCoordDiff);

        return null;
    }
}
