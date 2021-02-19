public class Radar {

    private static final Radar instance = new Radar();
    private final String manufacturer = "Manuel Truckses / Andreas Köhler";
    private final String type = "Team 05";
    private final String id = "9008480 / 1253402";
    public Port port = new Port();
    private boolean isOn;

    private Radar() {

    }

    public static Radar getInstance() {
        return instance;
    }

    public String innerVersion() {
        return manufacturer + "; " + type + "; " + id;
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
