package primitives;

public class Point {

    protected final Double3 xyz;
    public static final Double3 ZERO= new Double3(0, 0, 0);

    //constructors:
    public Point(double x,double y, double z){
        this.xyz=new Double3(x,y,z);
    }
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }
    public Vector subtract(Point other){
        return new Vector (this.xyz.subtract(other.xyz));
    }
    public Point add(Vector other){
        return new Point (this.xyz.add(other.xyz));
    }
    public double distanceSquared(Point other){
        double dx = this.xyz.d1 - other.xyz.d1;
        double dy = this.xyz.d2 - other.xyz.d2;
        double dz = this.xyz.d3 - other.xyz.d3;
        return dx * dx + dy * dy+ dz*dz;
    }
    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }


    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        return (obj instanceof Point other)
                && this.xyz.equals(other.xyz);
    }

    @Override
    public String toString() {return "(" + this.xyz.d1 + "," + this.xyz.d2 + "," + this.xyz.d3 + ")";}

}
