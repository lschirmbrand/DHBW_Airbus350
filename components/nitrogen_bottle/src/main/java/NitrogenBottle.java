public class NitrogenBottle {

    //static instance
    private static final NitrogenBottle instance = new NitrogenBottle();
    private final String manufacturer = "9008480";
    //port
    public Port port;
    private int amount = 0;

    // private constructor
    private NitrogenBottle() {
        port = new Port();
    }

    // static method getInstance
    public static NitrogenBottle getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "NitrogenBottle // " + manufacturer;
    }


    public int innerTakeOut(int amount) {
        this.amount -= amount;
        if (this.amount < 0) {
            this.amount = 0;
        }
        return this.amount;
    }


    public int innerRefill() {
        return this.amount = 250;
    }


    public int innerRefill(int amount) {
        this.amount += amount;
        if (this.amount > 250) this.amount = 250;
        return this.amount;
    }

    // inner class port
    public class Port implements INitrogenBottle {
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
