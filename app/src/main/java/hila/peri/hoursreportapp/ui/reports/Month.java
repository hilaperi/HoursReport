package hila.peri.hoursreportapp.ui.reports;

import java.io.Serializable;
import java.util.ArrayList;

public class Month implements Serializable {

    private ArrayList<Day> days = new ArrayList<>();

    public Month() {
    }

    public Month(ArrayList<Day> days) {
        this.days = days;
    }

    public ArrayList<Day> getDays() {
        return days;
    }

    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }

    public void addDay(Day day){
        this.days.add(day);
    }

    public Day getDay(int i){
        return days.get(i);
    }
}
