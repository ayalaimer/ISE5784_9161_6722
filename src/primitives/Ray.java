package primitives;

public class Ray {
    private final Point head;
    private final Vector direction;

    public Ray(Point p, Vector v) {
        head = new Point(p.xyz.d1, p.xyz.d2, p.xyz.d3);
        direction= new Vector(v.xyz.d1, v.xyz.d2, v.xyz.d3);
        direction.normalize();
    }
    public Point getHead() {
        return head;
    }

    public Vector getDirection() {
        return direction;
    }
}
