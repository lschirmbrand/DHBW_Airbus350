package event.satcom;

public class SatComSend {
    String request;

    public SatComSend(String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "SatComSend{" +
                "request='" + request + '\'' +
                '}';
    }
}
