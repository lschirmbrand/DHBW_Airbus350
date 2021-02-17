public interface IWeatherRadar {
    	String version();
	boolean On();
	int add (CheckPoint checkPoint);
	int remove(int checkPoint);
	int optimize(ArrayList<CheckPoint> checkPointList);
	boolean validate(int costIndex);
	boolean off();
}