//@author Andy Li Henry Lin
package adafnwa;
import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public Date(String date) {
        int index = date.indexOf("/");
        int lastIndex = date.lastIndexOf("/");
        this.month = Integer.parseInt(date.substring(0, index));
        this.day = Integer.parseInt(date.substring(index + 1, lastIndex));
        this.year = Integer.parseInt(date.substring(lastIndex + 1, lastIndex + 5));
    } //take “mm/dd/yyyy” and create a Date object
    public Date() {
        Calendar calendar = Calendar.getInstance();
        this.month = calendar.get(Calendar.MONTH);
        this.day = calendar.get(Calendar.DATE);
        this.year = calendar.get(Calendar.YEAR);
    } //create an object with today’s date (see Calendar class)

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int THE_EIGHTYS = 1980;

    public boolean isValid() {
        if (this.year < THE_EIGHTYS){
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        if (this.month > (Calendar.DECEMBER)){
            return false;
        }
        if (this.day > 31 && (this.month == Calendar.JANUARY || this.month == Calendar.MARCH
                    || this.month == Calendar.MAY || this.month == Calendar.JULY || this.month == Calendar.AUGUST
                    || this.month == Calendar.OCTOBER || this.month == Calendar.DECEMBER )){
            return false;
        }
        if (this.day > 30 && (this.month == Calendar.APRIL || this.month == Calendar.JUNE
                || this.month == Calendar.SEPTEMBER || this.month == Calendar.NOVEMBER)){
            return false;
        }
        if (this.day > 29 && this.month == Calendar.FEBRUARY){
            return false;
        }
        if (this.day == 29 && this.month == Calendar.FEBRUARY){
            if (this.year % QUADRENNIAL != 0 || this.year % CENTENNIAL != 0 || this.year % QUATERCENTENNIAL != 0){
                return false;
            }
        }
        if (this.year > calendar.get(Calendar.YEAR)){
            return false;
        }
        else if (this.year == calendar.get(Calendar.YEAR) && this.month > calendar.get(Calendar.MONTH)){
            return false;
        }
        else return this.year != calendar.get(Calendar.YEAR) || this.month != calendar.get(Calendar.MONTH)
                    || this.day <= calendar.get(Calendar.DATE);
    }

    @Override
    public int compareTo(Date date) {
        if (this.year > date.year){
            return 1;
        }
        else if (this.year < date.year){
            return -1;
        }
        else{
            if (this.month > date.month){
                return 1;
            }
            else if (this.month < date.month){
                return -1;
            }
            else{
                if (this.day > date.day){
                    return 1;
                }
                else if (this.day < date.day) {
                    return -1;
                }
            }
        }
        return 0;
    }

    public String toString(){
        String date;
        date = this.month + "/" + this.day + "/" + this.year;
        return date;
    }
}
