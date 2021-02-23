@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class GPS {

    private static final GPS instance = new GPS();
    private final String manufacturer = "Andreas Köhler / Manuel Truckses";
    private final String type = "Team 05";
    private final String id = "1253402 / 9008480";
    public Port port = new Port();
    private boolean isOn;
    private boolean isConnected;

    private GPS() {
    }

    public static GPS getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "GPS // " + manufacturer + "; " + type + "; " + id;
    }

    public boolean innerOn() {
        isOn = true;
        return true;
    }

    public boolean innerConnect(String satellite) {
        isConnected = true;
        return true;
    }

    public void innerSend(String request) {

    }

    public String innerReceive() {
        return "GPS Receive";
    }

    public boolean innerOff() {
        isOn = false;
        return false;
    }

    public class Port implements IGPS {
        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public boolean on() {
            return innerOn();
        }

        @Override
        public boolean connect(String satellite) {
            return innerConnect(satellite);
        }

        @Override
        public void send(String request) {
            innerSend(request);
        }

        @Override
        public String receive() {
            return innerReceive();
        }

        @Override
        public boolean off() {
            return innerOff();
        }
    }
}
