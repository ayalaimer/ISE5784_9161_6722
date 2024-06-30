package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Vector;
import primitives.Point;

/**
 * Abstract class representing a geometric shape that can be intersected by rays.
 * Provides methods for getting the emission color, material properties, and the normal vector at a point.
 */
public abstract class Geometry extends Intersectable {

     // The emission color of the geometry
     protected Color emission = Color.BLACK;

     // The material properties of the geometry
     private Material material = new Material();

     /**
      * Gets the emission color of the geometry.
      *
      * @return the emission color
      */
     public Color getEmission() {
          return emission;
     }

     /**
      * Sets the emission color of the geometry.
      *
      * @param emission the new emission color
      * @return the geometry itself for chaining
      */
     public Geometry setEmission(Color emission) {
          this.emission = emission;
          return this;
     }

     /**
      * Gets the material properties of the geometry.
      *
      * @return the material properties
      */
     public Material getMaterial() {
          return material;
     }

     /**
      * Sets the material properties of the geometry.
      *
      * @param material the new material properties
      * @return the geometry itself for chaining
      */
     public Geometry setMaterial(Material material) {
          this.material = material;
          return this;
     }

     /**
      * Abstract method to get the normal vector to the geometry at a given point.
      *
      * @param point the point at which to get the normal vector
      * @return the normal vector at the given point
      */
     public abstract Vector getNormal(Point point);
}
