package finalproject;

public class Date {
    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;

    }

    @Override
    public String toString() {
        return year + "/" + month + "/" + day;
    }

    public boolean equals(Date other) {
        return this.year == other.year &&
                this.month == other.month &&
                this.day == other.day;
    }

    public boolean isYesterdayOf(Date other) {

        if (this.year == other.year && this.month == other.month) {
            return this.day == other.day - 1;
        }
        return false;
    }

    public boolean late(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        if (date1.getYear() > date2.getYear()) {
            return true;
        } else if (date1.getYear() < date2.getYear()) {
            return false;
        }

        if (date1.getMonth() > date2.getMonth()) {
            return true;
        } else if (date1.getMonth() < date2.getMonth()) {
            return false;
        }

        return date1.getDay() > date2.getDay();
    }

    public int average(Date date) {
        int days1 = this.year * 365 + this.month * 30 + this.day;
        int days2 = date.year * 365 + date.month * 30 + date.day;
        return Math.abs(days2 - days1);
    }

}

