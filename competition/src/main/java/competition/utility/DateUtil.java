package competition.utility;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static @NotNull LocalDateTime stringToLocalDateTime(String str, String format){
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(format));
    }
}
