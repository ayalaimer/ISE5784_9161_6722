package primitives;

public class Vector extends Point{

    public Vector(double x,double y, double z) {
        super(x,y,z);
        if(this.equals(ZERO))
            throw new IllegalArgumentException("Value can not be equle to ZERO");
    }
    public Vector(Double3 other){
        super(other.d1,other.d2,other.d3);
        if(super.equals(ZERO))
            throw new IllegalArgumentException("Value can not be equle to ZERO");
    }
    //public Vector add(Vector other){

    public Vector scale(double num){
      return new Vector (this.xyz.scale(num));
    }
    public double dotProduct(Vector other){
        return (this.xyz.d1*other.xyz.d1)+(this.xyz.d2*other.xyz.d2)+(this.xyz.d2*other.xyz.d2);
    }
    public Vector crossProduct(Vector other){
        return new Vector((this.xyz.d2*other.xyz.d3)-(this.xyz.d3*other.xyz.d2),(this.xyz.d3*other.xyz.d1)-(this.xyz.d1*other.xyz.d3),(this.xyz.d1*other.xyz.d2)-(this.xyz.d2*other.xyz.d1));
    }
    public double lengthSquared(){
        return (this.xyz.d1 *  this.xyz.d1 +  this.xyz.d2 *  this.xyz.d2 +  this.xyz.d3 *  this.xyz.d3);
    }
    public double length(){
        return Math.sqrt(lengthSquared());
    }
    public Vector normalize(){
        double len=length();
        return new Vector(this.xyz.d1/len,this.xyz.d2/len,this.xyz.d3/len);
    }



}
