public class Battery {
    // static instance
    private static Battery instance = new Battery();
    // port
    public Port port;
    private String manufacturer = "2529977, 6499887";
    private String type = "team 04";
    private String id = "2529977, 6499887";


    // private constructor
    private Battery() {
        port = new Port();
    }

    // static method getInstance
    public static Battery getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "WeatherRadar // " + manufacturer + " - " + type + " - " + id;
    }


    // inner class port
    public class Port implements IBattery {
        public String version() {
            return innerVersion();
        }


	}
}
