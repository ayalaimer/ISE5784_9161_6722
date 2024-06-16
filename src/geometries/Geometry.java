package geometries;
import primitives.Vector;
import primitives.Point;

import java.awt.*;

public abstract class Geometry implements Intersectable{
    public abstract Vector getNormal(Point p);
     protected Color emission=Color.BLACK;

     public Color getEmission() {
          return emission;
     }
     public Geometry setEmission(Color color){
         this.emission = color;
         return this;
     }
}

