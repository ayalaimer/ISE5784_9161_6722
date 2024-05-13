package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{
   private final Point myPoint;
   private final Vector myNormal;

public Plane(Point x,Point y,Point z){
    myNormal=null;
    myPoint=x;
}
    public Plane(Point p, Vector v){
        myPoint=p;
        myNormal=v.normalize();
    }
public Vector getNormal(){
    return myNormal;
}
public Vector getNormal(Point p){
    return myNormal;
}


}
