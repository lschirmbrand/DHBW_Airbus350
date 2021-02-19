public class GPS {

    private static GPS instance = new GPS();
    private String manufacturer = "Manuel Truckses / Andreas Köhler";
    private String type = "Team 05";
    private String id = "9008480 / 1253402";
    private boolean isOn;
    private boolean isConnected;
    public Port port = new Port();

    private GPS() {

    }

    public String innerVersion() {
        return manufacturer + "; " + type + "; " + id;
    }

    public static GPS getInstance() {
        return instance;
    }

    public boolean innerOn() {
        isOn = true;
        return true;
    }

    public boolean innerConnect(String satelite) {
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
        public boolean connect(String satelite) {
            return innerConnect(satelite);
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
