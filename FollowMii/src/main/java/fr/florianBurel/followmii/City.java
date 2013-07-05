package fr.florianBurel.followmii;

/**
 * Created by fl0 on 05/07/13.
 */
public class City {

    private double longitude;
    private double latitude;
    private String name;

    public City(double latitude, double longitude) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        if(name == null)
            return "long : " + longitude + "; lat : " + latitude;
        else
            return name;
    }

    public City(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
