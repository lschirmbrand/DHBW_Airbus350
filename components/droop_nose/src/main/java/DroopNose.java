public class DroopNose {

    private static final DroopNose instance = new DroopNose();
    private final String manufacturer = "Manuel Truckses / Andreas Köhler";
    private final String type = "Team 05";
    private final String id = "9008480 / 1253402";
    public Port port = new Port();
    private int degree;

    private DroopNose() {

    }

    public static DroopNose getInstance() {
        return instance;
    }

    public String innerVersion() {
        return manufacturer + "; " + type + "; " + id;
    }

    public int innerNeutral() {
        return 0;
    }

    public int innerFullDown() {
        return 0;
    }

    public int innerDown(int degree) {
        this.degree -= degree;
        return degree;
    }

    public int innerUp(int degree) {
        this.degree += degree;
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
