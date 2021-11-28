package com.mos.util;

import java.util.HashMap;

public class GameTime
{
    private static double deltaTime;
    private static double nanoDeltaTime;
    
    private static final HashMap<String, Long> nanoTimers = new HashMap<>();
    private static final HashMap<String, Long> timers = new HashMap<>();
    
    
    public static void setDeltaTime(double nanoTimeDelta)
    {
        deltaTime = nanoTimeDelta / 1_000_000;
        nanoDeltaTime = nanoTimeDelta;
    }
    
    public static double getDeltaTime()
    {
        return deltaTime;
    }
    
    public static double getNanoDeltaTime()
    {
        return nanoDeltaTime;
    }
    
    public static void startTimer(String name)
    {
        timers.putIfAbsent(name, System.currentTimeMillis());
        
    }
    
    public static long getTimerValue(String name)
    {
        return System.currentTimeMillis() - timers.get(name);
    }
    
    public static void deleteTimer(String name)
    {
        timers.remove(name);
    }
    
    public static void restartTimer(String name)
    {
        timers.replace(name, System.currentTimeMillis());
    }
    
    public static boolean ifPassedRestart(String name, long value)
    {
        long dif = System.currentTimeMillis() - timers.get(name);
        long sc = dif - value;
        if (sc > 0)
        {
            timers.replace(name, System.currentTimeMillis() - sc);
            return true;
        }
        return false;
    }
    
    public static void startTimerNano(String name)
    {
        nanoTimers.putIfAbsent(name, System.nanoTime());
    }
    
    public static long getTimerValueNano(String name)
    {
        return System.nanoTime() - nanoTimers.get(name);
    }
    
    public static void deleteTimerNano(String name)
    {
        nanoTimers.remove(name);
    }
    
    public static void restartTimerNano(String name)
    {
        nanoTimers.replace(name, System.nanoTime());
    }
}
