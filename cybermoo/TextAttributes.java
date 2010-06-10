package cybermoo;

/**
 * Stores static strings representing the ansi escape
 * sequences used by terminal applications to represent
 * font effects such as colour and bolding
 * @author Shane
 */

public class TextAttributes {
    public static String ALL_ATTRIBUTES_OFF = "\u001B[0m";
    public static String BOLD = "\u001B[1m";
    public static String UNDERSCORE = "\u001B[4m";

    public static String FOREGROUND_BLACK = "\u001B[30m";
    public static String FOREGROUND_RED = "\u001B[31m";
    public static String FOREGROUND_GREEN = "\u001B[32m";
    public static String FOREGROUND_YELLOW = "\u001B[33m";
    public static String FOREGROUND_BLUE = "\u001B[34m";
    public static String FOREGROUND_MAGENTA = "\u001B[35m";
    public static String FOREGROUND_CYAN = "\u001B[36m";
    public static String FOREGROUND_WHITE = "\u001B[37m";

    public static String BACKGROUND_BLACK = "\u001B[40m";
    public static String BACKGROUND_RED = "\u001B[41m";
    public static String BACKGROUND_GREEN = "\u001B[42m";
    public static String BACKGROUND_YELLOW = "\u001B[43m";
    public static String BACKGROUND_BLUE = "\u001B[44m";
    public static String BACKGROUND_MAGENTA = "\u001B[45m";
    public static String BACKGROUND_CYAN = "\u001B[46m";
    public static String BACKGROUND_WHITE = "\u001B[47m";
}
