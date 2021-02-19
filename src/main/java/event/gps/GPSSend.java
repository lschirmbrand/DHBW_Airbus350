package event.gps;

public class GPSSend {
    private final String request;

    public GPSSend(String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "GPSSend{" +
                "request='" + request + '\'' +
                '}';
    }
}
