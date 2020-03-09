package com.mos.game_objects;

import com.mos.game_objects.shapes.Shape;
import com.mos.game_objects.shapes.module.Module;
import com.mos.graphics.Renderable;
import com.mos.graphics.Sprite;
import com.mos.graphics.Spriteable;
import com.mos.mechanics.Positionable;
import com.mos.util.Vector;

import java.awt.*;

public class Board implements Renderable, Positionable, Spriteable
{
    public final int WIDTH = 10;
    public final int HEIGHT = 20;
    public final int BORDER_THICKNESS = 16;

    private static final String spritePath = "sprites/TetrisBoard10x20.png";
    private static final String spriteName = "TetrisBoard10x20";

    private int posX;
    private int posY;

    public final int PIXEL_WIDTH = WIDTH * Shape.MODULE_SIDE_PIXEL_LENGTH;
    public final int PIXEL_HEIGHT = HEIGHT * Shape.MODULE_SIDE_PIXEL_LENGTH;

    private Sprite sprite;

    private Module[][] boardSheet = new Module[HEIGHT][WIDTH];

    public Board(int posX, int posY)
    {
        this(posX, posY, null);
    }

    public Board(int posX, int posY, Sprite sprite)
    {
        this.posX = posX;
        this.posY = posY;
        setSprite(sprite);
    }

    public void setSprite(Sprite sprite)
    {
        this.sprite = sprite;
    }

    @Override
    public Vector getPosition()
    {
        return new Vector(posX, posY);
    }

    @Override
    public void render(Graphics2D g)
    {
        g.drawImage(sprite.getImage(), posX, posY, null);
        renderModules(g);
    }

    private void renderModules(Graphics2D g)
    {
        for (Module[] modules : boardSheet)
            for (Module module : modules)
            {
                if (module != null)
                    module.render(g);
            }
    }

    static String getSpritePath()
    {
        return spritePath;
    }

    public static String getSpriteName()
    {
        return spriteName;
    }

    public Module[][] getBoardSheet()
    {
        return boardSheet;
    }

    public void addShapesModules(Shape shape)
    {
        shape.getModules().forEach(module ->
            boardSheet[module.getBoardPosition().getY()][module.getBoardPosition().getX()] = module);
    }

    public int returnDeleteFullRows()
    {
        int counter = 0;
        int bookmark = -1;
        for (int y = boardSheet.length - 1; y >= 0; y--)
        {
            boolean full = true;
            for (Module module : boardSheet[y])
            {
                if (module == null)
                {
                    full = false;
                    break;
                }
            }

            if (full)
            {
                bookmark = y;
            }
            else
            {
                if (bookmark != -1)
                {
                    int i = bookmark;
                    for (int j = y; i >= 0 && j >= 0; i--, j--)
                    {
                        boardSheet[i] = boardSheet[j];
                        for (Module m : boardSheet[i])
                        {
                            if (m != null)
                                for(int k = i - j; k > 0; k--)
                                    m.move(Vector.DOWN, Shape.MODULE_SIDE_PIXEL_LENGTH);
                        }
                        counter++;
                    }
                    for (; i >=0; i--)
                    {
                        boardSheet[i] = new Module[WIDTH];
                    }
                    bookmark = -1;
                }
            }

        }
        return  counter;
    }
}
