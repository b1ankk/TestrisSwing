package com.mos.game_objects.shapes;

import com.mos.game_objects.shapes.module.Module;
import com.mos.scene.GameScene;
import com.mos.util.Vector;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public abstract class Shape
{
    LinkedHashSet<Module> modules = new LinkedHashSet<>();

    public final static int MODULE_SIDE_PIXEL_LENGTH = 35;
    final static Vector SPAWN_POSITION = new Vector(188, 48);


    public void render(Graphics2D g)
    {
        modules.forEach(m -> m.render(g));
    }

    public void move(Vector direction, int distance)
    {
        modules.forEach(module -> module.move(direction, distance));
    }

    public HashSet<Module> getModules()
    {
        return modules;
    }

    public void rotateR()
    {
        rotateWithDegrees(90);
    }

    public void rotateL()
    {
        rotateWithDegrees(-90);
    }

    void rotateWithDegrees(double degrees)
    {
        Iterator<Module> itr = modules.iterator();
        Module baseModule = itr.next();
        Vector subtractValue = baseModule.getPosition();

        while (itr.hasNext())
        {
            Module m = itr.next();
            Vector pos = m.getPosition().add(subtractValue.negate());
            int x = pos.getX();
            int y = pos.getY();

            double angle = Math.toRadians(degrees);

            double X = x * Math.cos(angle) - y * Math.sin(angle);
            double Y = x * Math.sin(angle) + y * Math.cos(angle);

            X += subtractValue.getX();
            Y += subtractValue.getY();

            m.setPosition(new Vector((int)Math.round(X), (int)Math.round(Y)));

            int xBoard = m.getBoardPosition().getX() - baseModule.getBoardPosition().getX();
            int yBoard = m.getBoardPosition().getY() - baseModule.getBoardPosition().getY();

            double XBoard = xBoard * Math.cos(angle) - yBoard * Math.sin(angle);
            double YBoard = xBoard * Math.sin(angle) + yBoard * Math.cos(angle);

            XBoard += baseModule.getBoardPosition().getX();
            YBoard += baseModule.getBoardPosition().getY();

            m.setBoardPosition(new Vector((int)Math.round(XBoard), (int)Math.round(YBoard)));
        }
    }
}
