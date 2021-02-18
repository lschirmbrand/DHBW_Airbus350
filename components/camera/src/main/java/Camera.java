public class Camera implements ICamera {

    private String manufacturer = "Manuel Truckses / Andreas Köhler";
    private CameraType type;
    private String id = "9008480 / 1253402";
    private boolean isOn;

    @Override
    public String version() {
        return null;
    }

    @Override
    public CameraType setType(String type) {
        this.type = CameraType.valueOf(type);
        return this.type;
    }

    @Override
    public boolean on() {
        isOn = true;
        return true;
    }

    @Override
    public String record() {
        return null;
    }

    @Override
    public String zoomIn(double factor) {
        return null;
    }

    @Override
    public String zoomOut(double factor) {
        return null;
    }

    @Override
    public boolean off() {
        isOn = false;
        return true;
    }
}
