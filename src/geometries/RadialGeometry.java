package geometries;

import primitives.Vector;
import primitives.Point;

abstract class RadialGeometry implements Geometry{
    protected final double radius;
    public abstract Vector getNormal(Point p);
    public RadialGeometry(double myRadius){
        this.radius=myRadius;
    }
}
