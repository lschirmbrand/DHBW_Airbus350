public class WeatherRadar {
    // static instance
    private static final WeatherRadar instance = new WeatherRadar();
    private final String manufacturer = "<student name 01> / <student name 02>";
    private final String type = "team <id>";
    private final String id = "<student id 01> / <student id 02>";
    // port
    public Port port;
    private boolean isOn = false;

    // private constructor
    private WeatherRadar() {
        port = new Port();
    }

    // static method getInstance
    public static WeatherRadar getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "WeatherRadar // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerOn() {
        isOn = true;
        return isOn;
    }

    public boolean innerOff() {
        isOn = false;
        return isOn;
    }

    public boolean innerScan(String environment) {
        return environment.contains("bird");
    }

    // inner class port
    public class Port implements IWeatherRadar {
        public String version() {
            return innerVersion();
        }

        public boolean on() {
            return innerOn();
        }

        public boolean off() {
            return innerOff();
        }

        public boolean scan(String environment) {
            return innerScan(environment);
        }
    }
}