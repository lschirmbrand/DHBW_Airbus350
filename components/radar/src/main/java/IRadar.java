public interface IRadar {
    public String version();
    public boolean on();
    public boolean off();
    public boolean scan(String environment);
}
