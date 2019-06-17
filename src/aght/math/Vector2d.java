package aght.math;

import java.util.Random;

/**
 * A class to represent a two dimensional Euclidean (geometric) vector. The
 * vectors contain a x and y component, other information is calculated based on
 * these components, such as magnitude. In addition to the class methods, this
 * class also contains static methods to perform quick vector calculations
 * without instantiating a new object. Many of the methods that return itself or
 * a new Vector2d object can be chained with other methods that return this.
 * 
 * @author Andy Tang
 * @version 1.0
 */
public class Vector2d {

    public static final Random rand = new Random();

    /**
     * x component of the vector.
     */
    public double x;

    /**
     * y component of the vector.
     */
    public double y;

    /**
     * Constructs a Vector2d object with a x and y component from double values.
     * 
     * @param x
     *            the x component
     * @param y
     *            the y component
     */
    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a Vector2d object from an array, where the elements at index 0
     * and 1 are the x and y components respectively, any other element in the
     * array is ignored.
     * 
     * @param vec
     *            the array to create a Vector2d object from
     * @return new Vector2d object created from the argument array
     */
    public static Vector2d fromArray(double[] vec) {
        return new Vector2d(vec[0], vec[1]);
    }

    /**
     * Adds another vector to this vector.
     * 
     * @param vec
     *            the Vector2d object to add to this vector.
     * 
     * @return this Vector2d object.
     */
    public Vector2d add(Vector2d vec) {
        this.x += vec.x;
        this.y += vec.y;
        return this;
    }

    /**
     * This is a static method that adds two vectors.
     * 
     * @param vec1
     *            the first Vector2d object.
     * @param vec2
     *            the second Vector2d object.
     * @return new Vector2d object containing the sum of the two Vector2d
     *         objects.
     */
    public static Vector2d add(Vector2d vec1, Vector2d vec2) {
        return new Vector2d(vec1.x + vec2.x, vec1.y + vec2.y);
    }

    /**
     * Subtracts this vector from another vector.
     * 
     * @param vec
     *            the Vector2d object.
     * @return this Vector2d object.
     */
    public Vector2d sub(Vector2d vec) {
        this.x -= vec.x;
        this.y -= vec.y;
        return this;
    }

    /**
     * This is a static method that adds two vectors.
     * 
     * @param vec1
     *            the first Vector2d object.
     * @param vec2
     *            the second Vector2d object.
     * @return new Vector2d object containing the difference of the two Vector2d
     *         objects.
     */
    public static Vector2d sub(Vector2d vec1, Vector2d vec2) {
        return new Vector2d(vec1.x - vec2.x, vec1.y - vec2.y);
    }

    /**
     * Multiply this vector by a scalar.
     * 
     * @param n
     *            the scalar value
     * @return this Vector2d object.
     */
    public Vector2d mult(double n) {
        this.x *= n;
        this.y *= n;
        return this;
    }

    /**
     * This is a static method that multiplies a vector by a scalar.
     * 
     * @param vec
     *            the vector to multiply
     * @param n
     *            the scalar value
     * @return new Vector2d object containing the product
     */
    public static Vector2d mult(Vector2d vec, double n) {
        double nX = vec.x * n;
        double nY = vec.y * n;
        return new Vector2d(nX, nY);
    }

    /**
     * Divide this vector by a scalar.
     * 
     * @param n
     *            the scalar value
     * @return this Vector2d object.
     */
    public Vector2d div(double n) {
        this.x /= n;
        this.y /= n;
        return this;
    }

    /**
     * This is a static method that divides a vector by a scalar.
     * 
     * @param vec
     *            the Vector2d object
     * @param n
     *            the scalar value
     * @return new Vector2d object containing the quotient
     */
    public static Vector2d div(Vector2d vec, double n) {
        double nX = vec.x / n;
        double nY = vec.y / n;
        return new Vector2d(nX, nY);
    }

    /**
     * Gets the perpendicular vector to this vector.
     * 
     * @return the perpendicular vector
     */
    public Vector2d perp(boolean negate) {
        int n = negate == true ? -1 : 1;
        return new Vector2d(n * -y, n * x);
    }

    /**
     * Calculates the magnitude (length) of the vector.
     * 
     * @return the magnitude of this Vector2d object.
     */
    public double mag() {
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }

