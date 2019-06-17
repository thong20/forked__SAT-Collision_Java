package aght.shape.decomposition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aght.math.Vector2d;

/**
 * PolyDecompose.
 *
 * @author Andy Tang
 * @version 2018
 */
public class PolyDecompose {

    public Vector2d[][] triangulate(Vector2d[] v) {
        List<Vector2d> vertices = new ArrayList<Vector2d>(Arrays.asList(v));

        List<ArrayList<Vector2d>> triangles = new ArrayList<ArrayList<Vector2d>>();

        while (true) {

            boolean isCreated = false;

            for (int i = 0; i < vertices.size(); i++) {
                int prevIndex = i - 1 == -1 ? vertices.size() - 1 : i - 1;
                int currIndex = i;
                int nextIndex = i + 1 == vertices.size() ? 0 : i + 1;

                Vector2d prev = vertices.get(prevIndex);
                Vector2d curr = vertices.get(currIndex);
                Vector2d next = vertices.get(nextIndex);

                if (Vector2d.cross(Vector2d.sub(prev, curr),
                        Vector2d.sub(next, curr)) >= 0) {
                    continue;
                }

                int[] skips = { prevIndex, currIndex, nextIndex };

                if (pointInTriangle(new Vector2d[] { prev, curr, next },
                        vertices.toArray(new Vector2d[vertices.size()]),
                        skips)) {
                    continue;
                }

                ArrayList<Vector2d> triangleVerts = new ArrayList<Vector2d>();
                triangleVerts.add(prev.clone());
                triangleVerts.add(curr.clone());
                triangleVerts.add(next.clone());

                triangles.add(triangleVerts);

                vertices.remove(currIndex);

                isCreated = true;
            }

            if (!isCreated) {
                break;
            }
        }

        return convertToBasicArray(triangles);
    }
    
    private boolean pointInTriangle(Vector2d[] triPoints, Vector2d[] polyPoints,
            int[] skips) {

        for (int i = 0; i < polyPoints.length; i++) {

            if (findMatch(skips, i) != -1) {
                continue;
            }

            return triPoint(triPoints[0], triPoints[1], triPoints[2],
                    polyPoints[i]);

        }

        return false;
    }

    private Vector2d[][] convertToBasicArray(List<ArrayList<Vector2d>> list) {
        Vector2d[][] array = new Vector2d[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            ArrayList<Vector2d> row = list.get(i);
            array[i] = row.toArray(new Vector2d[row.size()]);
        }

        return array;
    }

    /*
     * Triangle, point collision using Barycentric Coordinates for faster
     * computation
     */
    private static boolean triPoint(Vector2d a, Vector2d b, Vector2d c,
            Vector2d p) {

        Vector2d v0 = Vector2d.sub(c, a);
        Vector2d v1 = Vector2d.sub(b, a);
        Vector2d v2 = Vector2d.sub(p, a);

        double dot00 = Vector2d.dot(v0, v0);
        double dot01 = Vector2d.dot(v0, v1);
        double dot02 = Vector2d.dot(v0, v2);
        double dot11 = Vector2d.dot(v1, v1);
        double dot12 = Vector2d.dot(v1, v2);

        double invDenom = 1 / (dot00 * dot11 - dot01 * dot01);
        double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
        double v = (dot00 * dot12 - dot01 * dot02) * invDenom;

        return (u >= 0) && (v >= 0) && (u + v < 1);
    }

    private int findMatch(int[] arr, int index) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == index) {
                return i;
            }
        }

        return -1;
    }
}
