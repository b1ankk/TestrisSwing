package com.mos.mechanics;

import com.mos.util.Vector;

public abstract class Collider implements Moveable
{
    int posX;
    int posY;

    public Collider(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
    }

    public abstract boolean isContaining(int x, int y);

    public abstract boolean isColliding(Collider collider);
    public boolean isColliding(Collidable collidable)
    {
        return isColliding(collidable.getCollider());
    }

    public abstract boolean canMove(Vector direction, int distance);

    @Override
    public void move(Vector direction, int stepCount)
    {
        Vector newVector = new Vector(posX, posY);
        newVector = newVector.add(direction.multiply(stepCount));
        posX = newVector.getX();
        posY = newVector.getY();
    }
}
