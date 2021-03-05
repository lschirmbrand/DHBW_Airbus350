public class AirFlowSensor {
    // static instance
    private static AirFlowSensor instance = new AirFlowSensor();
    public Port port;
    private String manufacturer = "2529977, 6499887";
    private int airPressure;
    private boolean isAlarm;

    // private constructor
    private AirFlowSensor() { port = new Port(); }

    // static method getInstance
    public static AirFlowSensor getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() { return "AirFlowSensor // " + manufacturer; }

    public boolean innerAlarm(int threshold) {
        isAlarm = airPressure > threshold;
        return isAlarm;
    }

    public int innerMeasure(String airFlow) {
        airPressure = airFlow.length();
        return airPressure;
    }


    // inner class port
    public class Port implements IAirflowSensor {
        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public int measure(String airFlow) { return innerMeasure(airFlow); }

        @Override
        public boolean alarm(int threshold) { return innerAlarm(threshold); }
    }
}
