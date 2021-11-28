package com.mos.scene;

import java.awt.*;

public abstract class Scene
{
    public abstract void input();
    
    public abstract void update();
    
    public abstract void render(Graphics2D g);
}
