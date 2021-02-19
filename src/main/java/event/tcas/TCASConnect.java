package event.tcas;

public class TCASConnect {
    private final String frequency;

    public TCASConnect(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "TCASConnect{" +
                "frequency='" + frequency + '\'' +
                '}';
    }
}
