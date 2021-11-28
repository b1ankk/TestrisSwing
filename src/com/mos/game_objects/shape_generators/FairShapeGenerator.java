package com.mos.game_objects.shape_generators;

import com.mos.game_objects.shapes.Shape;

import java.util.LinkedList;

public class FairShapeGenerator extends ShapeGenerator {
    private final LinkedList<Integer> numbers = new LinkedList<>();
    
    private void initializeList()
    {
        for (int i = 0; i < MAX_SHAPES; i++)
            numbers.add(i);
    }
    
    public Shape next()
    {
        if (numbers.isEmpty())
            initializeList();
        
        int seed = random.nextInt(numbers.size());
        int pick = numbers.remove(seed);
        
        return super.generate(pick);
    }
}
