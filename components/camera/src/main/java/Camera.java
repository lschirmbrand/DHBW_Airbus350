@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class Camera {

    private static final Camera instance = new Camera();
    private final String manufacturer = "Andreas Köhler / Manuel Truckses";
    private final String id = "1253402 / 9008480";
    public Port port = new Port();
    private CameraType type;
    private boolean isOn;

    private Camera() {

    }

    public static Camera getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "Camera // " + manufacturer + ";  " + id;
    }

    public CameraType innerSetType(String type) {
        this.type = CameraType.valueOf(type.toUpperCase());
        return this.type;
    }

    public boolean innerOn() {
        isOn = true;
        return true;
    }

    public String innerRecord() {
        return "Camera record";
    }

    public String innerZoomIn(double factor) {
        return "" + factor;
    }

    public String innerZoomOut(double factor) {
        return "" + factor;
    }

    public boolean innerOff() {
        isOn = false;
        return false;
    }

    public class Port implements ICamera {
        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public CameraType setType(String type) {
            return innerSetType(type);
        }

        @Override
        public boolean on() {
            return innerOn();
        }

        @Override
        public String record() {
            return innerRecord();
        }

        @Override
        public String zoomIn(double factor) {
            return innerZoomIn(factor);
        }

        @Override
        public String zoomOut(double factor) {
            return innerZoomOut(factor);
        }

        @Override
        public boolean off() {
            return innerOff();
        }
    }
}
