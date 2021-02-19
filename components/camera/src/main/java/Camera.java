public enum Camera {
    INSTANCE;

    private final String manufacturer = "Manuel Truckses / Andreas Köhler";
    private final String id = "9008480 / 1253402";
    public Port port = new Port();
    private CameraType type;
    private boolean isOn;

    public String innerVersion() {
        return manufacturer + "; " + type + "; " + id;
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
