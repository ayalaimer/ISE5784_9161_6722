package primitives;

/**
 * The Vector class represents a vector in three-dimensional space.
 * It extends the Point class and provides additional operations specific to vectors.
 */
public class Vector extends Point {

    /**
     * Constructs a new Vector with the specified x, y, and z components.
     * Throws an IllegalArgumentException if the vector is the zero vector.
     *
     * @param x the x component of the vector
     * @param y the y component of the vector
     * @param z the z component of the vector
     * @throws IllegalArgumentException if the vector is zero
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (this.xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Value cannot be equal to ZERO");
        }
    }

    /**
     * Constructs a new Vector with the specified components from another Double3 object.
     * Throws an IllegalArgumentException if the vector is the zero vector.
     *
     * @param other the Double3 object containing the vector components
     * @throws IllegalArgumentException if the vector is zero
     */
    public Vector(Double3 other) {
        super(other);
        if (this.xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Value cannot be equal to ZERO");
        }
    }

    /**
     * Adds the specified vector to this vector and returns the resulting vector.
     *
     * @param other the vector to add
     * @return the resulting vector after addition
     */
    public Vector add(Vector other) {
        return new Vector(this.xyz.add(other.xyz));
    }

    /**
     * Scales this vector by the specified scalar and returns the resulting vector.
     *
     * @param num the scalar to scale the vector by
     * @return the resulting vector after scaling
     */
    public Vector scale(double num) {
        return new Vector(this.xyz.scale(num));
    }

    /**
     * Computes the dot product of this vector and the specified vector.
     *
     * @param other the vector to compute the dot product with
     * @return the dot product of the two vectors
     */
    public double dotProduct(Vector other) {
        return (this.xyz.d1 * other.xyz.d1) + (this.xyz.d2 * other.xyz.d2) + (this.xyz.d3 * other.xyz.d3);
    }

    /**
     * Computes the cross product of this vector and the specified vector and returns the resulting vector.
     *
     * @param other the vector to compute the cross product with
     * @return the resulting vector after the cross product
     */
    public Vector crossProduct(Vector other) {
        return new Vector(
                (this.xyz.d2 * other.xyz.d3) - (this.xyz.d3 * other.xyz.d2),
                (this.xyz.d3 * other.xyz.d1) - (this.xyz.d1 * other.xyz.d3),
                (this.xyz.d1 * other.xyz.d2) - (this.xyz.d2 * other.xyz.d1)
        );
    }

    /**
     * Computes the squared length of this vector.
     *
     * @return the squared length of the vector
     */
    public double lengthSquared() {
        return (this.xyz.d1 * this.xyz.d1) + (this.xyz.d2 * this.xyz.d2) + (this.xyz.d3 * this.xyz.d3);
    }

    /**
     * Computes the length of this vector.
     *
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vector getOrthogonalVector() {
        Vector referenceVector;

        // If the vector is parallel to (0, 1, 0), use (1, 0, 0) instead
        if (Util.isZero(this.dotProduct(new Vector(0, 1, 0)))) {
            referenceVector = new Vector(1, 0, 0);
        } else {
            referenceVector = new Vector(0, 1, 0);
        }

        // Calculate the orthogonal vector using cross product
        Vector orthogonal = this.crossProduct(referenceVector);

        // Normalize the orthogonal vector
        return orthogonal.normalize();
    }
    /**
     * Rotates this vector around a given axis by a specified angle.
     *
     * @param axis The axis around which to rotate.
     * @param angleRadians The angle of rotation in radians.
     * @return The rotated vector.
     */
    public Vector rotateAround(Vector axis, double angleRadians) {
        double cosAngle = Math.cos(angleRadians);
        double sinAngle = Math.sin(angleRadians);
        double oneMinusCosAngle = 1 - cosAngle;

        // Calculate the components of the new vector after rotation
        double newX = (cosAngle + axis.xyz.d1 * axis.xyz.d1 * oneMinusCosAngle) * this.xyz.d1 +
                (axis.xyz.d1 * axis.xyz.d2 * oneMinusCosAngle - axis.xyz.d3 * sinAngle) * this.xyz.d2 +
                (axis.xyz.d1 * axis.xyz.d3 * oneMinusCosAngle + axis.xyz.d2 * sinAngle) * this.xyz.d3;

        double newY = (axis.xyz.d2 * axis.xyz.d1 * oneMinusCosAngle + axis.xyz.d3 * sinAngle) * this.xyz.d1 +
                (cosAngle + axis.xyz.d2 * axis.xyz.d2 * oneMinusCosAngle) * this.xyz.d2 +
                (axis.xyz.d2 * axis.xyz.d3 * oneMinusCosAngle - axis.xyz.d1 * sinAngle) * this.xyz.d3;

        double newZ = (axis.xyz.d3 * axis.xyz.d1 * oneMinusCosAngle - axis.xyz.d2 * sinAngle) * this.xyz.d1 +
                (axis.xyz.d3 * axis.xyz.d2 * oneMinusCosAngle + axis.xyz.d1 * sinAngle) * this.xyz.d2 +
                (cosAngle + axis.xyz.d3 * axis.xyz.d3 * oneMinusCosAngle) * this.xyz.d3;

        return new Vector(newX, newY, newZ);
    }

    /**
     * Normalizes this vector (scales it to length 1) and returns the resulting unit vector.
     *
     * @return the normalized (unit) vector
     */
    public Vector normalize() {
        double len = length();
        return new Vector(this.xyz.reduce(len));
    }
}
