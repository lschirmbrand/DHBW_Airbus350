public interface ITCAS {
    public String version();
    public boolean on();
    public boolean connect(String frequency);
    public boolean scan(String environment);
    public boolean alarm();
    public int determineAltitude(String environment);
    public int setAltitude(int value);
    public boolean off();
}
