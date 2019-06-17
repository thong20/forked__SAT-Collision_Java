package aght.collision;

import aght.math.Vector2d;

/**
 * AABB.
 *
 * @author Andy Tang
 * @version 2018
 */
public class AABB {
    
    double minX;
    double minY;
    double maxX;
    double maxY;

    public AABB(Vector2d[] vertices) {
        
        minX = Double.POSITIVE_INFINITY;
        minY = Double.POSITIVE_INFINITY;
        maxX = Double.NEGATIVE_INFINITY;
        maxY = Double.NEGATIVE_INFINITY;
        
        for (int i = 0; i < vertices.length; i++) {
            Vector2d vertex = vertices[i];
            
            if (vertex.x > maxX) maxX = vertex.x;
            if (vertex.x < minX) minX = vertex.x;
            if (vertex.y > maxY) maxY = vertex.y;
            if (vertex.y < minY) minY = vertex.y;
        }
    }

    public boolean intersects(AABB b) {
        return (this.minX <= b.maxX && this.maxX >= b.minX &&
                this.maxY >= b.minY && this.minY <= b.maxY);
    }
}
