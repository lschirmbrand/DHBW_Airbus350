public class LeftNavigationLight{
    // static instance
    private static LeftNavigationLightinstance = new LeftNavigationLight();
    // port
    public Port port;
    private String manufacturer = "<student name 01> / <student name 02>";
    private String type = "team <id>";
    private String id = "<student id 01> / <student id 02>";
    private boolean isOn = false;

    // private constructor
    private LeftNavigationLight() {
        port = new Port();
    }

    // static method getInstance
    public static LeftNavigationLightgetInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "LeftNavigationLight // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerOn() {
        isOn = true;
        return isOn;
    }

    public boolean innerOff() {
        isOn = false;
        return isOn;
    }

    public LightType innerSetType(String type){
	if(type.equals("green")) return LightType.green;
	return LightType.red;
    }

    public Position innerSetPosition(String position){
	if(position.equals("startboard")) return Position.startboard;
	return Position.port;
    }

    // inner class port
    public class Port implements ILeftNavigationLight{
        public String version() {
            return innerVersion();
        }

        public boolean on() {
            return innerOn();
        }

        public boolean off() {
            return innerOff();
        }

        public LightType setLightType(String type) {
	    return innerSetLightType(type);
	}

	public Position setPosistion(String position) {
	    return innerSetPosition(position);
	}
    }
}