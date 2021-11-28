package com.mos.game_objects.shape_generators;

import com.mos.game_objects.shapes.*;

import java.util.Random;

public abstract class ShapeGenerator
{
    protected static final int MAX_SHAPES = 7;
    protected Random random = new Random();
    
    public abstract Shape next();
    
    protected final Shape generate(int n)
    {
        switch (n) {
            case 0: {
                return new I_Shape();
            }
            case 1: {
                return new J_Shape();
            }
            case 2: {
                return new L_Shape();
            }
            case 3: {
                return new O_Shape();
            }
            case 4: {
                return new S_Shape();
            }
            case 5: {
                return new T_Shape();
            }
            case 6: {
                return new Z_Shape();
            }
            
        }
        
        return null;
    }
}
