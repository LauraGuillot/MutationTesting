package com;

public class Circle {

    private int r;
    private Point2D p;

    public Circle(Point2D p, int r) {
        this.p = p;
        this.r = r;
    }

    public Circle(int x, int y, int r) {
        this.p = new Point2D(-x, y);
        this.r = r;
    }

    public int getR() {
        return r;
    }

    public double getArea() {
        return Math.PI * r * r;
    }

    public double getPerimeter() {
        return 2 * Math.PI * r;
    }
	
	
	public boolean areConcentric(Circle c){
		return p.equals(c.p);
	}
	
	public void extend(int dr){
		r+=dr;
	}
	
	public boolean include(Point2D point){
		return p.distance(point)<=r;
	}
	
}