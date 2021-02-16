public class APU {
    // static instance
    private static APU instance = new APU();
    // port
    public Port port;
    private String manufacturer = "<student name 01> / <student name 02>";
    private String type = "team <id>";
    private String id = "<student id 01> / <student id 02>";
    private boolean isStarted = false;
    private int rpm = 0;

    // private constructor
    private APU() {
        port = new Port();
    }

    // static method getInstance
    public static APU getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "WeatherRadar // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerStart() {
        isStarted = true;
        return true;
    }

    public int innerIncreaseRPM(int value) {
        return rpm += value;
    }

    public int innerDecreaseRPM(int value) {
        return rpm -= value;
    }

    public void innerShutdown() {
        isStarted = false;
    }

    // inner class port
    public class Port implements IAPU {
        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public boolean start() {
            return innerStart();
        }

        @Override
        public int increaseRPM(int value) {
            return innerIncreaseRPM(value);
        }

        @Override
        public int decreaseRPM(int value) {
            return innerDecreaseRPM(value);
        }

        @Override
        public void shutdown() {
            innerShutdown();
        }
    }
}