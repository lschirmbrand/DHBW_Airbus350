public class Elevator {

    private static Elevator instance = new Elevator();

    public Elevator.Port port;
    private String manufacturer = "<student name 01> / <student name 02>";
    private String type = "team <id>";
    private String id = "<student id 01> / <student id 02>";
    private int degree = 90;

    private Elevator() {port = new Elevator.Port();}

    // static method getInstance
    public static Elevator getInstance() {return instance;}

    // inner methods
    public String innerVersion() {
        return "Elevator // " + manufacturer + " - " + type + " - " + id;
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
        this.degree = 0;
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


