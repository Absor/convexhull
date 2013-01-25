package convexhull.comparators;

import java.awt.geom.Point2D;
import java.util.Comparator;

/**
 *
 * @author Heikki Haapala
 */
public class AngleComparator implements Comparator<Point2D.Double> {

    private Point2D.Double refPoint;

    /**
     * Constructor for the class.
     *
     * @param refPoint the reference point from which the vectors are formed
     */
    public AngleComparator(Point2D.Double refPoint) {
        this.refPoint = refPoint;
    }

    /**
     * Points are sorted in increasing order of the angle they and the
     * point refPoint make with the x-axis.
     *
     * @param point1 point 1 to compare
     * @param point2 point 2 to compare
     * @return -1 if angle between refPoint->point1 and x-axis is smaller than
     *             the angle between refPoint->point2 and x-axis
     *          1 if it's the other way around and
     *          0 if the angles are equal
     */
    @Override
    public int compare(Point2D.Double point1, Point2D.Double point2) {
        // these points represent vectors refPoint->point
        Point2D.Double vector1 = new Point2D.Double(
                point1.getX() - this.refPoint.getX(),
                point1.getY() - this.refPoint.getY());
        Point2D.Double vector2 = new Point2D.Double(
                point2.getX() - this.refPoint.getX(),
                point2.getY() - this.refPoint.getY());
        
        // rising order of angles
        double difference = angleWithXAxis(vector1) - angleWithXAxis(vector2);
                
        if (difference < 0) {
            // point 1 smaller
            return -1;
        } else if (difference == 0) {
            return 0;
        }
        // point 2 smaller
        return 1;
    }

    // returns angle between vector and x-axis in radians
    private double angleWithXAxis(Point2D.Double vector) {
        return Math.atan2(vector.getY(), vector.getX());
    }
}
