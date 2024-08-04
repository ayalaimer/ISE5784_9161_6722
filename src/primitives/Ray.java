package primitives;

import geometries.Intersectable.GeoPoint;
import renderer.BlackBoard;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;

public class Ray {
    private final Point head;
    private final Vector direction;
    /** A small constant value used to slightly move the origin of the shadow rays to avoid self-shadowing. */
    private static final double DELTA = 0.1;
    /**
     * Constructor to initialize the Ray with a head point and a direction vector.
     * The direction vector is normalized.
     *
     * @param head      the starting point of the ray
     * @param direction the direction of the ray
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize(); // Using the normalized method of Vector
    }
    /**
     * Constructs a new Ray object with the specified starting point, direction, and normal.
     * The starting point is moved slightly in the direction of the normal to avoid self-shadowing.
     * @param p The starting point of the ray.
     * @param direction The direction of the ray.
     * @param n The normal vector at the starting point.
     */
    public Ray(Point p, Vector direction, Vector n) {
        this.direction = direction.normalize();
        double nv = n.dotProduct(this.direction);
        Vector dltVector=n.scale(nv<0 ? -DELTA : DELTA);//move the normal a bit by delta
        head = p.add(dltVector);
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
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }
    /** Calculates a beam of rays for a blackboard.
     * @param blackBoard The blackboard used to calculate the rays.
     * @return A list of rays calculated for the blackboard.
     */
    public List<Ray> calculateBeam(BlackBoard blackBoard) {
        List<Ray> rays = new LinkedList<>();
        List<Point> points = blackBoard.setRays(this);
        for (Point point : points) {
            rays.add(new Ray(head, point.subtract(head)));
        }
        return rays;
    }

    /**
     * Finds the closest GeoPoint to a given reference point.
     *
     * @param geoPoints the list of GeoPoints to search through
     * @return the GeoPoint closest to the reference point, or null if the list is empty
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints) {

        // If the list is empty, return null
        if (geoPoints == null || geoPoints.isEmpty()) {
            return null;
        }

        // Initialize the closest point as the first point in the list
        GeoPoint closestPoint = geoPoints.getFirst();
        double minDistance = head.distanceSquared(closestPoint.point);

        // Iterate over the remaining points in the list
        for (GeoPoint geoPoint : geoPoints) {
            double distance = head.distanceSquared(geoPoint.point);

            // If the current point is closer, update the closest point and minimum distance
            if (distance < minDistance) {
                closestPoint = geoPoint;
                minDistance = distance;
            }
        }

        // Return the closest point found
        return closestPoint;
    }


}
