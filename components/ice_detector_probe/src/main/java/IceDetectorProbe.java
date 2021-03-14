public class IceDetectorProbe {
    //static instance
    private static IceDetectorProbe instance = new IceDetectorProbe();

    // port
    public Port port;
    private String manufacturer = "5736465";
    private boolean isActivated = false;
    private boolean isIceDetected = false;

    //private constructor
    private IceDetectorProbe() {
        this.port = new Port();
    }

    //static method getInstance
    public static IceDetectorProbe getInstance() {
        return instance;
    }

    //inner methods
    public String innerVersion() {
        return "IceDetectorProbe // " + this.manufacturer;
    }

    public boolean innerActivate() {
        return (this.isActivated = true);
    }

    public boolean innerDetect() {
        return true;
    }

    public boolean innerDetect(String surface) {
        return true;
    }

    public boolean innerDetect(String surface, String pattern) {
        return true;
    }

    public boolean innerDeactivate() {
        this.isActivated = false;
        return true;
    }


    //inner class port
    public class Port implements IIceDetectorProbe {

        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public boolean activate() {
            return innerActivate();
        }

        @Override
        public boolean detect() {
            return innerDetect();
        }

        @Override
        public boolean detect(String surface) {
            return innerDetect(surface);
        }

        @Override
        public boolean detect(String surface, String pattern) {
            return innerDetect(surface, pattern);
        }

        @Override
        public boolean deactivate() {
            return innerDeactivate();
        }
    }
    }
