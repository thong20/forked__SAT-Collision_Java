package aght.collision;

import aght.math.Vector2d;
import aght.shape.Shape;
import aght.shape.decomposition.PolyDecompose;
import aght.shape.*;

/**
 * SAT.
 *
 * @author Andy Tang
 * @version 2018
 */
public class SAT {

    private static PolyDecompose decomposer = new PolyDecompose();

    public static boolean collide(Shape a, Shape b) {
        return convexConvex(a, b);
    }

    private static boolean convexConvex(Shape p1, Shape p2) {
        Vector2d[][] p1Decomp = decomposer.triangulate(p1.getVertices());
        Vector2d[][] p2Decomp = decomposer.triangulate(p2.getVertices());
        
        for (Vector2d[] p1Vertices : p1Decomp) {
            for (Vector2d[] p2Vertices : p2Decomp) {
                boolean collide = checkCollision(p1Vertices, p2Vertices);
                
                if (collide) {
                    return true;
                }
            }
        }
        
        return false;
    }

    private static boolean checkCollision(Vector2d[] aVertices,
            Vector2d[] bVertices) {

        int aLength = aVertices.length;
        int bLength = bVertices.length;

        // Try to find a separating axis using the first polygon's edges
        for (int i = 0, j = aLength - 1; i < aLength; j = i, i++) {
            Vector2d axis = Vector2d.sub(aVertices[i], aVertices[j]).perp(true);

            if (separatingAxis(aVertices, bVertices, axis)) {
                return false;
            }
        }

        // Try to find a separating axis using the second polygon's edges
        for (int i = 0, j = bLength - 1; i < bLength; j = i, i++) {
            Vector2d axis = Vector2d.sub(bVertices[i], bVertices[j]).perp(true);

            if (separatingAxis(aVertices, bVertices, axis)) {
                return false;
            }
        }

        return true;
    }

    private static boolean separatingAxis(Vector2d[] aVertices,
            Vector2d[] bVertices, Vector2d axis) {

        double minA = Double.POSITIVE_INFINITY;
        double maxA = Double.NEGATIVE_INFINITY;
        double minB = Double.POSITIVE_INFINITY;
        double maxB = Double.NEGATIVE_INFINITY;

        int maxLength = Math.max(aVertices.length, bVertices.length);

        // project both polygons onto axis
        for (int i = 0; i < maxLength; i++) {
            if (i < aVertices.length) {
                double dot = axis.dot(aVertices[i]);    

                if (dot < minA)
                    minA = dot;
                if (dot > maxA)
                    maxA = dot;
            }

            if (i < bVertices.length) {
                double dot = axis.dot(bVertices[i]);

                if (dot < minB)
                    minB = dot;
                if (dot > maxB)
                    maxB = dot;
            }

            // exit early if overlap found
            if (minA <= maxB && minB <= maxA) {
                return false;
            }
        }

        return true;
    }

}
