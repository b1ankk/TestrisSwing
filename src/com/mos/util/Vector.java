package com.mos.util;

import java.util.Objects;

public class Vector
{
    private final int x;
    private final int y;
    
    public static Vector LEFT = new Vector(-1, 0);
    public static Vector RIGHT = new Vector(1, 0);
    public static Vector DOWN = new Vector(0, 1);
    public static Vector UP = new Vector(0, -1);
    
    
    public Vector()
    {
        this(0, 0);
    }
    
    public Vector(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Vector add(Vector v)
    {
        return new Vector(this.x + v.x, this.y + v.y);
    }
    
    public Vector add(int x, int y)
    {
        return new Vector(this.x + x, this.y + y);
    }
    
    public Vector negate()
    {
        return new Vector(-x, -y);
    }
    
    public Vector multiply(int multiplier)
    {
        return new Vector(x * multiplier, y * multiplier);
    }
    
    public Vector multiply(double multiplier)
    {
        return new Vector((int) Math.round(x * multiplier), (int) Math.round(y * multiplier));
    }
    
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;
        Vector vector = (Vector) o;
        return x == vector.x &&
               y == vector.y;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
    
    @Override
    public String toString()
    {
        return "Vector{" +
               "x=" + x +
               ", y=" + y +
               '}';
    }
}
