public class FuelFlowSensor {
    //static instance
    private static FuelFlowSensor instance = new FuelFlowSensor();

    // port
    public Port port;
    private String manufacturer = "3186523";
    private int fuelFlow = 100;

    //private constructor
    private FuelFlowSensor() {
        this.port = new Port();
    }

    //static method getInstance
    public static FuelFlowSensor getInstance() {
        return instance;
    }
    //inner methods
    public String innerVersion() {
        return "FuelFlowSensor // " + this.manufacturer;
    }

    public int innerMeasure() {
        return (fuelFlow = fuelFlow - 10);
    }

    //inner class port
    public class Port implements IFuelFlowSensor {

        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public int measure() {
            return innerMeasure();
        }
    }
}
