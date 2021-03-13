public class OxygenBottle {
    // static instance
    private static final OxygenBottle instance = new OxygenBottle();
    private final String manufacturer = "9008480";
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
        return "OxygenBottle // " + manufacturer;
    }


    public int innerTakeOut(int amount) {
        this.amount -= amount;
        if (this.amount < 0) {
            this.amount = 0;
        }
        return this.amount;
    }


    public int innerRefill() {
        return this.amount = 100;
    }


    public int innerRefill(int amount) {
        this.amount += amount;
        if (this.amount > 100) {
            this.amount = 100;
        }
        return this.amount;
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
