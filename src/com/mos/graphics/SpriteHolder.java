package com.mos.graphics;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;


public class SpriteHolder
{
    private SpriteLoader spriteLoader;

    private Map<String, Sprite> spriteMap;

    private Map <String, SubSpriteFileProperties> paths = new HashMap<>();


    private boolean spritesLoaded = false;

    public SpriteHolder()
    {
        spriteLoader = new SpriteLoader();
//        Sprite sprite = spriteLoader.loadSprite("sprites/TetrisBoard10x20.png");
//        spriteMap = spriteLoader.loadSpritesFromSheet("sprites/BaseSprites.png", 35,35, 7);
//        spriteMap.put(sprite.getName(), sprite);

    }

    public void addSpritePath(String path, SubSpriteFileProperties sfp)
    {
        paths.put(path, sfp);
    }

    public void loadSprites()
    {
        paths.forEach((path, sfp) ->
        {
            if (sfp == null)
            {
                spriteLoader.loadSprite(path);
            }
            else
            {
                spriteLoader.loadSpritesFromSheet(path,
                        sfp.getWidth(), sfp.getHeight(), sfp.getSubSpriteAmount(), sfp.getNames());
            }
        });

        spriteMap = spriteLoader.getSprites();

        spritesLoaded = true;
    }

    public Sprite getSprite(String name)
    {
        try
        {
            Sprite result = spriteMap.get(name);

            if (result == null)
                throw new NullPointerException("Could't find sprite in memory: " + name);

            return result;
        }
        catch(NullPointerException e)
        {
            if (!spritesLoaded)
                throw new NullPointerException("Sprites have not been loaded!");
            else
            {
                System.err.println("Error occurred loading sprite named: " + name);
                System.err.println(e.getMessage());
                return new Sprite(new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB), "none");
            }
        }
    }

    public int getSpriteListSize()
    {
        return spriteMap.size();
    }


}
