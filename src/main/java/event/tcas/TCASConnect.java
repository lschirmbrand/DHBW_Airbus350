package event.tcas;

public class TCASConnect {
    private String frequency;
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
