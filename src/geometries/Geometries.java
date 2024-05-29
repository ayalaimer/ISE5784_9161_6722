package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{
    private final  List<Intersectable> myList = new LinkedList<>();
    public Geometries(){};
    public Geometries(Intersectable geometries){};
    public void add(Intersectable... geometries){
        for (int i = 0; i < geometries.length; i++) {
            myList.add(geometries[i]);
        }
    }
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