    /**
     * Calculates the squared magnitude (length) of the vector. Calculated using
     * the formula: x * x + y * y
     * 
     * @return the squared magnitude of this Vector2d object.
     */
    public double magSq() {
        return (this.x * this.x) + (this.y * this.y);
    }

    /**
     * Calculates the dot product this vector and another vector object.
     * 
     * @param vec
     *            the second Vector2d object
     * @return the dot product
     */
    public double dot(Vector2d vec) {
        return this.x * vec.x + this.y * vec.y;
    }

    /**
     * Calculates the dot product of two vectors.
     * 
     * @param vec1
     *            the first Vector2d object
     * @param vec2
     *            the second Vector2d object
     * @return the dot product of the two vectors.
     */
    public static double dot(Vector2d vec1, Vector2d vec2) {
        return vec1.x * vec2.x + vec1.y * vec2.y;
    }

    /**
     * Calculate the cross product of this vector and another vector.
     * 
     * @param vec
     *            the vector
     * @return cross product
     */
    public double cross(Vector2d vec) {
        return (this.x * vec.y) - (this.y * vec.x);
    }

    /**
     * Calculates the cross product of two vectors.
     * 
     * @param a
     *            vector 1
     * @param b
     *            vector 2
     * @return cross product
     */
    public static double cross(Vector2d a, Vector2d b) {
        return (a.x * b.y) - (a.y * b.x);
    }

