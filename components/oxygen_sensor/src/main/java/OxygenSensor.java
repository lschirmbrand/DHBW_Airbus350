public class OxygenSensor {
    //static instance
    private static OxygenSensor instance = new OxygenSensor();

    // port
    public Port port;
    private String manufacturer = "5736465";
    private boolean isAlarm = false;

    //private constructor
    private OxygenSensor() {
        this.port = new Port();
    }

    //static method getInstance
    public static OxygenSensor getInstance() {
        return instance;
    }

    //inner methods
    public String innerVersion() {
        return "OxygenSensor // " + this.manufacturer;
    }

    public int innerMeasure(String airFlow) {
        return 0;
    }

    public boolean innerAlarm() {
        return (this.isAlarm = true);
    }

    //inner class port
    public class Port implements IOxygenSensor {

        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public int measure(String airFlow) {
            return innerMeasure(airFlow);
        }

        @Override
        public boolean alarm() {
            return innerAlarm();
        }
    }
}
