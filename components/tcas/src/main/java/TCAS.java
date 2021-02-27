@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class TCAS {

    private static final TCAS instance = new TCAS();
    private final String manufacturer = "1253402";
    public Port port = new Port();
    private boolean isOn;
    private boolean isConnected;
    private boolean isAlarm;
    private int altitude;

    private TCAS() {

    }

    public static TCAS getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "TCAS // " + manufacturer;
    }

    public boolean innerOn() {
        isOn = true;
        return true;
    }

    public boolean innerConnect(String frequency) {
        isConnected = frequency.trim().equalsIgnoreCase("astra-8");
        return isConnected;
    }

    public boolean innerScan(String environment) {
        return environment.toLowerCase().contains("bird");
    }

    public boolean innerAlarm() {
        isAlarm = true;
        return true;
    }

    public int innerDetermineAltitude(String environment) {
        altitude = environment.toLowerCase().contains("low air") ? 50 : 0;
        return altitude;
    }

    public int innerSetAltitude(int value) {
        altitude = value;
        if(altitude > 90) {
            altitude = 90;
        }
        return altitude;
    }

    public boolean innerOff() {
        isOn = false;
        return false;
    }

    public class Port implements ITCAS {

        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public boolean on() {
            return innerOn();
        }

        @Override
        public boolean connect(String frequency) {
            return innerConnect(frequency);
        }

        @Override
        public boolean scan(String environment) {
            return innerScan(environment);
        }

        @Override
        public boolean alarm() {
            return innerAlarm();
        }

        @Override
        public int determineAltitude(String environment) {
            return innerDetermineAltitude(environment);
        }

        @Override
        public int setAltitude(int value) {
            return innerSetAltitude(value);
        }

        @Override
        public boolean off() {
            return innerOff();
        }
    }
}
