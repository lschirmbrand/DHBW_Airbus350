public class NitrogenBottle {

    //static instance
    private static NitrogenBottle instance = new NitrogenBottle();

    //port
    public Port port;
    private String manufacturer = "Manuel Truckses / Andreas Köhler";
    private String type = "Team 05";
    private String id = "9008480 / 1253402";
    private int amount = 250;

    // static method getInstance
    public static NitrogenBottle getInstance() {
        return instance;
    }

    // private constructor
    private NitrogenBottle() {
        port = new Port();
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
