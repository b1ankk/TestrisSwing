package com.mos.mechanics;

import java.util.HashSet;
import java.util.Set;

public class Physics
{
    private static Set<Collidable> collidableObjects = new HashSet<>();

    public static void addCollidable(Collidable c)
    {
        collidableObjects.add(c);
    }

    public static void checkCollisions()
    {
        for (Collidable c1 : collidableObjects)
        {
            for (Collidable c2 : collidableObjects)
            {
                if (c1 != c2)
                {
                    if (c1.isColliding(c2))
                    {
                        c1.onCollision(c2);
                    }
                }
            }
        }
    }
}
