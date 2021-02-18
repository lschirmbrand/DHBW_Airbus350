public class LogoLight{
    // static instance
    private static LogoLight instance = new LogoLight();
    // port
    public Port port;
    private String manufacturer = "<student name 01> / <student name 02>";
    private String type = "team <id>";
    private String id = "<student id 01> / <student id 02>";
    private boolean isOn = false;

    // private constructor
    private LogoLight() {
        port = new Port();
    }

    // static method getInstance
    public static LogoLight getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "LogoLight // " + manufacturer + " - " + type + " - " + id;
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
    public class Port implements ILogoLight{
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