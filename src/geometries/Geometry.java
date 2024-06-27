package geometries;
import primitives.Color;
import primitives.Material;
import primitives.Vector;
import primitives.Point;

public abstract class Geometry extends Intersectable{

     protected Color emission = Color.BLACK;
     private Material material=new Material();

     public Color getEmission() {
          return emission;
     }
     public Geometry setEmission(Color emission) {
          this.emission = emission;
          return this;
     }
     public Material getMaterial() {
          return material;
     }
     public Geometry setMaterial(Material material) {
          this.material = material;
          return this;
     }


     public abstract Vector getNormal(Point point);
}
