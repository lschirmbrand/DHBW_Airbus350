public class FireDetector {
    //static instance
    private static FireDetector instance = new FireDetector();

    // port
    public Port port;
    private String manufacturer = "3186523";
    private boolean isAlarm = false;

    //private constructor
    private FireDetector() {
        this.port = new Port();
    }

    //static method getInstance
    public static FireDetector getInstance() {
        return instance;
    }
    //inner methods
    public String innerVersion() {
        return "FireDetector // " + this.manufacturer;
    }

    public boolean innerScan(String air) {
        return true;
    }

    public boolean innerAlarm() {
        return (this.isAlarm = true);
    }

    //inner class port
    public class Port implements IFireDetector {

        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public boolean scan(String air) {
            return innerScan(air);
        }

        @Override
        public boolean alarm() {
            return innerAlarm();
        }
    }
}
