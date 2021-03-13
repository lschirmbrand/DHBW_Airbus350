public class EconomyClassSeat {
    //static instance
    private static EconomyClassSeat instance = new EconomyClassSeat();

    // port
    public Port port;
    private String manufacturer = "5736465";
    private Passenger passenger;
    private int level;
    private boolean isNonSmokingSingOn = false;
    private boolean isSeatBeltSignOn = false;

    //private constructor
    private EconomyClassSeat() {
        this.port = new Port();
    }

    //static method getInstance
    public static EconomyClassSeat getInstance() {
        return instance;
    }
    //inner methods
    public String innerVersion() {
        return "EconomyClassSeat // " + this.manufacturer;
    }

    public int innerAssign(Passenger passenger) {
        this.passenger = passenger;
        return 1;
    }

    public boolean innerNonSmokingSignOn() {
        return (this.isNonSmokingSingOn = true);
    }

    public boolean innerNonSmokingSignOff() {
        return (this.isNonSmokingSingOn = false);
    }

    public boolean innerSeatBeltSignOn() {
        return (this.isSeatBeltSignOn = true);
    }

    public boolean innerSeatBeltSignOff() {
        return (this.isSeatBeltSignOn = false);
    }

    public boolean innerUpRight() {
        return true;
    }

    public int innerLevel1() {
        return (this.level = 1);
    }

    //inner class port
    public class Port implements IEconomyClassSeat {

        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public int assign(Passenger passenger) {
            return innerAssign(passenger);
        }

        @Override
        public boolean nonSmokingSignOn() {
            return innerNonSmokingSignOn();
        }

        @Override
        public boolean nonSmokingSignOff() {
            return innerNonSmokingSignOff();
        }

        @Override
        public boolean seatBeltSignOn() {
            return innerSeatBeltSignOn();
        }

        @Override
        public boolean seatBeltSignOff() {
            return innerSeatBeltSignOff();
        }

        @Override
        public void upRight() {
            innerUpRight();
        }

        @Override
        public int level1() {
            return innerLevel1();
        }
    }
}
