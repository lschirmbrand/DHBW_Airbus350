public class CargoCompartmentLight{
    // static instance
    private static CargoCompartmentLight instance = new CargoCompartmentLight();
    // port
    public Port port;
    private String manufacturer = "<4669114>/<?>";
    private String type = "team <02>";
    private String id = "<student id 01> / <student id 02>";
    private boolean isOn = false;
    private int intensity = 0;

    // private constructor
    private CargoCompartmentLight() {
        port = new Port();
    }

    // static method getInstance
    public static CargoCompartmentLight getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "WeatherRadar // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerOn() {
        isOn = true;
	intensity = 100;
        return isOn;
    }

    public boolean innerOff() {
        isOn = false;
	intensity = 0;
        return isOn;
    }

    public void innerDim(){
        intensity -= 10;
	if(intensity < 0) intensity = 0;
    }

    // inner class port
    public class Port implements ICargoCompartmentLight{
        public String version() {
            return innerVersion();
        }

        public boolean on() {
            return innerOn();
        }

        public boolean off() {
            return innerOff();
        }

        public void dim() {
            innerDim();
        }
    }
}