package com.mos.mechanics;

import com.mos.util.Vector;

public class ColliderRectangle extends Collider
{
    private int width;
    private int height;


    public ColliderRectangle(int posX, int posY, int width, int height)
    {
        super(posX, posY);
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean isContaining(int x, int y)
    {
        return (x >= posX && x <= posX + width) &&
                (y >= posY && y <= posY + height);
    }

    @Override
    public boolean isColliding(Collider collider)
    {
        return false;///////////////////TO DOOOOOOOOOOOOO
    }

    @Override
    public boolean canMove(Vector direction, int distance)
    {
        return false;///TO DOOOOOOOOOOOOOOOOOOOOOOOOOOO
    }
}
