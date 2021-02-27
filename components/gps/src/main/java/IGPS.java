@SuppressWarnings({"unused", "SpellCheckingInspection"})
public interface IGPS {
    String version();

    boolean on();

    boolean connect(String satelite);

    void send(String request);

    String receive();

    boolean off();
}
