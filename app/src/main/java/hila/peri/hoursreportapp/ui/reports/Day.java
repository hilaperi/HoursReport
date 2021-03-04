package hila.peri.hoursreportapp.ui.reports;

import com.google.android.gms.maps.model.LatLng;

public class Day {

    private String day;
    private String sTypeOfDay;
    private double lat;
    private double lng;

    public Day() {
    }

    public Day(String day, String sTypeOfDay, double lat, double lng) {
        this.day = day;
        this.sTypeOfDay = sTypeOfDay;
        this.lat = lat;
        this.lng = lng;
    }

    public String getDay() {
        return day;
    }

    public String getsTypeOfDay() {
        return sTypeOfDay;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setsTypeOfDay(String sTypeOfDay) {
        this.sTypeOfDay = sTypeOfDay;
    }

}
