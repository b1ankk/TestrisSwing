package com.mos.game_objects.shape_generators;

import com.mos.game_objects.shapes.Shape;

public class RandomShapeGenerator extends ShapeGenerator
{
    public Shape next()
    {
        int pick = random.nextInt(MAX_SHAPES);
        return generate(pick);
    }
}
