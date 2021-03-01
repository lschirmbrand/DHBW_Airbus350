public class TemperatureSensor {
    // static instance
    private static TemperatureSensor instance = new TemperatureSensor();
    // port
    public Port port;
    private String manufacturer = "2529977, 6499887";
    private String type = "team 04";
    private String id = "2529977, 6499887";


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
        return "WeatherRadar // " + manufacturer + " - " + type + " - " + id;
    }


    // inner class port
    public class Port implements ITemperatureSensor {
        public String version() {
            return innerVersion();
        }


	}
}
