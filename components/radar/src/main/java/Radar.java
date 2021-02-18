public class Radar implements IRadar {
    private String manufacturer = "Manuel Truckses / Andreas Köhler";
    private String type = "Team 05";
    private String id = "9008480 / 1253402";
    private boolean isOn;

    @Override
    public String version() {
        return null;
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

    public boolean isOn() {
        return isOn;
    }

    @Override
    public boolean on() {
        isOn = true;
        return true;
    }

    @Override
    public boolean off() {
        isOn = false;
        return false;
    }

    @Override
    public boolean scan(String environment) {
        return false;
    }
}
