package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {


    @Test
    void testFindIntersections() {

        final Triangle triangle=new Triangle(new Point(1,0,0),new Point(-1,0,0),new Point(0,0,1));
        final Vector v100=new Vector(1,0,0);

        //TC01:
        List<Point>result1=triangle.findIntersections(new Ray(new Point(-1,0,0.5),v100));
        assertEquals(1,result1.size());
        assertEquals(new Point(0,0,0),result1.get(0));

        //TC02:
        assertNull(triangle.findIntersections(new Ray(new Point(-1,0,0.5) ,v100)));

        //TC03:
        assertNull(triangle.findIntersections(new Ray(new Point(-1,0,2) ,v100)));


        //TC04:
        assertNull(triangle.findIntersections(new Ray(new Point(-1,0.5,0) ,v100)));

        //TC05:
        assertNull(triangle.findIntersections(new Ray(new Point(-1,0,1) ,v100)));

        //TC06:
        assertNull(triangle.findIntersections(new Ray(new Point(-1,0,-1) ,v100)));

    }



}