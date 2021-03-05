public class Battery {
    // static instance
    private static Battery instance = new Battery();
    // port
    public Port port;
    private String manufacturer = "2529977, 6499887";
    private int percentage;

    // private constructor
    private Battery() {
        port = new Port();
    }

    // static method getInstance
    public static Battery getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() { return "Battery // " + manufacturer; }

    public int innerCharge() {
        if (percentage < 100) {
            percentage++;
        }
        return percentage;
    }

    public int innerDischarge() {
        if (percentage > 0 ) {
            percentage--;
        }
        return percentage;
    }


    // inner class port
    public class Port implements IBattery {
        public String version() {
            return innerVersion();
        }

        @Override
        public int charge() {
            return innerCharge();
        }

        @Override
        public int discharge() {
            return innerDischarge();
        }
    }
}
