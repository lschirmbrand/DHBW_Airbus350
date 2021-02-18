public class SatCom {
    // static instance
    private static SatCom instance = new SatCom();
    // port
    public Port port;
    private String manufacturer = "Manuel Truckses / Andreas Köhler";
    private String type = "Team 05";
    private String id = "9008480 / 1253402";
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
        return "SatCom // " + manufacturer + " - " + type + " - " + id;
    }
    public boolean innerOn() {
        return isConnected = true;
    }

    public boolean innerConnect(String satalite, String frequency) {
        return isConnected;
    }

    public void innerSend(String request) {

    }

    public String innerReceive() {
        return null;
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

        public boolean connect(String satalite, String frequency) {
            return innerConnect(satalite,frequency);
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

