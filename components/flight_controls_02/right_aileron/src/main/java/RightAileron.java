public class RightAileron {
    // static instance
    private static RightAileron instance = new RightAileron();
    // port
    public Port port;
    private String manufacturer = "<student name 01> / <student name 02>";
    private String type = "02";
    private String id = "<3106335> / <4669114>";
    private int degree = 0;

    // private constructor
    private RightAileron() {
        port = new Port();
    }

    // static method getInstance
    public static RightAileron getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "RightAileron // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerNeutral() {
        degree = 0;
        return degree;
    }

    public int innerFullDown() {
        degree = 0;//TODO maximalen Drehgrad rausfinden
        return degree;
    }

    public int innerFullUp() {
        degree = 0;//TODO maximalen Drehgrad rausfinden
        return degree;
    }

    public int innerDown(int degree) {
        this.degree -= degree;//TODO maximalen Drehgrad rausfinden
        return this.degree;
    }

    public int innerUp(int degree) {
        this.degree += degree;//TODO maximalen Drehgrad rausfinden
        return this.degree;
    }

    public boolean innerScan(String environment) {
        return environment.contains("bird");
    }

    // inner class port
    public class Port implements IRightAileron {
        public String version() {return innerVersion();}

        public int neutral() {return innerNeutral();}

        public int fullDown() {return innerFullDown();}

        public int fullUp() {return innerFullUp();}

        public int down(int degree) {return innerDown(degree);}

        public int up(int degree) {return innerUp(degree);}
    }
}
