package org.automation.testing.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogUtil {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String message) {
        System.out.println(
                "[" + LocalDateTime.now().format(FORMATTER) + "] " + message
        );
    }
}