public interface IGPS {
    public String version();
    public boolean on();
    public boolean connect(String satelite);
    public void send(String request);
    public String receive();
    public boolean off();
}
