public class NitrogenBottle {

    //static instance
    private static final NitrogenBottle instance = new NitrogenBottle();
    private final String manufacturer = "";
    private final String type = "Team 05";
    private final String id = "9008480";
    //port
    public Port port;
    private int amount = 250;

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
        return "NitrogenBottle // " + manufacturer + " - " + type + " - " + id;
    }


    public int innerTakeOut(int amount) {
        return this.amount -= amount;
    }


    public int innerRefill() {
        return this.amount = 250;
    }


    public int innerRefill(int amount) {
        return this.amount += amount;
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
