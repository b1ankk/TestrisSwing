package com.mos.graphics;

import java.util.List;

public class SubSpriteFileProperties
{
    private boolean isSpriteSheet;
    private int width;
    private int height;
    private int subSpriteAmount;

    private List<String> names;

    public SubSpriteFileProperties()
    {
        isSpriteSheet = false;
    }

    public SubSpriteFileProperties(final int width, final int height, final int subSpriteAmount)
    {
        isSpriteSheet = true;
        this.width = width;
        this.height = height;
        this.subSpriteAmount = subSpriteAmount;
    }

    public SubSpriteFileProperties(final int width, final int height, final int subSpriteAmount, List<String> names)
    {
        this(width, height, subSpriteAmount);
        this.names = names;
    }

    public boolean isSpriteSheet()
    {
        return isSpriteSheet;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getSubSpriteAmount()
    {
        return subSpriteAmount;
    }

    public String getName(int index)
    {
        try
        {
            return names.get(index);
        }
        catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }

    public List<String> getNames()
    {
        return names;
    }
}
