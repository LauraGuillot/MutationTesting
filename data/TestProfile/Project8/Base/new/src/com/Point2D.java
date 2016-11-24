package com;

public class Point2D{
	private int x;
	private int y;
	
	public Point2D(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void translate(int dx,int dy){
		this.x+=dx;
		this.y+=dy;
	}
	
	public boolean equals(Point2D p){
		return this.x==p.getX()&&this.y==p.getY();
	}
	
	
	public double distance(Point2D p){
		return Math.sqrt((this.x-p.getX())*(this.x-p.getX())+(this.y-p.getY())*(this.y-p.getY()));
	}
}