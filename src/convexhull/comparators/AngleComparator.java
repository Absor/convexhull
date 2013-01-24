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
     *
     * @param refPoint
     */
    public AngleComparator(Point2D.Double refPoint) {
        this.refPoint = refPoint;
    }

    /**
     *
     * @param point1
     * @param point2
     * @return
     */
    @Override
    public int compare(Point2D.Double point1, Point2D.Double point2) {
        // TODO
        return (int) (point1.getX()-point2.getX());
    }
}
