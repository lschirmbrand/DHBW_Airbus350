public enum Radar {
    INSTANCE;

    public Port port = new Port();
    private final String manufacturer = "Manuel Truckses / Andreas Köhler";
    private final String type = "Team 05";
    private final String id = "9008480 / 1253402";
    private boolean isOn;

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
