public class Slat {
    // static instance
    private static Slat instance = new Slat();
    // port
    public Port port;
    private String manufacturer = "<3106335> / <4669114>";
    private int degree = 0;

    // private constructor
    private Slat() {
        port = new Port();
    }

    // static method getInstance
    public static Slat getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "Slat // " + manufacturer;
    }

    public int innerNeutral() {
        degree = 0;
        return degree;
    }

    public int innerFullDown() {
        degree = -20;
        return degree;
    }

    public int innerDown(int degree) {
        this.degree -= degree;
        if(this.degree < -20) this.degree = -20;
        return this.degree;
    }

    public int innerUp(int degree) {
        this.degree += degree;
        if(this.degree > 0) this.degree = 0;
        return this.degree;
    }

    public boolean innerScan(String environment) {
        return environment.contains("bird");
    }

    // inner class port
    public class Port implements ISlat {
        public String version() {return innerVersion();}

        public int neutral() {return innerNeutral();}

        public int fullDown() {return innerFullDown();}

        public int down(int degree) {return innerDown(degree);}

        public int up(int degree) {return innerUp(degree);}
    }
}
