@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class TurbulentAirFlowSensor {

    private static final TurbulentAirFlowSensor instance = new TurbulentAirFlowSensor();
    private final String manufacturer = "1253402";
    public Port port = new Port();
    private boolean isAlarm = false;

    private TurbulentAirFlowSensor() {

    }

    public static TurbulentAirFlowSensor getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "TurbulentAirFLowSensor // " + manufacturer;
    }

    public boolean innerAlarm() {
        isAlarm = true;
        return true;
    }

    public int innerMeasure(String airFlow) {
        return airFlow.length();
    }

    public class Port implements ITurbulentAirFlowSensor {
        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public boolean alarm() {
            return innerAlarm();
        }

        @Override
        public int measure(String airFlow) {
            return innerMeasure(airFlow);
        }
    }
}
