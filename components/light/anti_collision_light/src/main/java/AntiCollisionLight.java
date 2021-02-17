public class AntiCollisionLight {
    // static instance
    private static AntiCollisionLight instance = new AntiCollisionLight();
    // port
    public Port port;
    private String manufacturer = "<student name 01> / <student name 02>";
    private String type = "team <id>";
    private String id = "<student id 01> / <student id 02>";
    private boolean isOn = false;

    // private constructor
    private AntiCollisionLight() {
        port = new Port();
    }

    // static method getInstance
    public static AntiCollisionLight getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "AntiCollisionLight // " + manufacturer + " - " + type + " - " + id;
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
    public class Port implements IAntiCollisionLight {
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
