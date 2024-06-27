package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{

    public Point position;
    private double kC=1,kL=0,kQ=0;

    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight(Color intensity, Point position ) {
        super(intensity);
        this.position=position;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(this.position);
        return this.getIntensity().scale(1/(kC+kL*d+kQ*d*d));    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(this.position).normalize();

    }

}