package primitives;

/**
 * The Point class represents a point in three-dimensional space.
 * It provides methods for basic point operations such as addition, subtraction, and distance calculation.
 */
public class Point {

    /**
     * The coordinates of the point.
     */
    protected final Double3 xyz;

    /**
     * A constant representing the origin point (0, 0, 0).
     */
    public static final Point ZERO = new Point(0, 0, 0);

    /**
     * Constructs a new Point with the specified x, y, and z coordinates.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     * @param z the z coordinate of the point
     */
    public Point(double x, double y, double z) {
        this.xyz = new Double3(x, y, z);
    }

    /**
     * Constructs a new Point with the specified coordinates from a Double3 object.
     *
     * @param xyz the Double3 object containing the point coordinates
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Subtracts the specified point from this point and returns the resulting vector.
     *
     * @param other the point to subtract
     * @return the resulting vector after subtraction
     */
    public Vector subtract(Point other) {
        return new Vector(this.xyz.subtract(other.xyz));
    }

    /**
     * Adds the specified vector to this point and returns the resulting point.
     *
     * @param other the vector to add
     * @return the resulting point after addition
     */
    public Point add(Vector other) {
        return new Point(this.xyz.add(other.xyz));
    }

    /**
     * Computes the squared distance between this point and the specified point.
     *
     * @param other the point to compute the distance to
     * @return the squared distance between the two points
     */
    public double distanceSquared(Point other) {
        double dx = this.xyz.d1 - other.xyz.d1;
        double dy = this.xyz.d2 - other.xyz.d2;
        double dz = this.xyz.d3 - other.xyz.d3;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Computes the distance between this point and the specified point.
     *
     * @param other the point to compute the distance to
     * @return the distance between the two points
     */
    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point other) && xyz.equals(other.xyz);
    }

    /**
     * Returns a string representation of the point.
     *
     * @return a string representation of the point
     */
    @Override
    public String toString() {
        return "(" + this.xyz.d1 + "," + this.xyz.d2 + "," + this.xyz.d3 + ")";
    }
}
