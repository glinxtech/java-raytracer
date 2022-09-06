package utils;

/**
 * Wrapper for logging
 */
public class Logger
{
    private static int logLevel = 0;

    // If the set logLevel is equal or higher to the level used when logging, it will print
    public static void log(String msg, int level)
    {
        if (level <= Logger.logLevel)
        {
            System.out.println(msg);
        }
    }

    public static void setLogLevel(int level)
    {
        Logger.logLevel = level;
    }
}
