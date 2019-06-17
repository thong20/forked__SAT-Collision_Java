package aght.shape;

import java.util.ArrayList;

import aght.math.Vector2d;
import javafx.scene.shape.SVGPath;

/**
 * Shape.
 *
 * @author Andy Tang
 * @version 2018
 */
public class Shape extends SVGPath {

    private Vector2d[] vertices;

    public Shape(Vector2d[] vertices, String svgPath) {
        this.setContent(svgPath);
        this.vertices = vertices;
    }
    
    protected Shape(Vector2d[] vertices, String svgPath, Vector2d layout) {
        setLayoutPosition(layout.negate());
        this.setContent(svgPath);
        this.vertices = vertices;
    }

    public Vector2d[] getVertices() {
        ArrayList<Double> temp = new ArrayList<Double>();
        double[] xy = new double[vertices.length * 2];

        for (Vector2d vertex : vertices) {
            temp.add(vertex.x);
            temp.add(vertex.y);
        }
        
        xy = temp.stream().mapToDouble(Double::doubleValue).toArray();
        
        this.getLocalToParentTransform().transform2DPoints(xy, 0, xy, 0,
                xy.length / 2);

        double[] x = new double[vertices.length];
        double[] y = new double[vertices.length];

        int n = 0;
        for (int i = 0; i < xy.length; i += 2) {
            x[n] = xy[i];
            n++;
        }

        n = 0;
        for (int i = 1; i < xy.length; i += 2) {
            y[n] = xy[i];
            n++;
        }

        Vector2d[] transformed = new Vector2d[vertices.length];
        for (int i = 0; i < transformed.length; i++) {
            transformed[i] = new Vector2d(x[i], y[i]);
        }

        return transformed;
    }
    
    private void setLayoutPosition(Vector2d pos) {
        setLayoutX(pos.x);
        setLayoutY(pos.y);
    }
    
    public void setX(double x) {
        this.setTranslateX(x);
    }

    public void setY(double y) {
        this.setTranslateY(y);
    }

    public double getX() {
        return this.getTranslateX();
    }

    public double getY() {
        return this.getTranslateY();
    }
    
    public void setVertices(Vector2d[] n) {
        vertices = n;
    }
    
    public void rotate(double angle) {
        setRotate(angle);
    }
}
