public interface ISatCom {
    public String version();

    public boolean on();

    public boolean connect(String satalite, String frequency);

    public void send(String request);

    public String receive();

    public boolean off();
}
