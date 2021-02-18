public class PortableWaterTank {
    // static instance
    private static PortableWaterTank instance = new PortableWaterTank();
    // port
    public Port port;
    private String manufacturer = "Manuel Truckses / Andreas Köhler";
    private String type = "Team 05";
    private String id = "9008480 / 1253402";
    private int amount = 1000;
    private boolean isLocked = false;

    //private constructor
    private PortableWaterTank() {
        port = new Port();
    }

    // static method getInstance
    public static PortableWaterTank getInstance() {
        return instance;
    }
    //inner methods

    public String innerVersion() {
        return "PortableWaterTank // " + manufacturer + " - " + type + " - " + id;
    }


    public int innerTakeOut(int amount) {
        return this.amount -= amount;
    }


    public int innerRefill() {
        return this.amount = 1000;
    }


    public int innerRefill(int amount) {
        return this.amount += amount;
    }

    public boolean innerLock() {
        return isLocked = true;
    }

    public boolean innerUnlock() {
        return isLocked = false;
    }
    //inner class port
    public class Port implements IPortableWaterTank {


        public String version() {
            return innerVersion();
        }

        public boolean lock() {
            return innerLock();
        }

        public boolean unlock() {
            return innerUnlock();
        }

        public int takeOut(int amount) {
            return innerTakeOut(amount);
        }

        public int refill() {
            return innerRefill();
        }

        public int refill(int amount) {
            return innerRefill(amount);
        }
    }
}
