public class BusinessClassSeat {
    //static instance
    private static BusinessClassSeat instance = new BusinessClassSeat();

    // port
    public Port port;
    private String manufacturer = "3186523";
    private Passenger passenger;
    private int level;
    private boolean isNonSmokingSingOn = false;
    private boolean isSeatBeltSignOn = false;

    //private constructor
    private BusinessClassSeat() {
        this.port = new Port();
    }

    //static method getInstance
    public static BusinessClassSeat getInstance() {
        return instance;
    }
    //inner methods
    public String innerVersion() {
        return "BusinessClassSeat // " + this.manufacturer;
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

    public boolean innerReadingLightOn() {
        return true;
    }

    public boolean innerReadingLightOff() {
        return true;
    }

    public boolean innerUpRight() {
        return true;
    }

    public int innerLevel1() {
        return (this.level = 1);
    }

    public int innerLevel2() {
        return  (this.level = 2);
    }

    //inner class port
    public class Port implements IBusinessClassSeat {

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
        public boolean readingLightOn() {
            return innerReadingLightOn();
        }

        @Override
        public boolean readingLightOff() {
            return innerReadingLightOff();
        }

        @Override
        public void upRight() {
            innerUpRight();
        }

        @Override
        public int level1() {
            return innerLevel1();
        }

        @Override
        public int level2() {
            return innerLevel2();
        }
    }
}
