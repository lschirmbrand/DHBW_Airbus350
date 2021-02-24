public class AirConditioning {
    // static instance
    private static final AirConditioning instance = new AirConditioning();
    private final String manufacturer = "4775194 / 8864957";
    private final String type = "team 01";
    private final String id = "4775194 / 8864957";
    // port
    public Port port;
    private int temperature;
    private boolean isOn;

    // private constructor
    private AirConditioning() {
        port = new Port();
    }

    // static method getInstance
    public static AirConditioning getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "AirConditioning // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerOn() {
        return isOn = true;
    }

    public String innerClean(String airFlow) {
        return airFlow;
    }

    public int innerHeat(String airFlow, int temperature) {
        return this.temperature = temperature;
    }

    public int innerCool(String airFlow, int temperature) {
        return this.temperature = temperature;
    }

    public boolean innerOff() {
        return isOn = false;
    }


    // inner class port
    public class Port implements IAirConditioning {
        public String version() {
            return innerVersion();
        }

        public boolean on() {
            return innerOn();
        }

        public String clean(String airFlow) {
            return innerClean(airFlow);
        }

        public int heat(String airFlow, int temperature) {
            return innerHeat(airFlow, temperature);
        }

        public int cool(String airFlow, int temperature) {
            return innerCool(airFlow, temperature);
        }

        public boolean off() {
            return innerOff();
        }


    }
}
