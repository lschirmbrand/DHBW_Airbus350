@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class DroopNose {

    private static final DroopNose instance = new DroopNose();
    private final String manufacturer = "1253402";
    public Port port = new Port();
    private int degree;

    private DroopNose() {

    }

    public static DroopNose getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "DroopNose // " + manufacturer;
    }

    public int innerNeutral() {
        degree = 0;
        return degree;
    }

    public int innerFullDown() {
        degree = -90;
        return degree;
    }

    public int innerDown(int degree) {
        this.degree -= degree;
        if (this.degree < -90) {
            this.degree = -90;
        }
        return this.degree;
    }

    public int innerUp(int degree) {
        this.degree += degree;
        if (this.degree > 90) {
            this.degree = 90;
        }
        return this.degree;
    }

    public class Port implements IDroopNose {
        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public int neutral() {
            return innerNeutral();
        }

        @Override
        public int fullDown() {
            return innerFullDown();
        }

        @Override
        public int down(int degree) {
            return innerDown(degree);
        }

        @Override
        public int up(int degree) {
            return innerUp(degree);
        }
    }
}
