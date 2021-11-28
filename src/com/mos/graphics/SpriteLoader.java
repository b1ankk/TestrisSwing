package com.mos.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


/**
 * The {@code SpriteLoader} class is a utility to read image files, wrap them in {@link Sprite} class
 * and store inside of the {@code SpriteLoader} class itself.
 */
class SpriteLoader
{
    private final Map<String, Sprite> sprites = new HashMap<>();
    
    SpriteLoader()
    {
    
    }
    
    /**
     * Loads an image from a specified {@code path}. Creates a {@link Sprite} of the loaded image,
     * saves it in an internal map and returns it.
     * Writes error messages to {@code System.err} if the file couldn't be loaded.
     *
     * @param path path to the image file to load
     * @return returns a {@link Sprite} created from a loaded image
     */
    Sprite loadSprite(String path)
    {
        File file = new File(path);
        
        BufferedImage img = null;
        Sprite sprite = null;
        try {
            if (!file.exists())
                throw new FileNotFoundException("No such file found: " + path);
            
            img = ImageIO.read(file);
            sprite = new Sprite(img, file.getName());
            
            System.out.println("Sprite loaded: " + sprite.getName());
            
            sprites.put(sprite.getName(), sprite);
        }
        catch (IOException e) {
            System.err.println("Error reading file: " + path);
            System.err.println(e.getMessage());
        }
        
        return sprite;
    }
    
    
    /**
     * Loads an image file from the {@code path} given. Splits it using the passed parameters,
     * creating multiple instances of {@link Sprite} saves them in the internal map and
     * returns them in a {@link HashMap}.
     *
     * @param path   path of file to read from
     * @param width  a width of a sub-image
     * @param height a height of a sub-image
     * @param amount amount of sub-images inside of the specified file
     * @return a {@link Map} is returned in format {@code <String imageName, Sprite sprite>}.
     */
    Map<String, Sprite> loadSpritesFromSheet(String path, int width, int height, int amount)
    {
        return loadSpritesFromSheet(path, width, height, amount, null);
    }
    
    
    /**
     * Loads an image file from the {@code path} given. Splits it using the passed parameters,
     * creating multiple instances of {@link Sprite}, saves them in the internal map and
     * returns them in a {@link HashMap}.
     *
     * @param path   path of file to read from
     * @param width  a width of a sub-image
     * @param height a height of a sub-image
     * @param amount amount of sub-images inside of the specified file
     * @param names  sub-image name parameter. A {@code List<String>} containing names for the sub-images.
     *               Names are used in order as they appear in the array. A name for a sub-image is created
     *               concatenating the {@code path} file name and the passed name. If an empty, too small
     *               {@link List} or
     *               {@code null} is passed, names are named using the {@code path} file name and orderly
     *               natural numbers.
     * @return a {@link Map} is returned in format {@code <String imageName, Sprite sprite>}.
     */
    Map<String, Sprite> loadSpritesFromSheet(String path, int width, int height, int amount, List<String> names)
    {
        Sprite spriteSheet = loadSprite(path);
        BufferedImage img = spriteSheet.getImage();
        Map<String, Sprite> spriteSheetMap = new HashMap<>();
        
        int counter = 0;
        for (int y = 0; y < img.getHeight() && amount > 0; y += height)
            for (int x = 0; x < img.getWidth() && amount > 0; x += width) {
                BufferedImage bi = img.getSubimage(x, y, width, height);
                
                String spriteName;
                try {
                    spriteName = spriteSheet.getName() + "_" + names.get(counter);
                }
                catch (IndexOutOfBoundsException | NullPointerException e) {
                    spriteName = spriteSheet.getName() + "_" + counter;
                }
                
                
                Sprite sprite = new Sprite(bi, spriteName);
                sprites.put(spriteName, sprite);
                spriteSheetMap.put(spriteName, sprite);
                amount--;
                counter++;
                
                System.out.println("SubSprite loaded: " + spriteName);
            }
        
        return spriteSheetMap;
    }
    
    
    /**
     * Returns an {@code UnmodifiableMap} of loaded {@link Sprite} instances.
     * Returns {@code null} if no sprites were loaded yet.
     *
     * @return an {@code UnmodifiableMap} of loaded {@link Sprite} instances.
     */
    Map<String, Sprite> getSprites()
    {
        return Collections.unmodifiableMap(sprites);
    }
    
}
