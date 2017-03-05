package hellow.mobapde.com.helloworld;

import java.util.ArrayList;

/**
 * Created by Patrick on 3/3/2017.
 */

public class Adventure {
    private String name;
    private String details;
    private String status;
    private ArrayList<Stop> stops;

    public Adventure() {

    }

    public Adventure(String name, String details, String status, ArrayList<Stop> stops) {
        this.name = name;
        this.details = details;
        this.status = status;
        this.stops = stops;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Stop> getStops() {
        return stops;
    }

    public void setStops(ArrayList<Stop> stops) {
        this.stops = stops;
    }
}