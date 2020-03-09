package com.mos.graphics;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class Sprite
{
    private final BufferedImage image;
    private final int width;
    private final int height;
    private final String name;

//    public Sprite()
//    {
//
//    }

    public Sprite(BufferedImage image, String name)
    {
        this.image = image;
        int dotIndex = name.lastIndexOf('.');
        this.name = name.substring(0, dotIndex > 0 ? dotIndex : name.length());
        width = image.getWidth();
        height = image.getHeight();
    }

    public BufferedImage getImage()
    {
        return image;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Sprite)) return false;
        Sprite sprite = (Sprite) o;
        return width == sprite.width &&
                height == sprite.height &&
                Objects.equals(image, sprite.image);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(image, width, height);
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
