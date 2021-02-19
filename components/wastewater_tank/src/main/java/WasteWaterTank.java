public class WasteWaterTank {
    // static instance
    private static WasteWaterTank instance = new WasteWaterTank();
    // port
    public Port port;
    private String manufacturer = "Manuel Truckses / Andreas Köhler";
    private String type = "Team 05";
    private String id = "9008480 / 1253402";
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
        return "WasteWaterTank // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerLock() {
        return isLocked = true;
    }

    public boolean innerUnlock() {
        return isLocked = false;
    }

    public int innerAdd(int amount) {
        return capacity + amount;
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
