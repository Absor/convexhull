package convexhull.graphics;

import convexhull.datastructures.LinkedList;
import convexhull.datastructures.LinkedListNode;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.JPanel;

/**
 *
 * @author Heikki Haapala
 */
public class PointPrinter extends JPanel {

    private LinkedList hullPoints;
    private LinkedList allPoints;
    private double max;

    public PointPrinter(LinkedList allPoints, LinkedList hullPoints) {
        this.hullPoints = hullPoints;
        this.allPoints = allPoints;
        findMax();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLUE);

        Dimension size = getSize();
        Insets insets = getInsets();

        int width = size.width - insets.left - insets.right;
        int height = size.height - insets.top - insets.bottom;

        double scaler = (Math.min(width, height) / 2) / this.max * 0.95;

        LinkedListNode node = this.hullPoints.getHead();
        while (node != null) {
            Point2D.Double point1 = node.getPoint();
            double x1 = point1.getX() * scaler + (width / 2);
            double y1 = point1.getY() * scaler + (height / 2);
            Shape circle = new Ellipse2D.Double(x1-3, y1-3, 7, 7);
            g2d.draw(circle);
            if (node.getPrev() != null) {
                Point2D.Double point2 = node.getPrev().getPoint();
                double x2 = point2.getX() * scaler + (width / 2);
                double y2 = point2.getY() * scaler + (height / 2);
                Shape line = new Line2D.Double(x1, y1, x2, y2);
                g2d.draw(line);
            }
            node = node.getNext();
        }
        
        g2d.setColor(Color.RED);
        
        node = this.allPoints.getHead();
        while (node != null) {
            Point2D.Double point1 = node.getPoint();
            double x1 = point1.getX() * scaler + (width / 2);
            double y1 = point1.getY() * scaler + (height / 2);
            Shape circle = new Ellipse2D.Double(x1-1, y1-1, 3, 3);
            g2d.draw(circle);
            node = node.getNext();
        }
    }

    private void findMax() {
        LinkedListNode node = this.allPoints.getHead();
        this.max = node.getPoint().getX();
        while (node != null) {
            Point2D.Double point = node.getPoint();
            double absX = Math.abs(point.getX());
            if (absX > this.max) {
                this.max = absX;
            }
            double absY = Math.abs(point.getY());
            if (absY > this.max) {
                this.max = absY;
            }
            node = node.getNext();
        }
    }
}