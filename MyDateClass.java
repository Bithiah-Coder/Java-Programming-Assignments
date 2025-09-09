import java.util.GregorianCalendar;

public class MyDate {
    private int year, month, day;

    // Default constructor: initializes to the current date
    public MyDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        this.year = calendar.get(GregorianCalendar.YEAR);
        this.month = calendar.get(GregorianCalendar.MONTH);
        this.day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    // Constructor: initializes based on milliseconds since Jan 1, 1970
    public MyDate(long elapsedTimeMillis) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(elapsedTimeMillis);
        this.year = calendar.get(GregorianCalendar.YEAR);
        this.month = calendar.get(GregorianCalendar.MONTH);
        this.day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    // Constructor: initializes with explicit year, month, and day
    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Getters for year, month, and day
    public int getYear() { return year; }
    public int getMonth() { return month; }
    public int getDay() { return day; }

    // Update date using elapsed time in milliseconds
    public void setDate(long elapsedTimeMillis) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(elapsedTimeMillis);
        this.year = calendar.get(GregorianCalendar.YEAR);
        this.month = calendar.get(GregorianCalendar.MONTH);
        this.day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    // Display the date in a readable format
    public void showDate() {
        System.out.println("Year: " + year + ", Month: " + (month + 1) + ", Day: " + day);
    }
}
