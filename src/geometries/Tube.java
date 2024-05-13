package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;
public class Tube extends RadialGeometry{

    protected final Ray axis;
    public Tube(Ray myAxis,double myRadius){
        super(myRadius);
        this.axis=myAxis;
    }
    public Vector getNormal(Point p){
        return null;
    }
}
