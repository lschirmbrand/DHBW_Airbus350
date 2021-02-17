public class HydraulicPump{

    private static HydraulicPump instance = new HydraulicPump();

    public Port port;

    private String manufacturer = "<student name 01> / <student name 02>";
    private String type = "team <id>";
    private String id = "<student id 01> / <student id 02>";
    private int oil_amount = 1000;
    private int compress_amount = 1000;

    private final int compressed = 1000;
    private final int decompressed = 0;

    private final int oil_filled = 1000;
    private final int oil_empty = 0;

    // private constructor
    private HydraulicPump() {
        port = new Port();
    }

    // static method getInstance
    public static HydraulicPump getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "WeatherRadar // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerCompress() {
        this.compress_amount = compressed;
        return this.compress_amount;
    }

    public int innerCompress(int amount) {
        this.compress_amount = amount;
        return this.compress_amount;
    }

    public int innerDecompress() {
        this.compress_amount = decompressed;
        return this.compress_amount;
    }

    public int innerRefillOil() {
        this.oil_amount = 1000;
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

