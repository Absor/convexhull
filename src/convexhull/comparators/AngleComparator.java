package convexhull.comparators;

import java.awt.geom.Point2D;
import java.util.Comparator;

/**
 * Provides comparator for sorting points by their polar angle, in increasing
 * order.
 *
 * @author Heikki Haapala
 */
public class AngleComparator implements Comparator<Point2D.Double> {

    private Point2D.Double pole;

    /**
     * Constructor for the class.
     *
     * @param pole the point that will be used as the pole of the polar
     * coordinates
     */
    public AngleComparator(Point2D.Double pole) {
        this.pole = pole;
    }

    /**
     * Comparing method for two points.
     *
     * @param point1 point 1 to compare
     * @param point2 point 2 to compare
     * @return -1 if polar angle of point1 is smaller than the polar angle of
     * point2 1 if it's the other way around and 0 if the angles are equal
     */
    @Override
    public int compare(Point2D.Double point1, Point2D.Double point2) {
        // 
        double difference = polarAngle(point1) - polarAngle(point2);
        // rising order of angles     
        if (difference < 0) {
            // point 1 angle smaller
            return -1;
        } else if (difference == 0) {
            return 0;
        }
        // point 2 angle smaller
        return 1;
    }

    // returns the polar angle of the point with the predefined point as the
    // pole
    private double polarAngle(Point2D.Double point) {
        Point2D.Double scaledPoint = new Point2D.Double(
                point.getX() - this.pole.getX(),
                point.getY() - this.pole.getY());
        return Math.atan2(scaledPoint.getY(), scaledPoint.getX());
    }
}
