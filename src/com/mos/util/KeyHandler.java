package com.mos.util;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

public class KeyHandler implements KeyListener
{
    private Map<Integer, Key> keys = new HashMap<>();


    public static class Key
    {
        private enum IdleState
        {
            UP, DOWN
        }

        private enum ActiveState
        {
            PRESSED, RELEASED, NONE
        }

//        private static final boolean STATE_DOWN = true;
//        private static final boolean STATE_UP = false;
//
//        private boolean pressed = false;
//        private boolean released = false;
//        private boolean state = STATE_UP;

        private IdleState idleState = IdleState.UP;
        private ActiveState activeState = ActiveState.NONE;

        private final int keyCode;

        private Key(int keyCode)
        {
            this.keyCode = keyCode;
        }

        public boolean wasPressed()
        {
            return activeState == ActiveState.PRESSED;
        }

        public boolean wasReleased()
        {
            return activeState == ActiveState.RELEASED;
        }

        public boolean isDown()
        {
            return idleState == IdleState.DOWN;
        }

        public boolean isUp()
        {
            return idleState == IdleState.UP;
        }

        private synchronized IdleState getIdleState()
        {
            return idleState;
        }

        private synchronized ActiveState getActiveState()
        {
            return activeState;
        }

        private synchronized void setIdleState(IdleState idleState)
        {
            this.idleState = idleState;
        }

        private synchronized void setActiveState(ActiveState activeState)
        {
            this.activeState = activeState;
        }

        public int getKeyCode()
        {
            return keyCode;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key key = (Key) o;
            return keyCode == key.keyCode;
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(keyCode);
        }
    }


    public Key up = new Key(KeyEvent.VK_UP);
    public Key down = new Key(KeyEvent.VK_DOWN);
    public Key left = new Key(KeyEvent.VK_LEFT);
    public Key right = new Key(KeyEvent.VK_RIGHT);
    public Key z = new Key(KeyEvent.VK_Z);
    public Key x = new Key(KeyEvent.VK_X);


    private List<Key> keysToEndState = new LinkedList<>();

    public KeyHandler(Canvas gamePanel)
    {
        gamePanel.addKeyListener(this);

//        Thread activeStateEnder = new Thread()
//        {
//            @Override
//            public void run()
//            {
//                while (true)
//                {
//                    while (!keysToEndState.isEmpty())
//                    {
//                        keysToEndState.remove(0).setActiveState(Key.ActiveState.NONE);
//                    }
//                    try
//                    {
//                        Thread.sleep(1);
//                    }
//                    catch (InterruptedException e)
//                    {
//                        System.err.println(e.getMessage());
//                    }
//
//                }
//            }
//        };

//        activeStateEnder.start();

        keys.put(up.getKeyCode(), up);
        keys.put(down.getKeyCode(), down);
        keys.put(left.getKeyCode(), left);
        keys.put(right.getKeyCode(), right);
        keys.put(z.getKeyCode(), z);
        keys.put(x.getKeyCode(), x);
    }


    @Override
    public void keyPressed(KeyEvent e)
    {
//        long timer = System.nanoTime();
        Key key = keys.get(e.getKeyCode());
        if (key != null)
        {
            if (key.getIdleState() == Key.IdleState.UP)
            {
                key.setActiveState(Key.ActiveState.PRESSED);
                keysToEndState.add(key);
            }

            if (key.getIdleState() != Key.IdleState.DOWN)
                key.setIdleState(Key.IdleState.DOWN);
        }
//        System.out.println((System.nanoTime() - timer) );
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        Key key = keys.get(e.getKeyCode());
        if (key != null)
        {
            if (key.getIdleState() == Key.IdleState.DOWN)
            {
                key.setActiveState(Key.ActiveState.RELEASED);
                keysToEndState.add(key);
            }

            if (key.getIdleState() != Key.IdleState.UP)
                key.setIdleState(Key.IdleState.UP);
        }
    }



    @Override
    public void keyTyped(KeyEvent e)
    {
        //NOTHING SO FAR
    }


    public void clearActiveStates()
    {
        while (!keysToEndState.isEmpty())
            {
                keysToEndState.remove(0).setActiveState(Key.ActiveState.NONE);
            }
    }

}
