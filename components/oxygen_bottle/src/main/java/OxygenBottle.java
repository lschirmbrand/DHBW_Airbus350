public class OxygenBottle {
    // static instance
    private static final OxygenBottle instance = new OxygenBottle();
    private final String manufacturer = "Manuel Truckses / Andreas Köhler";
    private final String type = "Team 05";
    private final String id = "9008480 / 1253402";
    // port
    public Port port;
    private int amount = 100;

    // private constructor
    private OxygenBottle() {
        port = new Port();
    }

    public static OxygenBottle getInstance() {
        return instance;
    }

    //inner methods
    public String innerVersion() {
        return "OxygenBottle // " + manufacturer + " - " + type + " - " + id;
    }


    public int innerTakeOut(int amount) {
        return this.amount -= amount;
    }


    public int innerRefill() {
        return this.amount = 100;
    }


    public int innerRefill(int amount) {
        return this.amount += amount;
    }

    //inner class port
    public class Port implements IOxygenBottle {

        public String version() {
            return innerVersion();
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
