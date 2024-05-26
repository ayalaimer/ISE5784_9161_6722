package geometries;
import primitives.Vector;
import primitives.Point;

public interface Geometry extends Intersectable{
     Vector getNormal(Point p);
}
