package com.mos;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window
{
    private int width;
    private int height;
    private final String title;

    private JFrame frame;
//    private JPanel windowPanel;
    private Canvas canvas;
    private BufferStrategy bufferStrategy;

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
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        frame.setUndecorated(true);
        frame.setVisible(true);



//        windowPanel = new JPanel();
//        windowPanel.setPreferredSize(new Dimension(width, height));
//        windowPanel.setMinimumSize(new Dimension(width, height));
//        windowPanel.setMaximumSize(new Dimension(width, height));
//        windowPanel.setFocusable(true);
//        windowPanel.requestFocus();

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setFocusable(true);
        canvas.requestFocus();

        frame.add(canvas);
        frame.pack();

//        frame.createBufferStrategy(2);
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();


        //frame.setLayout(new BorderLayout());
        //System.out.println(frame.getInsets());

    }

//    public JPanel getWindowPanel()
//    {
//        return windowPanel;
//    }


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
