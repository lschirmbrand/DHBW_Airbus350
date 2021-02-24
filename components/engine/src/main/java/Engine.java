public class Engine {

    private static final Engine instance = new Engine();

    public Engine.Port port;
    private final String manufacturer = "4775194";
    private boolean isStarted;
    private int rpm;
    private boolean isFire;

    private Engine() {
        port = new Engine.Port();
    }

    // static method getInstance
    public static Engine getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "Engine // " + manufacturer;
    }


    public boolean innerStart() {
        isStarted = true;
        this.rpm = 1000;
        return true;
    }

    public int innerIncreaseRPM(int value) {
        this.rpm += value;
        if (this.rpm > 15000) {
            this.rpm = 15000;
        }
        return this.rpm;
    }

    public int innerDecreaseRPM(int value) {
        this.rpm -= value;
        if (this.rpm < 0) {
            this.rpm = 0;
        }
        return this.rpm;
    }

    public boolean innerShutdown() {
        isStarted = false;
        this.rpm = 0;
        return false;
    }

    public void innerExtinguishFire() {
    }


    // inner class port
    public class Port implements IEngine {
        public String version() {
            return innerVersion();
        }

        public boolean start() {
            return innerStart();
        }

        public int increaseRPM(int value) {
            return innerIncreaseRPM(value);
        }

        public int decreaseRPM(int value) {
            return innerDecreaseRPM(value);
        }

        public boolean shutdown() {
            return innerShutdown();
        }

        public void extinguishFire() {
            innerExtinguishFire();
        }
    }
}
