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
    private double xCorrection;
    private double yCorrection;
    private double pointAreaWidth;
    private double pointAreaHeight;
    private double maxX, minX, maxY, minY;

    public PointPrinter(LinkedList allPoints, LinkedList hullPoints) {
        setBackground(Color.WHITE);
        this.hullPoints = hullPoints;
        this.allPoints = allPoints;
        findMax();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        Dimension size = getSize();
        Insets insets = getInsets();

        int width = size.width - insets.left - insets.right - 20;
        int height = size.height - insets.top - insets.bottom - 20;

        double xScaler = width;
        if (pointAreaWidth != 0) {
            xScaler /= pointAreaWidth;
        }
        double yScaler = height;
        if (pointAreaHeight != 0) {
            yScaler /= pointAreaHeight;
        }

        g2d.setColor(Color.BLACK);

        Shape xAxle = new Line2D.Double(0, yScaler * yCorrection + 10, width + 20, yScaler * yCorrection + 10);
        g2d.draw(xAxle);
        Shape yAxle = new Line2D.Double(xScaler * xCorrection + 10, 0, xScaler * xCorrection + 5, height + 20);
        g2d.draw(yAxle);
        
        g2d.setColor(Color.BLUE);

        LinkedListNode node = this.hullPoints.getHead();
        while (node != null) {
            Point2D.Double point1 = node.getPoint();
            double x1 = (point1.getX() + xCorrection) * xScaler + 10;
            double y1 = (point1.getY() + yCorrection) * yScaler + 10;
            Shape circle = new Ellipse2D.Double(x1 - 3, y1 - 3, 7, 7);
            g2d.draw(circle);
            if (node.getPrev() != null) {
                Point2D.Double point2 = node.getPrev().getPoint();
                double x2 = (point2.getX() + xCorrection) * xScaler + 10;
                double y2 = (point2.getY() + yCorrection) * yScaler + 10;
                Shape line = new Line2D.Double(x1, y1, x2, y2);
                g2d.draw(line);
            }
            // line to close the hull
            if (node.getNext() == null) {
                Point2D.Double point2 = hullPoints.getHead().getPoint();
                double x2 = (point2.getX() + xCorrection) * xScaler + 10;
                double y2 = (point2.getY() + yCorrection) * yScaler + 10;
                Shape line = new Line2D.Double(x1, y1, x2, y2);
                g2d.draw(line);
            }
            node = node.getNext();
        }

        g2d.setColor(Color.RED);

        node = this.allPoints.getHead();
        while (node != null) {
            Point2D.Double point1 = node.getPoint();
            double x1 = (point1.getX() + xCorrection) * xScaler + 10;
            double y1 = (point1.getY() + yCorrection) * yScaler + 10;
            Shape circle = new Ellipse2D.Double(x1 - 1, y1 - 1, 3, 3);
            g2d.draw(circle);
            node = node.getNext();
        }

        g2d.setColor(Color.BLACK);
        g2d.drawString("X: [" + minX + "," + maxX + "]", 10, 20);
        g2d.drawString("Y: [" + minY + "," + maxY + "]", 10, 35);
    }

    private void findMax() {
        LinkedListNode node = this.allPoints.getHead();
        maxX = node.getPoint().getX();
        minX = node.getPoint().getX();
        maxY = node.getPoint().getY();
        minY = node.getPoint().getY();
        node = node.getNext();
        while (node != null) {
            Point2D.Double point = node.getPoint();
            if (point.getX() > maxX) {
                maxX = point.getX();
            } else if (point.getX() < minX) {
                minX = point.getX();
            }
            if (point.getY() > maxY) {
                maxY = point.getY();
            } else if (point.getY() < minY) {
                minY = point.getY();
            }
            node = node.getNext();
        }

        this.xCorrection = -minX;
        this.yCorrection = -minY;
        this.pointAreaWidth = maxX - minX;
        this.pointAreaHeight = maxY - minY;
    }
}