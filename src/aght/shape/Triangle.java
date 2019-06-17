package aght.shape;

import aght.math.Vector2d;

/**
 * Triangle.
 *
 * @author Andy Tang
 * @version 2018
 */
public class Triangle extends Shape {
    public Triangle(Vector2d p1, Vector2d p2, Vector2d p3) {
        super(new Vector2d[] { p1, p2, p3 }, createSVGPath(p1, p2, p3));
    }

    private static String createSVGPath(Vector2d p1, Vector2d p2, Vector2d p3) {
        String svgPath = "M";

        svgPath += p1.x + "," + p1.y + ",";
        svgPath += p2.x + "," + p2.y + ",";
        svgPath += p3.x + "," + p3.y;

        return svgPath;
    }
}
