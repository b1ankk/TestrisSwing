package com.mos.scene;

import com.mos.game_objects.Board;
import com.mos.game_objects.shape_generators.FairShapeGenerator;
import com.mos.game_objects.shape_generators.ShapeGenerator;
import com.mos.game_objects.shapes.*;
import com.mos.game_objects.shapes.Shape;
import com.mos.game_objects.shapes.module.Module;
import com.mos.graphics.SubSpriteFileProperties;
import com.mos.graphics.SpriteHolder;
import com.mos.util.GameTime;
import com.mos.util.KeyHandler;
import com.mos.util.Vector;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GameScene extends Scene
{
    private SpriteHolder spriteHolder;
    private KeyHandler keyHandler;

    public static final String BOARD = "TetrisBoard10x20";
    public static final String SQUARE = "BaseSprites";

    public static final class SQUARE_NAME
    {
        public static final String RED = "red";
        public static final String PURPLE = "purple";
        public static final String GREEN = "green";
        public static final String YELLOW = "yellow";
        public static final String ORANGE = "orange";
        public static final String BLUE = "blue";
        public static final String CYAN = "cyan";

        private SQUARE_NAME(){}

        private static List<String> getAllNames()
        {
            List<String> nameList = new ArrayList<>();
            nameList.add(RED);
            nameList.add(PURPLE);
            nameList.add(GREEN);
            nameList.add(YELLOW);
            nameList.add(ORANGE);
            nameList.add(BLUE);
            nameList.add(CYAN);

            return Collections.unmodifiableList(nameList);
        }

        private static String getFullName(String s)
        {
            return SQUARE + "_" + s;
        }
    }


    public static final int BOARD_POSITION_X = 32;
    public static final int BOARD_POSITION_Y = 32;

    private final int DROP_PAUSE_TIME = 800;
    private final int SHAPE_RESPAWN_TIME = 400;

    private Shape shape;
    private static Board board;
    private ShapeGenerator shapeGenerator = new FairShapeGenerator();


    public GameScene(KeyHandler keyHandler)
    {
        spriteHolder = new SpriteHolder();
        this.keyHandler = keyHandler;



        spriteHolder.addSpritePath(String.format("sprites/%s.png", BOARD), null);
        spriteHolder.addSpritePath(String.format("sprites/%s.png", SQUARE),
                new SubSpriteFileProperties(35,35, 7, SQUARE_NAME.getAllNames()));
        spriteHolder.loadSprites();

        I_Shape.setSprite(spriteHolder.getSprite(SQUARE_NAME.getFullName(SQUARE_NAME.CYAN)));
        J_Shape.setSprite(spriteHolder.getSprite(SQUARE_NAME.getFullName(SQUARE_NAME.BLUE)));
        L_Shape.setSprite(spriteHolder.getSprite(SQUARE_NAME.getFullName(SQUARE_NAME.YELLOW)));
        O_Shape.setSprite(spriteHolder.getSprite(SQUARE_NAME.getFullName(SQUARE_NAME.ORANGE)));
        S_Shape.setSprite(spriteHolder.getSprite(SQUARE_NAME.getFullName(SQUARE_NAME.GREEN)));
        T_Shape.setSprite(spriteHolder.getSprite(SQUARE_NAME.getFullName(SQUARE_NAME.PURPLE)));
        Z_Shape.setSprite(spriteHolder.getSprite(SQUARE_NAME.getFullName(SQUARE_NAME.RED)));

        board = new Board(BOARD_POSITION_X, BOARD_POSITION_Y, spriteHolder.getSprite(Board.getSpriteName()));

        shape = shapeGenerator.next();

//        Timer timer = new Timer(DROP_PAUSE_TIME,
//            event -> moveShape(Vector.DOWN, Shape.MODULE_SIDE_PIXEL_LENGTH));
//        timer.start();
    }

    private Timer downTimer;
    public void input()
    {
        final int offset = Shape.MODULE_SIDE_PIXEL_LENGTH;
        final int timerDelay = 50;

        if (keyHandler.up.wasPressed())
        {
            shape.move(Vector.UP, offset);
        }

        if (keyHandler.down.wasPressed())
        {
            downTimer = new Timer(timerDelay, e -> moveShape(Vector.DOWN, offset));

            downTimer.start();
        }
        else if (keyHandler.down.wasReleased())
        {
            downTimer.stop();
        }

        if (keyHandler.left.wasPressed())
        {
            moveShape(Vector.LEFT, offset);
        }

        if (keyHandler.right.wasPressed())
        {
            moveShape(Vector.RIGHT, offset);
        }

        if (keyHandler.z.wasPressed())
        {
            smartRotateL();
        }

        if (keyHandler.x.wasPressed())
        {
            smartRotateR();

        }
    }


    @Override
    public void update()
    {
        final String dropTimer = "dropTimer";
        final String respawnTimer = "respawnTimer";

        //        Physics.checkCollisions();

        GameTime.startTimer(dropTimer);
        if (GameTime.ifPassedRestart(dropTimer, DROP_PAUSE_TIME))
        {
            moveShape(Vector.DOWN, Shape.MODULE_SIDE_PIXEL_LENGTH);
            GameTime.restartTimer(dropTimer);
        }

        if (!canShapeMove(Vector.DOWN, true))
        {
            GameTime.startTimer(respawnTimer);
            if (GameTime.ifPassedRestart(respawnTimer, SHAPE_RESPAWN_TIME))
            {
                board.addShapesModules(shape);
                shape = shapeGenerator.next();
                GameTime.deleteTimer(respawnTimer);
            }
        }

        board.returnDeleteFullRows();
    }

//    private void changeShape()
//    {
//
//    }

    @Override
    public void render(Graphics2D g)
    {
        board.render(g);
        shape.render(g);
    }


    private boolean moveShape(Vector direction, int stepCount, boolean checkBorder)
    {
        if (canShapeMove(direction, checkBorder))
        {
            shape.move(direction, stepCount);
            return true;
        }
        return false;
    }
    private boolean moveShape(Vector direction, int stepCount)
    {
        return moveShape(direction, stepCount, true);
    }



    private boolean canShapeMove(Vector direction, boolean checkBorder)
    {
//        if (direction.equals(Vector.RIGHT))
//        {
//            for(Module module : shape.getModules())
//            {
//                int x = module.getBoardPosition().getX();
//
//                if (x == board.WIDTH - 1)
//                    return false;
//            }
//        }
//
//        else if (direction.equals(Vector.LEFT))
//        {
//            for(Module module : shape.getModules())
//            {
//                int x = module.getBoardPosition().getX();
//
//                if (x == 0)
//                    return false;
//            }
//        }
//
//        else if (direction.equals(Vector.DOWN))
//        {
//            for(Module module : shape.getModules())
//            {
//                int y = module.getBoardPosition().getY();
//
//                if (y == board.HEIGHT - 1)
//                    return false;
//            }
//        }

        try
        {
            for (Module module : shape.getModules())
            {
                Vector newPosition = module.getBoardPosition().add(direction);
                if (board.getBoardSheet()[newPosition.getY()][newPosition.getX()] != null)
                    return false;
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            return !checkBorder;
        }

        return true;
    }


    private void smartRotateR()
    {
        smartRotate(shape::rotateR, shape::rotateL);
    }

    private void smartRotateL()
    {
        smartRotate(shape::rotateL, shape::rotateR);
    }

    private void smartRotate(Runnable rotate, Runnable derotate)
    {
        rotate.run();
        try
        {
            for (Module module : shape.getModules())
            {
                if (board.getBoardSheet()[module.getBoardPosition().getY()][module.getBoardPosition().getX()] != null)
                {
                    derotate.run();
                    break;
                }
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            for (Iterator<Module> itr = shape.getModules().iterator(); itr.hasNext();)
            {
                Module m = itr.next();
                if (m.getBoardPosition().getX() < 0)
                {
                    if (moveShape(Vector.RIGHT, Shape.MODULE_SIDE_PIXEL_LENGTH, false))
                    {
                        itr = shape.getModules().iterator();
                    }
                    else
                    {
                        derotate.run();
                        break;
                    }
                }
                else if (m.getBoardPosition().getX() >= board.WIDTH)
                {
                    if (moveShape(Vector.LEFT, Shape.MODULE_SIDE_PIXEL_LENGTH, false))
                    {
                        itr = shape.getModules().iterator();
                    }
                    else
                    {
                        derotate.run();
                        break;
                    }
                }
            }
        }
    }


    public static Board getBoard()
    {
        return board;
    }
}
