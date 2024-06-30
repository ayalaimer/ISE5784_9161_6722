package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Abstract class representing an Intersectable object in the scene.
 * Provides methods to find intersections of rays with geometrical objects.
 */
public abstract class Intersectable {

    /**
     * Helper method to find intersections of a ray with geometries.
     * This method is implemented by subclasses to provide specific intersection logic.
     *
     * @param ray the ray to intersect with the geometries
     * @return a list of GeoPoints representing the intersections
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * Finds the intersection points of a ray with geometries.
     *
     * @param ray the ray to intersect with the geometries
     * @return a list of points representing the intersections, or null if there are no intersections
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(geoPoint -> geoPoint.point).toList();
    }

    /**
     * Finds the GeoPoints of intersections of a ray with geometries.
     *
     * @param ray the ray to intersect with the geometries
     * @return a list of GeoPoints representing the intersections, or null if there are no intersections
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * Class representing a point of intersection between a ray and a geometry.
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * Constructs a GeoPoint with the specified geometry and point.
         *
         * @param geo   the geometry intersected by the ray
         * @param point the point of intersection
         */
        public GeoPoint(Geometry geo, Point point) {
            geometry = geo;
            this.point = point;
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
}
