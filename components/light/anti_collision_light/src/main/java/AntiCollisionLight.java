public class AntiCollisionLight {
    // static instance
    private static AntiCollisionLight instance = new AntiCollisionLight();
    // port
    public Port port;
    private String manufacturer = "<3106335> / <4669114>";
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
        return "AntiCollisionLight // " + manufacturer;
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
