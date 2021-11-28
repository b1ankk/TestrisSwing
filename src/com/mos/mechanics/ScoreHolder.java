package com.mos.mechanics;

import com.mos.graphics.Renderable;

import java.awt.*;

public class ScoreHolder implements Renderable
{
    private int score = 0;
    private final int x;
    private final int y;
    
    public ScoreHolder(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void add(int n)
    {
        score += n;
    }
    
    public String toString()
    {
        return Integer.toString(score);
    }
    
    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
        g.setFont(font);
        g.drawString(toString(), x, y);
    }
}
