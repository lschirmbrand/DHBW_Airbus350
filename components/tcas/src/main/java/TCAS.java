public class TCAS implements ITCAS{
    private String manufacturer;
    private String type;
    private String id;
    private boolean isOn;
    private boolean isConnected;
    private boolean isAlarm;
    private int altitude;

    public TCAS(boolean isOn, boolean isConnected, boolean isAlarm, int altitude) {
        this.manufacturer = "Manuel Truckses / Andreas Köhler";
        this.type = "Team 05";
        this.id = "9008480 / 1253402";
        this.isOn = isOn;
        this.isConnected = isConnected;
        this.isAlarm = isAlarm;
        this.altitude = altitude;
    }

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

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public boolean isAlarm() {
        return isAlarm;
    }

    public void setAlarm(boolean alarm) {
        isAlarm = alarm;
    }

    public int getAltitude() {
        return altitude;
    }

    @Override
    public String version() {
        return "Version";
    }

    @Override
    public boolean on() {
        setOn(true);
        return true;
    }

    @Override
    public boolean connect(String frequency) {
        setConnected(true);
        return false;
    }

    @Override
    public boolean scan(String environment){
        return true;
    }
    @Override
    public boolean alarm(){
        setAlarm(true);
        return true;
    }
    @Override
    public int determineAltitude(String environment){
        return 0;
    }
    @Override
    public int setAltitude(int value){
        altitude = value;
        return value;
    }
    @Override
    public boolean off(){
        setOn(false);
        return true;
    }
}
