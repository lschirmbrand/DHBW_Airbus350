public class Elevator {

    private static Elevator instance = new Elevator();

    public Elevator.Port port;
    private String manufacturer = "4775194";
    private int degree = 90;

    private Elevator() {port = new Elevator.Port();}

    // static method getInstance
    public static Elevator getInstance() {return instance;}

    // inner methods
    public String innerVersion() {
        return "Elevator // " + manufacturer;
    }

    public int innerNeutral(){
        this.degree = 90;
        return this.degree;
    }

    private int innerFullUp() {
        this.degree = 180;
        return this.degree;
    }

    private int innerFullDown() {
        this.degree = 45;
        return this.degree;
    }

    private int innerUp(int degree) {
        this.degree += degree;
        return this.degree;
    }

    private int innerDown(int degree) {
        this.degree -= degree;
        return this.degree;
    }


    public class Port implements IElevator{

        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public int neutral() { ;
            return innerNeutral();
        }

        @Override
        public int fullUp() {
            return innerFullUp();
        }

        @Override
        public int fullDown() {
            return innerFullDown();
        }

        @Override
        public int up(int degree) {
            return innerUp(degree);
        }

        @Override
        public int down(int degree) {
            return innerDown(degree);
        }
    }

}


