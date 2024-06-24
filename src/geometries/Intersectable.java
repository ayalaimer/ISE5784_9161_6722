package geometries;

import primitives.* ;
import java.util.List;

public abstract class Intersectable  {

    public static class GeoPoint
    {
        public Geometry geometry;
        public Point point;

        public GeoPoint(Geometry geo,Point point)
        {
            geometry=geo;
            this.point=point;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object instanceof GeoPoint other) {
                return this.geometry.equals(other.geometry) && this.point.equals(other.point);
            }
            return false;
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);


    public List<Point> findIntersections(Ray ray)
    {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(GeoPoint -> GeoPoint.point).toList();
    }
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

}
