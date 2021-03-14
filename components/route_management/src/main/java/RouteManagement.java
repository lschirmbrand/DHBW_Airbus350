import java.util.ArrayList;

public class RouteManagement{
    // static instance
    private static RouteManagement instance = new RouteManagement();
    // port
    public Port port;
    private String manufacturer = "<student name 01> / <student name 02>";
    private String type = "team <id>";
    private String id = "<student id 01> / <student id 02>";
    private boolean isOn = false;
    private ArrayList<CheckPoint> checkPointList = new ArrayList<CheckPoint>();
    private double costIndex = 0;

    // private constructor
    private RouteManagement() {
        port = new Port();
    }

    // static method getInstance
    public static RouteManagement getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "RouteManagement // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerOn() {
        isOn = true;
        return isOn;
    }

    public boolean innerOff() {
        isOn = false;
        return isOn;
    }

    public int innerAdd(CheckPoint checkPoint){
	checkPointList.add(checkPoint);
	return checkPointList.size();
    }

    public int innerRemove(int checkPoint){
	checkPointList.remove(checkPoint);
	return checkPointList.size();
    }

    public void innerPrintCheckPoints(){
	for(CheckPoint cp : checkPointList)
		System.out.println("CheckPoint: "+cp.GetName() + "| ID: " + cp.GetSequenceID() + "|Position: " + cp.GetLatitude() + " " + cp.GetLongitude() + "//");
    }

    public double innerSetCostIndex(int value){
	this.costIndex = value;
	return costIndex;
    }

    // inner class port
    public class Port implements IRouteManagement{
        public String version() {
            return innerVersion();
        }

        public boolean on() {
            return innerOn();
        }

        public boolean off() {
            return innerOff();
        }

        public int add(CheckPoint checkPoint){
	    return innerAdd(checkPoint);
        }

	    public int remove(int checkPoint){
	    return innerRemove(checkPoint);
	}

	    public void printCheckPoints(){
	     innerPrintCheckPoints();
	    }

	    public double setCostIndex(int value){
	    return innerSetCostIndex(value);
	}
    }
}