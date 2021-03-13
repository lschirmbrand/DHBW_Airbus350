public class CrewSeat {
    //static instance
    private static CrewSeat instance = new CrewSeat();

    // port
    public Port port;
    private String manufacturer = "3186523";
    private boolean isNonSmokingSingOn = false;
    private boolean isSeatBeltSignOn = false;

    //private constructor
    private CrewSeat() {
        this.port = new Port();
    }

    //static method getInstance
    public static CrewSeat getInstance() {
        return instance;
    }
    //inner methods
    public String innerVersion() {
        return "CrewSeat // " + this.manufacturer;
    }

    public int innerAssign(CrewMember crewMember) {
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
        return false;
    }

    //inner class port
    public class Port implements ICrewSeat {

        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public int assign(CrewMember crewMember) {
            return innerAssign(crewMember);
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
    }
}
