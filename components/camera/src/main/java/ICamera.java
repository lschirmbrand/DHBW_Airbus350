public interface ICamera {
    public String version();
    public CameraType setType(String type);
    public boolean on();
    public String record();
    public String zoomIn(double factor);
    public String zoomOut(double factor);
    public boolean off();
}
