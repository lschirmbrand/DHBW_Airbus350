public class Engine {

    private static Engine instance = new Engine();

    public Engine.Port port;
    private String manufacturer = "<student name 01> / <student name 02>";
    private String type = "team <id>";
    private String id = "<student id 01> / <student id 02>";
    private String start;
    private boolean isStarted;
    private int rpm;
    private boolean isFire;

    private Engine() {port = new Engine.Port();}

    // static method getInstance
    public static Engine getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "Engine // " + manufacturer + " - " + type + " - " + id;
    }


    public boolean innerStart() {
        isStarted = true;
        this.rpm = 1000;
        return true;
    }

    public int innerIncreaseRPM(int value) {
        this.rpm += value;
        return this.rpm;
    }

    public int innerDecreaseRPM(int value) {
        return 0;
    }

    public boolean innerShutdown() {
        isStarted =  false;
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
