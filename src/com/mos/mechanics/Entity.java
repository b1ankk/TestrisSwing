package com.mos.mechanics;

import com.mos.graphics.Renderable;
import com.mos.graphics.Sprite;
import com.mos.graphics.Spriteable;
import com.mos.util.Vector;

import java.awt.*;

public abstract class Entity implements Collidable, Moveable, Renderable, Positionable, Spriteable
{
    protected Vector position;
    protected int width;
    protected int height;
    protected Sprite sprite;
    protected Collider collider;
    
    
    public Entity(Vector position, int width, int height, Sprite sprite)
    {
        this.position = position;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
        
        Physics.addCollidable(this);
    }
    
    @Override
    public void move(Vector direction, int stepCount)
    {
        position = position.add(direction.multiply(stepCount));
        collider.move(direction, stepCount);
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    @Override
    public Vector getPosition()
    {
        return position;
    }
    
    public void setPosition(Vector position)
    {
        this.position = position;
    }
    
    public void setPosition(int x, int y)
    {
        this.position = new Vector(x, y);
    }
    
    @Override
    public void render(Graphics2D g)
    {
        g.drawImage(sprite.getImage(), position.getX(), position.getY(), null);
    }
    
    @Override
    public void setSprite(Sprite sprite)
    {
        this.sprite = sprite;
    }
    
    @Override
    public void onCollision(Collidable collidable)
    {
    
    }
}
