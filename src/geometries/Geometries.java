package geometries;

import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * The Geometries class represents a collection of intersectable geometries.
 * It allows adding multiple geometries and finding their intersection points with a given ray.
 */
public class Geometries extends Intersectable {
    private final List<Intersectable> geometries = new LinkedList<>();

    /**
     * Default constructor. Initializes an empty list of geometries.
     */
    public Geometries() {
    }

    /**
     * Constructor that initializes the list with the given geometries.
     *
     * @param geometries the geometries to add to the collection
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * Adds the given geometries to the collection.
     *
     * @param geometries the geometries to add
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geometry : geometries) {
            this.geometries.add(geometry);
        }
    }

    /**
     * Finds the intersection points of the given ray with all geometries in the collection.
     *
     * @param ray the ray to find intersections with
     * @return a list of intersection points, or null if no intersections were found
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> points = null; // Initialize the list variable as null

        // Iterate over all geometries in the composite structure
        for (Intersectable geometry : this.geometries) {
            // Find intersection points of the current geometry with the given ray
            List<GeoPoint> geometryIntersections = geometry.findGeoIntersectionsHelper(ray);

            // If intersection points were found
            if (geometryIntersections != null) {
                // If the points list has not been created yet, create it
                if (points == null) {
                    points = new LinkedList<>();
                }
                // Add all intersection points to the points list
                points.addAll(geometryIntersections);
            }
        }

        // Return the list of intersection points, or null if no intersections were found
        return points;
    }
}
