public class WasteWaterTank {
    // static instance
    private static final WasteWaterTank instance = new WasteWaterTank();
    // port
    public Port port;
    private final String manufacturer = "9008480";
    private int capacity = 1000;//1000
    private boolean isLocked;

    // private constructor
    private WasteWaterTank() {
        port = new Port();
    }

    //instance getter
    public static WasteWaterTank getInstance() {
        return instance;
    }

    //inner methodes
    public String innerVersion() {
        return "WasteWaterTank // " + manufacturer;
    }

    public boolean innerLock() {
        return isLocked = true;
    }

    public boolean innerUnlock() {
        return isLocked = false;
    }

    public int innerAdd(int amount) {
        capacity += amount;
        return capacity;
    }

    public int innerPumpOut() {
        return capacity = 0;
    }

    //inner class port
    public class Port implements IWasteWaterTank {

        public String version() {
            return innerVersion();
        }

        public boolean lock() {
            return innerLock();
        }

        public boolean unlock() {
            return innerUnlock();
        }

        public int add(int amount) {
            return innerAdd(amount);
        }

        public int pumpOut() {
            return innerPumpOut();
        }
    }
}
