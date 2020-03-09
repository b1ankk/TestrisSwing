package com.mos.mechanics;

import com.mos.util.Vector;

public interface Collidable
{
    Collider getCollider();
    boolean isContaining(int x, int y);
    boolean isColliding(Collider c);
    boolean isColliding(Collidable c);
    boolean canMove(Vector direction, int distance);
    void onCollision(Collidable collidable);
}
