public class AirflowSensor {
    // static instance
    private static AirflowSensor instance = new AirflowSensor();
    // port
    public Port port;
    private String manufacturer = "2529977, 6499887";
    private String type = "team 04";
    private String id = "2529977, 6499887";


    // private constructor
    private AirflowSensor() {
        port = new Port();
    }

    // static method getInstance
    public static AirflowSensor getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "WeatherRadar // " + manufacturer + " - " + type + " - " + id;
    }


    // inner class port
    public class Port implements IAirflowSensor {
        public String version() {
            return innerVersion();
        }


	}
}
