package com.mos.game_objects.shapes;

import com.mos.game_objects.shapes.module.Module;
import com.mos.graphics.Sprite;
import com.mos.util.Vector;

public class I_Shape extends Shape
{
    private static Sprite sprite;

    private State state = new State();

    private class State
    {
        private int x = 0;

        public int get()
        {
            return x;
        }

        public void increment()
        {
            x++;
            if (x == 4)
                x = 0;
        }

        public void decrement()
        {
            x--;
            if (x == -1)
                x = 3;
        }
    }

    public static void setSprite(Sprite s)
    {
        sprite = s;
    }

    public I_Shape()
    {
        int dif = MODULE_SIDE_PIXEL_LENGTH;
        Module m1 = new Module(SPAWN_POSITION.add(new Vector(0, 0).multiply(dif)).add(dif, 0), dif, dif, sprite);
        Module m2 = new Module(SPAWN_POSITION.add(new Vector(0, -1).multiply(dif)).add(dif, 0), dif, dif, sprite);
        Module m3 = new Module(SPAWN_POSITION.add(new Vector(0, 1).multiply(dif)).add(dif, 0), dif, dif, sprite);
        Module m4 = new Module(SPAWN_POSITION.add(new Vector(0, 2).multiply(dif)).add(dif, 0), dif, dif, sprite);

        modules.add(m1);
        modules.add(m2);
        modules.add(m3);
        modules.add(m4);
    }



    @Override
    public void rotateL()
    {
        rotateWithDegrees(-90);

        if (state.get() == 3)
            modules.forEach(module ->
            {
                module.setPosition(module.getPosition().add(0, MODULE_SIDE_PIXEL_LENGTH));
                module.setBoardPosition(module.getBoardPosition().add(0, 1));
            });

        else if (state.get() == 2)
            modules.forEach(module ->
            {
                module.setPosition(module.getPosition().add(MODULE_SIDE_PIXEL_LENGTH, 0));
                module.setBoardPosition(module.getBoardPosition().add(1, 0));
            });
        else if (state.get() == 1)
            modules.forEach(module ->
            {
                module.setPosition(module.getPosition().add(0, -MODULE_SIDE_PIXEL_LENGTH));
                module.setBoardPosition(module.getBoardPosition().add(0, -1));
            });
        else if (state.get() == 0)
            modules.forEach(module ->
            {
                module.setPosition(module.getPosition().add(-MODULE_SIDE_PIXEL_LENGTH, 0));
                module.setBoardPosition(module.getBoardPosition().add(-1, 0));
            });

        state.decrement();
    }

    @Override
    public void rotateR()
    {
        rotateWithDegrees(90);

        if (state.get() == 0)
            modules.forEach(module ->
            {
                module.setPosition(module.getPosition().add(0, MODULE_SIDE_PIXEL_LENGTH));
                module.setBoardPosition(module.getBoardPosition().add(0, 1));
            });
        else if (state.get() == 1)
            modules.forEach(module ->
            {
                module.setPosition(module.getPosition().add(-MODULE_SIDE_PIXEL_LENGTH, 0));
                module.setBoardPosition(module.getBoardPosition().add(-1, 0));
            });
        else if (state.get() == 2)
            modules.forEach(module ->
            {
                module.setPosition(module.getPosition().add(0, -MODULE_SIDE_PIXEL_LENGTH));
                module.setBoardPosition(module.getBoardPosition().add(0, -1));
            });
        else if (state.get() == 3)
            modules.forEach(module ->
            {
                module.setPosition(module.getPosition().add(MODULE_SIDE_PIXEL_LENGTH, 0));
                module.setBoardPosition(module.getBoardPosition().add(1, 0));
            });

        state.increment();
    }
}
