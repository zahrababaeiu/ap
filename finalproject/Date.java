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

}

