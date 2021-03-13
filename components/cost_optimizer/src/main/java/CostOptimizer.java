import java.util.ArrayList;

public class CostOptimizer{
    // static instance
    private static CostOptimizer instance = new CostOptimizer();
    // port
    public Port port;
    private String manufacturer = "<student name 01> / <student name 02>";
    private String type = "team <id>";
    private String id = "<student id 01> / <student id 02>";
    private boolean isOn = false;

    ArrayList<CheckPoint> checkPointList;
    int costIndex = 0;

    // private constructor
    private CostOptimizer() {
        port = new Port();
        checkPointList = new ArrayList<CheckPoint>();
    }

    // static method getInstance
    public static CostOptimizer getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "CostOptimizer// " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerOn() {
        isOn = true;
        return isOn;
    }

    public boolean innerOff() {
        isOn = false;
        return isOn;
    }

    public int innerAdd(CheckPoint cp) {
        checkPointList.add(cp);
	    return checkPointList.size();
    }

    public int innerRemove(int checkPoint){
	    checkPointList.remove(checkPoint);
	    return checkPointList.size();
    }

    public int innerOptimize(ArrayList<CheckPoint> checkPointList){
	    costIndex = checkPointList.size();
	    return costIndex;
    }

    public boolean innerValidate(int costIndex)
    {
	    return this.costIndex == costIndex;
    }

    // inner class port
    public class Port implements ICostOptimizer{
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

	    public int optimize(ArrayList<CheckPoint> checkPointList){
	    return innerOptimize(checkPointList);
	}

	    public boolean validate(int costIndex){
	    return innerValidate(costIndex);
	}
    }
}