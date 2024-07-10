package geometries;

import primitives.Point;
import primitives.Vector;

abstract class RadialGeometry extends Geometry {
    protected final double radius;

    public RadialGeometry(double myRadius) {
        this.radius = myRadius;
    }

    public abstract Vector getNormal(Point p);
}
