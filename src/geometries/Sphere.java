package geometries;

import primitives.Point;
import primitives.Vector;
public class Sphere extends RadialGeometry{
    private final Point center;
    public Sphere(Point myCenter,double myRadius){
        super(myRadius);
        this.center=myCenter;
    }
    public Vector getNormal(Point p){
        return null;
    }
}
