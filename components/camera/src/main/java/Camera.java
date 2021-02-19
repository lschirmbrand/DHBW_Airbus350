public class Camera {

    private static Camera instance = new Camera();
    private String manufacturer = "Manuel Truckses / Andreas Köhler";
    private CameraType type;
    private String id = "9008480 / 1253402";
    private boolean isOn;
    public Port port = new Port();

    private Camera() {

    }

    public String innerVersion() {
        return manufacturer + "; " + type + "; " + id;
    }

    public static Camera getInstance() {
        return instance;
    }

    public CameraType innerSetType(String type) {
        this.type = CameraType.valueOf(type);
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
        return true;
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
