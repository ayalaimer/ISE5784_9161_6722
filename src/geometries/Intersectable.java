package geometries;

import primitives.* ;
import java.util.List;

public interface Intersectable  {
    List<Point> findIntersectable(Ray ray);
}
