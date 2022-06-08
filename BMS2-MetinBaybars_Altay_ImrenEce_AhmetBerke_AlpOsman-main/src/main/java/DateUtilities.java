import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

// Helper methods for handling dates. Comparison and formatting etc.
public class DateUtilities {
    //Checks two dates which is given parameters; are in same day or not.
   public static boolean isSameDay(LocalDate date1, LocalDate date2) {
       return ChronoUnit.DAYS.between(date1, date2) == 0;
   }
   //Formats the date as dd.mm.yyyy
   public static String formatDateAsDay(LocalDate date) {
       return date.getDayOfMonth() + " " + date.getMonth().name() + " " + date.getYear();
   }

   public static String formatDateAsDay(LocalDateTime date) {
        return formatDateAsDay(date.toLocalDate());
    }
    //Formats the date as hh:mm
   public static String formatDateAsTime(LocalDateTime date) {
       return date.getHour() + ":" + date.getMinute();
   }
    //Formats two dates as: dd.mm.yyy hh:mm - hh:mm
   public static String formatDatesAndTimesBetween(LocalDateTime date1, LocalDateTime date2) {
       return formatDateAsDay(date1) + " " + formatDateAsTime(date1) + " - " + formatDateAsTime(date2);
   }
}
