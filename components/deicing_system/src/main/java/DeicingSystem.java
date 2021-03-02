public class DeicingSystem {
    // static instance
    private static DeicingSystem instance = new DeicingSystem();
    // port
    public Port port;
    private String manufacturer = "2529977, 6499887";
    public int amount = 1000;
    private boolean isActivated;

    // private constructor
    private DeicingSystem() {
        port = new Port();
    }

    // static method getInstance
    public static DeicingSystem getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "DeIcingSystem // " + manufacturer ;
    }

    public boolean innerActivate() {
        isActivated = true;
        return true;
    }

    public int innerDeIceAmount(int amount) { return this.amount - amount; }

    public int innerRefill() {
        this.amount = 1000;
        return this.amount;
    }

    public boolean innerDeactivate() { return isActivated = false; }


    // inner class port
    public class Port implements IDeicingSystem {
        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public boolean activate() { return innerActivate(); }

        @Override
        public int deIce(int amount) { return innerDeIceAmount(amount); }

        @Override
        public int refill() { return innerRefill(); }

        @Override
        public boolean deactivate() { return innerDeactivate(); }
    }
}
