package com.mos;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window
{
    private final int width;
    private final int height;
    private final String title;
    
    private final JFrame frame;
    private final Canvas canvas;
    private final BufferStrategy bufferStrategy;
    
    
    public Window(int width, int height, String title)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setTitle(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setFocusable(true);
        canvas.requestFocus();
        
        frame.add(canvas);
        frame.pack();
        
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
    }
    
    public Canvas getCanvas()
    {
        return canvas;
    }
    
    public BufferStrategy getBufferStrategy()
    {
        return bufferStrategy;
    }
    
    public JFrame getFrame()
    {
        return frame;
    }
}
