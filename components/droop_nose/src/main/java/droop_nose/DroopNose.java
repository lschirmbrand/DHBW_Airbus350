package droop_nose;

public class DroopNose implements IDroopNose{

    private String manufacturer = "Manuel Truckses / Andreas Köhler";
    private String type = "Team 05";
    private String id = "9008480 / 1253402";
    private int degree;

    public DroopNose(int degree) {
        this.degree = degree;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    @Override
    public String version() {
        return null;
    }

    @Override
    public int neutral() {
        return 0;
    }

    @Override
    public int fullDown() {
        return 0;
    }

    @Override
    public int down(int degree) {
        return 0;
    }

    @Override
    public int up(int degree) {
        return 0;
    }
}
