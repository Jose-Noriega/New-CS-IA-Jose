import java.util.*;
//import java.time.*;
import java.text.*;

public class Formatter {
    //instance field
    Date today = new Date();
    SimpleDateFormat hourFormatter, minuteFormatter, dateFormatter, fileDateFormatter;
    double lunchStart, lunchEnd;

    //constructor
    Formatter() {
        hourFormatter = new SimpleDateFormat("HH");
        minuteFormatter = new SimpleDateFormat("mm");
        dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        fileDateFormatter = new SimpleDateFormat("MM-dd-yyyy");
    }

    //method to format current time
    double formatCurrentTime() {
        double hour = Double.parseDouble(hourFormatter.format(today));
        double minutes = Double.parseDouble(minuteFormatter.format(today));
        double time = hour + (minutes/60);
        return time;
    }

    //method to format current date
    String formatCurrentDay() {
        return dateFormatter.format(today);
    }

    //method to format date for file names
    String formatDate() {
        return fileDateFormatter.format(today);
    }

    double formatTime(String time, boolean isPM) {
        double formattedTime;
        double timeHours = Double.parseDouble(time.substring(0, time.indexOf(":")));
        if (isPM) {
            timeHours += 12;
        } else{}
        double timeMinutes = Double.parseDouble(time.substring(time.indexOf(":") + 1));
        formattedTime = timeHours + (timeMinutes/60);
        return formattedTime;
    }
}