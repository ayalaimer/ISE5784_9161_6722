package primitives;

import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;
import geometries.Intersectable.GeoPoint;

public class Ray {
    private final Point head;
    private final Vector direction;

    /**
     * Constructor to initialize the Ray with a head point and a direction vector.
     * The direction vector is normalized.
     *
     * @param head the starting point of the ray
     * @param direction the direction of the ray
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize(); // Using the normalized method of Vector
    }

    /**
     * Gets the head point of the ray.
     *
     * @return the head point of the ray
     */
    public Point getHead() {
        return head;
    }

    /**
     * Gets the direction vector of the ray.
     *
     * @return the direction vector of the ray
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * Computes the point on the ray at a given distance t from the head point.
     *
     * @param t the distance from the head point
     * @return the point on the ray at distance t
     */
    public Point getPoint(double t) {
        if (isZero(t)) {
            return head;
        }
        return head.add(direction.scale(t));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Ray other)
                && this.head.equals(other.head)
                && this.direction.equals(other.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, direction);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "head=" + head +
                ", direction=" + direction +
                '}';
    }

    /**
     * Finds the closest point to this ray among a list of points.
     *
     * @param points the list of points to search for the closest point.
     * @return the closest point to this ray, or null if the list is empty.
     */
    public Point findClosestPoint(List<Point> points) {
        if (points == null || points.isEmpty()) {
            return null;
        }

        Point closestPoint = null;
        double minDistance = Double.MAX_VALUE;

        for (Point point : points) {
            double distance = head.distanceSquared(point);
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = point;
            }
        }

        return closestPoint;
    }
    public Point findClosestGeoPoint(){
return null;
    }
}
