public class DeicingSystem {
    // static instance
    private static DeicingSystem instance = new DeicingSystem();
    // port
    public Port port;
    private String manufacturer = "2529977, 6499887";
    private String type = "team 04";
    private String id = "2529977, 6499887";


    // private constructor
    private DeicingSystem() {
        port = new Port();
    }

    // static method getInstance
    public static DeicingSystem getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "WeatherRadar // " + manufacturer + " - " + type + " - " + id;
    }


    // inner class port
    public class Port implements IDeicingSystem {
        public String version() {
            return innerVersion();
        }


	}
}
