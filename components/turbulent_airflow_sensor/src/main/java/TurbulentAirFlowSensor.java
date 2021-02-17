public class TurbulentAirFlowSensor implements ITurbulentAirFlowSensor {

    private String manufacturer = "Manuel Truckses / Andreas Köhler";
    private String type = "Team 05";
    private String id = "9008480 / 1253402";
    private boolean isAlarm = false;

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

    public boolean isAlarm() {
        return isAlarm;
    }

    public void setAlarm(boolean alarm) {
        isAlarm = alarm;
    }

    @Override
    public String version() {
        return null;
    }

    @Override
    public boolean alarm() {
        setAlarm(true);
        return true;
    }

    @Override
    public int measure(String airFlow){
        return 0;
    }
}
