public class Radar {

    private static Radar instance = new Radar();
    private String manufacturer = "Manuel Truckses / Andreas Köhler";
    private String type = "Team 05";
    private String id = "9008480 / 1253402";
    private boolean isOn;
    public Port port = new Port();

    private Radar() {

    }

    public String innerVersion() {
        return manufacturer + "; " + type + "; " + id;
    }

    public static Radar getInstance() {
        return instance;
    }

    public boolean innerOn() {
        isOn = true;
        return true;
    }

    public boolean innerOff() {
        isOn = false;
        return false;
    }

    public boolean innerScan(String environment) {
        return true;
    }

    public class Port implements IRadar {
        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public boolean on() {
            return innerOn();
        }

        @Override
        public boolean off() {
            return innerOff();
        }

        @Override
        public boolean scan(String environment) {
            return innerScan(environment);
        }
    }
}
