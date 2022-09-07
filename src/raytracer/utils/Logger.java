package raytracer.utils;

/**
 * Wrapper for logging
 */
public class Logger
{
    private static int logLevel = 0;

    /**
     * Prints log message if the set logLevel is higher than that of the message.
     *
     * @param msg the log message
     * @param level the priority of log for given message
     */
    public static void log(String msg, int level)
    {
        if (level <= Logger.logLevel)
        {
            System.out.println(msg);
        }
    }

    /**
     * Sets our current log level.
     *
     * @param level the new level value
     */
    public static void setLogLevel(int level)
    {
        Logger.logLevel = level;
    }
}
