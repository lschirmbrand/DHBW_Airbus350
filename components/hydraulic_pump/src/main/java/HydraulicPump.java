public class HydraulicPump{

    private static HydraulicPump instance = new HydraulicPump();

    public Port port;

    private String manufacturer = "4775194";

    private final int compressed = 5000;
    private final int decompressed = 0;
    private final int oil_filled = 5000;
    private final int oil_empty = 0;
    private int compress_amount = compressed;

    private int oil_amount = 1000;



    // private constructor
    private HydraulicPump() {
        port = new Port();
    }

    // static method getInstance
    public static HydraulicPump getInstance() {
        return instance;
    }

    public String innerVersion() {return "WeatherRadar // " + manufacturer;}

    public int innerCompress() {
        this.compress_amount = compressed;
        this.oil_amount = oil_filled;
        return this.compress_amount;
    }

    public int innerCompress(int amount) {
        this.compress_amount = amount;
        this.oil_amount = oil_filled;
        return this.compress_amount;
    }

    public int innerDecompress() {
        this.compress_amount = decompressed;
        this.oil_amount = oil_empty;
        return this.compress_amount;
    }

    public int innerRefillOil() {
        this.oil_amount = 5000;
        return oil_amount;
    }

    public int innerRefillOil(int amount) {
        this.oil_amount += amount;
        return this.oil_amount;
    }

    public class Port implements IHydraulicPump{

        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public int compress() {
            return innerCompress();
        }

        @Override
        public int compress(int amount) {
            return innerCompress(amount);
        }

        @Override
        public int decompress() {
            return innerDecompress();
        }

        @Override
        public int refillOil() {
            return innerRefillOil();
        }

        @Override
        public int refillOil(int amount) {
            return innerRefillOil(amount);
        }
    }
}

