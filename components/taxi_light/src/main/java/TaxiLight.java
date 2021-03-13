public class TaxiLight {
    //static instance
    private static TaxiLight instance = new TaxiLight();

    // port
    public Port port;
    private String manufacturer = "5736465";
    private boolean isOn = false;

    //private constructor
    private TaxiLight() {
        this.port = new Port();
    }

    //static method getInstance
    public static TaxiLight getInstance() {
        return instance;
    }

    //inner methods
    public String innerVersion() {
        return "TaxiLight // " + this.manufacturer;
    }

    public boolean innerOn() {
        return (this.isOn = true);
    }

    public boolean innerOff() {
        return (this.isOn = false);
    }

    //inner class port
    public class Port implements ITaxiLight {

        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public boolean on() {
            return innerOn();
        }

        @Override
        public boolean off() {
            return innerOff();
        }
    }
}
