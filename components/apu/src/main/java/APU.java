public class APU {
    // static instance
    private static final APU instance = new APU();
    // port
    public Port port;
    private final String manufacturer = "8864957";
    private boolean isStarted = false;
    private int rpm = 0;

    // private constructor
    private APU() {
        port = new Port();
    }

    // static method getInstance
    public static APU getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "APU // " + manufacturer;
    }

    public boolean innerStart() {
        isStarted = true;
        return true;
    }

    public int innerIncreaseRPM(int value) {
        rpm += value;
        if (rpm > 5000) {
            rpm = 5000;
        }
        return rpm;
    }

    public int innerDecreaseRPM(int value) {
        rpm -= value;
        if (rpm < 0) {
            rpm = 0;
        }
        return rpm;
    }

    public void innerShutdown() {
        isStarted = false;
        rpm = 0;
    }

    // inner class port
    public class Port implements IAPU {
        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public boolean start() {
            return innerStart();
        }

        @Override
        public int increaseRPM(int value) {
            return innerIncreaseRPM(value);
        }

        @Override
        public int decreaseRPM(int value) {
            return innerDecreaseRPM(value);
        }

        @Override
        public void shutdown() {
            innerShutdown();
        }
    }
}