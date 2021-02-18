public class SatCom implements ISatCom {

    private String manufacturer;
    private String type;
    private String id;
    private boolean isConnected;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    @Override
    public String version() {
        return null;
    }

    @Override
    public boolean on() {
        return false;
    }

    @Override
    public boolean connect(String satalite, String frequency) {
        return false;
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
        return false;
    }
}
