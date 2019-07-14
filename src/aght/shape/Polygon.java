package aght.shape;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import aght.math.Vector2d;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;

/**
 * Polygon.
 *
 * @author Andy Tang
 * @version 2018
 */
public class Polygon extends Shape {

    private boolean isConvex;

    public Polygon(double x, double y, double radius, double sides) {
        super(generateEvenPoly(radius, sides), createSVGPath(generateEvenPoly(radius, sides)));
        setTranslateX(x);
        setTranslateY(y);
        isConvex = true;
    }

    private Polygon(Vector2d[] vertices, Vector2d layout) {
        super(vertices, createSVGPath(vertices), layout);

        isConvex = checkIfConvex();
    }

    private static Vector2d[] generateEvenPoly(double radius, double sides) {
        ArrayList<Vector2d> vertices = new ArrayList<Vector2d>();

        for (int i = 0; i < sides; i++) {
            double angle = Vector2d.map(i, 0, sides, 0, Math.PI * 2);
            double x = (radius * Math.cos(angle));
            double y = (radius * Math.sin(angle));
            vertices.add(new Vector2d(x, y));
        }

        return vertices.toArray(new Vector2d[vertices.size()]);
    }

    public static Polygon fromVertices(Vector2d[] v) {
        Vector2d centroid = centerOfMass(v);

        return new Polygon(v, centroid);
    }

    public static Vector2d centerOfMass(Vector2d[] vertices) {
        double cx = 0;
        double cy = 0;

        double area = area(vertices);
        double factor = 0;

        for (int i = 0; i < vertices.length; i++) {

            int j = (i + 1) % vertices.length;

            factor = (vertices[i].getX() * vertices[j].getY() - vertices[j].getX() * vertices[i].getY());

            cx += (vertices[i].getX() + vertices[j].getX()) * factor;
            cy += (vertices[i].getY() + vertices[j].getY()) * factor;
        }

        factor = 1 / (area * 6);

        return new Vector2d(cx * factor, cy * factor);
    }

    private static double area(Vector2d[] vertices) {
        double area = 0;

        for (int i = 0; i < vertices.length; i++) {
            int j = (i + 1) % vertices.length;
            area += vertices[i].getX() * vertices[j].getY();
            area -= vertices[j].getX() * vertices[i].getY();
        }

        return area / 2.0;
    }

    private static String createSVGPath(Vector2d[] vertices) {

        StringBuilder svgPath = new StringBuilder("M");
        for (Vector2d vertex : vertices) {
            svgPath.append(vertex.getX() + "," + vertex.getY() + ",");
        }

        return svgPath.toString();
    }

    private boolean checkIfConvex() {
        Vector2d[] vertices = this.getVertices();

        // If user attempts to close the shape, ignore the closing vertex to
        // avoid calculation errors
        int verticesLength = vertices.length;
        if (vertices[0].equals(vertices[vertices.length - 1])) {
            verticesLength--;
        }

        Vector2d prev = vertices[verticesLength - 2];
        Vector2d curr = vertices[verticesLength - 1];
        Vector2d next = vertices[0];

        boolean isCCW = Vector2d.cross3(prev, curr, next) > 0;

        for (int i = 1; i < verticesLength; i++) {
            prev = curr;
            curr = next;
            next = vertices[i];
            if ((Vector2d.cross3(prev, curr, next) > 0) != isCCW) {
                return false;
            }
        }

        return true;
    }

    public static Polygon createRandomPolygon(double x, double y, double radius,
                                              double n) {

        Random r = new Random();
        ArrayList<Vector2d> vertices = new ArrayList<Vector2d>();

        for (int i = 0; i < n; i++) {
            double angle = Vector2d.map(i, 0, n, 0, Math.PI * 2);
            double X = (radius + r.nextInt(20) + 10) * Math.cos(angle);
            double Y = (radius + r.nextInt(20) + 10) * Math.sin(angle);
            vertices.add(new Vector2d(x + X, y + Y));
        }

        return Polygon.fromVertices(vertices.toArray(new Vector2d[vertices.size()]));
    }

    public boolean isConvex() {
        return isConvex;
    }
}
