package org;

public class Triangle {

    private final int a;
    private final int b;
    private final int c;

    public int getA() 
	{
        return a;
    }

    public int getB() 
	{
        return b;
    }

    public int getC() 
	{
        return c;
    }

    public Triangle(int a, int b, int c) 
	{
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean equi() 
	{
        return a == b&& b == c;
    }
}
