package com.mos.game_objects.shapes.module;

import com.mos.game_objects.shapes.Shape;
import com.mos.graphics.Sprite;
import com.mos.mechanics.Collidable;
import com.mos.mechanics.Collider;
import com.mos.mechanics.ColliderRectangle;
import com.mos.mechanics.Entity;
import com.mos.scene.GameScene;
import com.mos.util.Vector;

import java.awt.*;

public class Module extends Entity
{
    private Vector boardPosition;
    
    public Module(Vector position, int width, int height, Sprite sprite)
    {
        super(position, width, height, sprite);
        collider = new ColliderRectangle(position.getX(), position.getY(), width, height);
        int x = (super.position.getX() - GameScene.BOARD_POSITION_X - GameScene.getBoard().BORDER_THICKNESS) /
                Shape.MODULE_SIDE_PIXEL_LENGTH;
        int y = (super.position.getY() - GameScene.BOARD_POSITION_Y - GameScene.getBoard().BORDER_THICKNESS) /
                Shape.MODULE_SIDE_PIXEL_LENGTH;
        boardPosition = new Vector(x, y);
    }
    
    @Override
    public Collider getCollider()
    {
        return collider;
    }
    
    @Override
    public boolean isColliding(Collidable c)
    {
        return collider.isColliding(c);
    }
    
    @Override
    public boolean isContaining(int x, int y)
    {
        return collider.isContaining(x, y);
    }
    
    @Override
    public boolean isColliding(Collider c)
    {
        return super.collider.isColliding(c);
    }
    
    @Override
    public boolean canMove(Vector direction, int distance)
    {
        return collider.canMove(direction, distance);
    }
    
    @Override
    public void move(Vector direction, int stepCount)
    {
        super.move(direction, stepCount);
        boardPosition = boardPosition.add(direction);
    }
    
    @Override
    public void render(Graphics2D g)
    {
        if (!isAboveBoard())
            super.render(g);
    }
    
    public boolean isAboveBoard()
    {
        return position.getY() < GameScene.BOARD_POSITION_Y;
    }
    
    public Vector getBoardPosition()
    {
        return boardPosition;
    }
    
    public void setBoardPosition(Vector boardPosition)
    {
        this.boardPosition = boardPosition;
    }
}
