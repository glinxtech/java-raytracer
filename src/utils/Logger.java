package utils;


public class Logger
{
    private static int logLevel = 0;

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
