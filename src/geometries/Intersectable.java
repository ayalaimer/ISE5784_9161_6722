package geometries;

import primitives.* ;
import java.util.List;

public abstract class Intersectable  {

    public List<Point> findIntersections(Ray ray) {
        return null;
    }
     public List<GeoPoint> findGeoIntersections(Ray ray) {
             return findGeoIntersectionsHelper(ray);
     }
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);


 
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        public GeoPoint(Geometry geometry,Point point){
                this.geometry=geometry;
                this.point=point;
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
            ", point=" + point +
                    '}';
        }
        @Override
         public boolean equals(Object obj) {
             if (this == obj) {
                 return true;
             }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            GeoPoint other = (GeoPoint) obj;
                return this.geometry == other.geometry && this.point.equals(other.point);
        }

    }
}
                                                         