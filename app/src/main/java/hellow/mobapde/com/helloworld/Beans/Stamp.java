package hellow.mobapde.com.helloworld.Beans;

/**
 * Created by Patrick on 3/3/2017.
 */

public class Stamp {
    private String name;
    private String description;
    //picture
    private String date;

    public Stamp(String name, String description, String date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
