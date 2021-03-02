public class TemperatureSensor {
    // static instance
    private static TemperatureSensor instance = new TemperatureSensor();
    // port
    public Port port;
    private String manufacturer = "2529977, 6499887";
    private int temperature;
    private boolean isAlarm = false;

    // private constructor
    private TemperatureSensor() {
        port = new Port();
    }

    // static method getInstance
    public static TemperatureSensor getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "TemperatureSensor // " + manufacturer;
    }

    public int innnerMeasure() { return temperature; }

    public boolean innerAlarm(int threshold) {
        if (threshold > temperature) {
            isAlarm = true;
            return true;
        } else {
            isAlarm = false;
            return false;
        }
    }

    // inner class port
    public class Port implements ITemperatureSensor {
        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public int measure() { return innnerMeasure(); }

        @Override
        public boolean alarm(int threshold) { return innerAlarm(threshold); }
    }
}
