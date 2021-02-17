public class Rudder {
    // static instance
    private static Rudder instance = new Rudder();
    // port
    public Port port;
    private String manufacturer = "<student name 01> / <student name 02>";
    private String type = "02";
    private String id = "<3106335> / <4669114>";
    private int degree = 0;

    // private constructor
    private Rudder() {
        port = new Port();
    }

    // static method getInstance
    public static Rudder getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "Rudder // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerNeutral() {
        degree = 0;
        return degree;
    }

    public int innerFullLeft() {
        degree = 0;//TODO maximalen Drehgrad rausfinden
        return degree;
    }

    public int innerFullRight() {
        degree = 0;//TODO maximalen Drehgrad rausfinden
        return degree;
    }

    public int innerLeft(int degree) {
        this.degree -= degree;//TODO maximalen Drehgrad rausfinden
        return this.degree;
    }

    public int innerRight(int degree) {
        this.degree += degree;//TODO maximalen Drehgrad rausfinden
        return this.degree;
    }

    // inner class port
    public class Port implements IRudder {
        public String version() {return innerVersion();}

        public int neutral() {return innerNeutral();}

        public int fullLeft() {return innerFullLeft();}

        public int fullRight() {return innerFullRight();}

        public int left(int degree) {return innerLeft(degree);}

        public int right(int degree) {return innerRight(degree);}
    }
}
