public class LandingLight{
    // static instance
    private static LandingLightinstance = new LandingLight();
    // port
    public Port port;
    private String manufacturer = "<student name 01> / <student name 02>";
    private String type = "team <id>";
    private String id = "<student id 01> / <student id 02>";
    private boolean isOn = false;

    // private constructor
    private LandingLight() {
        port = new Port();
    }

    // static method getInstance
    public static WeatherRadar getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "LandingLight // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerOn() {
        isOn = true;
        return isOn;
    }

    public boolean innerOff() {
        isOn = false;
        return isOn;
    }

    // inner class port
    public class Port implements ILandingLight{
        public String version() {
            return innerVersion();
        }

        public boolean on() {
            return innerOn();
        }

        public boolean off() {
            return innerOff();
        }
    }
}