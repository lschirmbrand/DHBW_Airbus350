import java.util.Locale;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class GPS {

    private static final GPS instance = new GPS();
    private final String manufacturer = "1253402";
    public Port port = new Port();
    private boolean isOn;
    private boolean isConnected;

    private GPS() {
    }

    public static GPS getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "GPS // " + manufacturer;
    }

    public boolean innerOn() {
        isOn = true;
        return true;
    }

    public boolean innerConnect(String satellite) {
        isConnected = satellite.toLowerCase().trim().equals("astra-8");
        return isConnected;
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
