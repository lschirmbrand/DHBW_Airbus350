public class SatCom {
    // static instance
    private static final SatCom instance = new SatCom();
    // port
    public Port port;
    private final String manufacturer = "9008480";
    private boolean isConnected;

    // private constructor
    private SatCom() {
        port = new Port();
    }

    // instance return
    public static SatCom getInstance() {
        return instance;
    }

    // inner Methods
    public String innerVersion() {
        return "SatCom // " + manufacturer;
    }

    public boolean innerOn() {
        return isConnected = true;
    }

    public boolean innerConnect(String satellite, String frequency) {
        return isConnected;
    }

    public void innerSend(String request) {

    }

    public String innerReceive() {
        return "";
    }

    public boolean innerOff() {
        return isConnected = false;
    }

    // inner Port
    public class Port implements ISatCom {

        public String version() {
            return innerVersion();
        }

        public boolean on() {
            return innerOn();
        }

        public boolean connect(String satellite, String frequency) {
            return innerConnect(satellite, frequency);
        }

        public void send(String request) {
            innerSend(request);
        }

        public String receive() {
            return innerReceive();
        }

        public boolean off() {
            return innerOff();
        }
    }
}

