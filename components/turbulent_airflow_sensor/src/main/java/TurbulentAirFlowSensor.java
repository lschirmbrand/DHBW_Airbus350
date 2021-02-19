public class TurbulentAirFlowSensor {

    private static TurbulentAirFlowSensor instance = new TurbulentAirFlowSensor();
    private String manufacturer = "Manuel Truckses / Andreas Köhler";
    private String type = "Team 05";
    private String id = "9008480 / 1253402";
    private boolean isAlarm = false;
    public Port port = new Port();

    private TurbulentAirFlowSensor() {

    }

    public String innerVersion() {
        return manufacturer + "; " + type + "; " + id;
    }

    public static TurbulentAirFlowSensor getInstance() {
        return instance;
    }

    public boolean innerAlarm() {
        isAlarm = true;
        return true;
    }

    public int innerMeasure(String airFlow) {
        return 0;
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
