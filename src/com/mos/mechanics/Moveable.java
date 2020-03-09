package com.mos.mechanics;

import com.mos.util.Vector;

public interface Moveable
{
    public void move(Vector direction, int stepCount);
    //public void move(Vector direction, double stepCount);
}
