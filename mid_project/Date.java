package ap.mid_project;

public class Date {
    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setMonth(int month) {
        if(month >= 1 && month <= 12){
            this.month = month;
        }
    }

    public int getMonth() {
        return month;
    }

    public void setDay(int day) {
       if(day >= 1 && day <= 31){
           this.day = day;
       }
    }

    public int getDay() {
        return day;
    }
    @Override
    public String toString() {
        return year + "/" + month + "/" + day;
    }
}


