public class GPS implements IGPS {

    private String manufacturer = "Manuel Truckses / Andreas Köhler";
    private String type = "Team 05";
    private String id = "9008480 / 1253402";
    private boolean isOn;
    private boolean isConnected;

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

    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public String version() {
        return null;
    }

    @Override
    public boolean on() {
        isOn = true;
        return true;
    }

    @Override
    public boolean connect(String satelite) {
        isConnected = true;
        return true;
    }

    @Override
    public void send(String request) {

    }

    @Override
    public String receive() {
        return null;
    }

    @Override
    public boolean off() {
        isOn = false;
        return false;
    }
}