    /**
     * Calculate the cross product of three vectors.
     * 
     * @param a
     *            vector 1
     * @param b
     *            vector 2
     * @param c
     *            vector 3
     * @return cross product
     */
    public static double cross3(Vector2d a, Vector2d b, Vector2d c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

    /**
     * Calculates the angle of rotation for this vector.
     * 
     * @return the angle of rotation
     */
    public double heading() {
        return Math.atan2(this.y, this.x);
    }
    
    public Vector2d negate() {
        this.x *= -1;
        this.y *= -1;
        return this;
    }
    
    public static Vector2d negate(Vector2d vec) {
        vec.x *= -1;
        vec.y *= -1;
        return vec;
    }

    /**
     * Calculates and returns the angle (in radians) between two vectors.
     * 
     * @param vec
     *            the second Vector2d object.
     * @return the angle between the two vectors (in radians)
     */
    public double angleBetween(Vector2d vec) {
        return Math.acos(this.dot(vec) / (this.mag() * vec.mag()));
    }

    /**
     * Calculates the distance between this vector and another vector.
     * 
     * @param vec
     *            the vector to compare this vector to.
     * @return the distance between the two vectors
     */
    public double dist(Vector2d vec) {
        return Math.hypot(this.x - vec.x, this.y - vec.y);
    }

    /**
     * Calculates the distance between two vectors.
     * 
     * @param vec1
     *            the first vector
     * @param vec2
     *            the second vector
     * @return the distance between the two vectors.
     */
    public static double dist(Vector2d vec1, Vector2d vec2) {
        return Math.hypot(vec1.x - vec2.x, vec1.y - vec2.y);
    }

    /**
     * Normalize this vector.
     * 
     * @return this normalized Vector2d object
     */
    public Vector2d normalize() {
        double magnitude = mag();
        if (magnitude > 0) {
            return this.div(mag());
        }
        return this;
    }

    /**
     * Limit the magnitude of the vector to a set value.
     * 
     * @param max
     *            the maximum magnitude of the vector.
     * @return this Vector2d object
     */
    public Vector2d limit(double max) {
        double magnitudeSquared = Math.pow(mag(), 2);

        if (magnitudeSquared > Math.pow(max, 2)) {
            this.div(Math.sqrt(magnitudeSquared)).mult(max);
        }
        return this;
    }

    /**
     * Sets the magnitude of this vector to a value.
     * 
     * @param n
     *            the new magnitude of the vector.
     * @return this Vector2d object.
     */
    public Vector2d setMag(double n) {
        return this.normalize().mult(n);
    }

    /**
     * Creates a 2D unit vector (vector with a magnitude of 1) from an angle.
     * 
     * @param angle
     *            the desired angle.
     * @return new Vector2d object created from an angle.
     */
    public Vector2d fromAngle(double angle) {
        return new Vector2d(Math.cos(angle), Math.sin(angle));
    }

    /**
     * Make a unit vector (vector with a magnitude of 1) from a random angle.
     * 
     * @return the new random Vector2d object.
     */
    public static Vector2d random2D() {
        double angle = rand.nextDouble() * Math.PI * 2;
        Vector2d ret = new Vector2d(0, 0);

        ret = ret.fromAngle(angle);
        return ret;
    }

    /**
     * Translates this vector by another vector. Performing this action is the
     * same as using the add method from this class.
     * <p>
     * 
     * <pre>
     * <code>
     * Vector2d vec = new Vector2d(10, 10);    // {10, 10}
     * vec.translate(new Vector2d(1, 1));      // {11, 11}
     * </code>
     * </pre>
     * 
     * @param vec
     *            the vector to translate by
     * @return this Vector2d object
     */
    public Vector2d translate(Vector2d vec) {
        this.add(vec);
        return this;
    }

    /**
     * WARNING: MAY NOT WORK DUE TO MODIFYING, BUT BEING TOO LAZY TO TEST!
     * 
     * Scales this vector by another vector with an anchor point as the origin.
     * This operation moves translates the coordinates so that the anchor point
     * is the origin, then performs the scaling operation, lastly translating so
     * that the origin is restored to that of the original anchor point. An
     * anchor point of null will result in an anchor point at (0, 0). The
     * scaling is done by utilizing a 2D translation matrix
     * <p>
     * The equation representing the transform is (where x' and y' are the
     * scaled coordinates):
     * 
     * <pre>
     * [  x' ]   [  vX   0  ] [  x  ]
     * [  y' ] = [  0   vY  ] [  y  ]
     * </pre>
     * 
     * @param anchor
     *            the anchor point
     * @param vec
     *            the vector to translate by
     * @return this Vector2d object.
     */
    public Vector2d scale(Vector2d anchor, Vector2d vec) {
        if (anchor == null) {
            anchor = new Vector2d(0, 0);
        }

        this.translate(anchor.mult(-1));

        // @formatter:off
        double[][] scaleMatrix = { { vec.getX(), 0 }, 
                                   { 0, vec.getY() }};

        double[][] vectorMatrix = { { this.x }, 
                                    { this.y }};
        // @formatter:on

        Matrix m1 = new Matrix(scaleMatrix);
        Matrix m2 = new Matrix(vectorMatrix);
        Matrix r0 = m1.mult(m2);

        this.x = r0.get(0, 0);
        this.y = r0.get(1, 0);

        this.translate(anchor.mult(-1));

        return this;
    }

    /**
     * Rotates this vector around an anchor point by a specified angle. This
     * operation translates the coordinates so that the anchor point is at the
     * origin, then rotating them about the new origin, lastly translating so
     * that the origin is restored to that of the original anchor point. An
     * anchor point of null will result in the anchor point being at (0, 0). The
     * rotation is done by utilizing a 2D rotation matrix.
     * <p>
     * The equation representing the transform is (where x' and y' are the
     * rotated coordinates):
     * 
     * <pre>
     * [  x' ]   [  cos(theta)   -sin(theta)  ] [  x  ]
     * [  y' ] = [  sin(theta)    cos(theta)  ] [  y  ]
     * </pre>
     * 
     * @param anchor
     *            the anchor point
     * @param a
     *            the angle to rotate by (radians)
     * @return this vector rotated around the anchor point by the specified
     *         angle
     */
    public Vector2d rotate(Vector2d anchor, double a) {
        if (anchor == null) {
            anchor = new Vector2d(0, 0);
        }

        if (this.x == anchor.x && this.y == anchor.y) {
            return this;
        }

        this.translate(anchor.mult(-1));

        // @formatter:off
        double[][] rotationMatrix = { { Math.cos(a), -Math.sin(a)},
                                      { Math.sin(a), Math.cos(a) } };

        double[][] vectorMatrix = { { this.x },
                                    { this.y }};
        // @formatter:on
        Matrix m1 = new Matrix(rotationMatrix);
        Matrix m2 = new Matrix(vectorMatrix);
        Matrix r0 = m1.mult(m2);

        this.x = r0.get(0, 0);
        this.y = r0.get(1, 0);

        this.translate(anchor.mult(-1));

        return this;
    }

    /**
     * Rotates this vector around an anchor point by a specified angle. This
     * operation translates the coordinates so that the anchor point is at the
     * origin, then rotating them about the new origin, lastly translating so
     * that the origin is restored to that of the original anchor point. An
     * anchor point of null will result in the anchor point being at (0, 0). The
     * rotation is done by utilizing a 3D Z-axis rotation matrix with the Z
     * coordinate as 0.
     * <p>
     * The equation representing the transform is (where x' and y' are the new
     * coordinates):
     * 
     * <pre>
     * [  x' ]   [  cos(theta)   -sin(theta)   0  ] [  x  ]
     * [  y' ] = [  sin(theta)    cos(theta)   0  ] [  y  ]
     * [  0  ]   [      0             0        1  ] [  0  ]
     * </pre>
     * 
     * @param anchor
     *            the anchor point
     * @param a
     *            the angle to rotate by (degrees)
     * @return this vector rotated around the anchor point by the specified
     *         angle
     */
    public Vector2d rotateDeg(Vector2d anchor, double a) {
        return rotate(anchor, Math.toRadians(a));
    }

    /**
     * Creates an array representation of this vector.
     * 
     * @return array representing this vector
     */
    public double[] toArray() {
        return toArray(this);
    }

    /**
     * Creates an array representation of this vector.
     * 
     * @param vec
     *            the Vector2d object to convert
     * @return array representing this vector
     */
    public static double[] toArray(Vector2d vec) {
        double[] arr = { vec.x, vec.y };
        return arr;
    }

    /**
     * Creates a matrix array representation of this vector in the form of a two
     * dimensional array.
     * 
     * <pre>
     * <code>
     * Vector2d v = new Vector2d(10, 5);   // {10, 5}
     * v.toMatrixArray();                  // {{10}, {5}}
     * </code>
     * </pre>
     * 
     * @return matrix array representing this vector
     */
    public double[][] toMatrixArray() {
        return toMatrixArray(this);
    }

    /**
     * Create a matrix array representing a vector in the form of a two
     * dimensional array.
     * 
     * <pre>
     * <code>
     * Vector2d v = new Vector2d(10, 5);          // {10, 5} 
     * double[][] = Vector2d.toMatrixArray(v);    // {{10}, {5}}
     * </code>
     * </pre>
     * 
     * @param vec
     *            the Vector2d object to convert
     * @return matrix array representing vector
     */
    public static double[][] toMatrixArray(Vector2d vec) {
        double[][] matrix = { { vec.getX() }, { vec.getY() } };
        return matrix;
    }

    /**
     * Creates a copy of this vector.
     * 
     * @return new Vector2d object containing a copy of this vector
     */
    public Vector2d clone() {
        return new Vector2d(this.x, this.y);
    }

    /**
     * Creates a copy of a Vector2d object and returns a new Vector2d object
     * containing the copy.
     * 
     * @param vec
     *            the Vector2d object to copy
     * @return new Vector2d object containing a copy of the argument Vector2d
     *         object
     */
    public static Vector2d clone(Vector2d vec) {
        return new Vector2d(vec.getX(), vec.getY());
    }

    /**
     * Sets the x, y components of the vector.
     * 
     * @param nX
     *            the x component of the vector.
     * @param nY
     *            the y component of the vector.
     */
    public void set(double nX, double nY) {
        this.x = nX;
        this.y = nY;
    }

    /**
     * Sets the x component of the vector.
     * 
     * @param x
     *            the x component of the vector.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y component of the vector.
     * 
     * @param y
     *            the y component of the vector.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Gets the x component of the vector.
     * 
     * @return the x component.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets the y component of the vector.
     * 
     * @return the y component.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Re-maps a number from one range to another. This method is not related to
     * the Vector2d class, it is just an method that is required, but could not
     * find a suitable class for.
     * 
     * @param val
     *            the number to map
     * @param inLowerBound
     *            the lower bound of the value's current range.
     * @param inUpperBound
     *            the upper bound of the value's current range.
     * @param outLowerBound
     *            the new lower bound of the value's target range.
     * @param outUpperBound
     *            the new upper bound of the value's target range.
     * @return the re-mapped number.
     */
    public static double map(double val, double inLowerBound,
            double inUpperBound, double outLowerBound, double outUpperBound) {
        return (((val - inLowerBound) * (outUpperBound - outLowerBound))
                / ((inUpperBound - inLowerBound))) + outLowerBound;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (obj instanceof Vector2d) {
            Vector2d vec = (Vector2d) obj;

            if (this.x == vec.x && this.y == vec.y) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }
}
