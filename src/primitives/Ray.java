package primitives;

import static primitives.Util.isZero;

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
 }
