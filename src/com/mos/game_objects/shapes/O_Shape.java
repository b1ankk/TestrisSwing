package com.mos.game_objects.shapes;

import com.mos.game_objects.shapes.module.Module;
import com.mos.graphics.Sprite;
import com.mos.util.Vector;

public class O_Shape extends Shape
{
    private static Sprite sprite;

    public O_Shape()
    {
        int dif = MODULE_SIDE_PIXEL_LENGTH;
        Module m1 = new Module(SPAWN_POSITION.add(new Vector(1, 0).multiply(dif)), dif, dif, sprite);
        Module m2 = new Module(SPAWN_POSITION.add(new Vector(1, 1).multiply(dif)), dif, dif, sprite);
        Module m3 = new Module(SPAWN_POSITION.add(new Vector(0, 0).multiply(dif)), dif, dif, sprite);
        Module m4 = new Module(SPAWN_POSITION.add(new Vector(0, 1).multiply(dif)), dif, dif, sprite);

        modules.add(m1);
        modules.add(m2);
        modules.add(m3);
        modules.add(m4);
    }

    public static void setSprite(Sprite s)
    {
        sprite = s;
    }
}
