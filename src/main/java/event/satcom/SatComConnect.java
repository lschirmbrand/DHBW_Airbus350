package event.satcom;

public class SatComConnect {
    String satalite;
    String frequency;

    public SatComConnect(String satalite, String frequency) {
        this.satalite = satalite;
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "SatComConnect{" +
                "satalite='" + satalite + '\'' +
                ", frequency='" + frequency + '\'' +
                '}';
    }
}
