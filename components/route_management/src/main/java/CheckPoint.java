public class CheckPoint{
	int sequenceID;
	String name;
	String latitude;
	String longitude;

	public int GetSequenceID(){return sequenceID;}
	public String GetName(){return name;}
	public String GetLatitude(){return latitude;}
	public String GetLongitude(){return longitude;}

	public void SetSequenceID(int s){this.sequenceID = s;}
	public void SetName(String n){name = n;}
	public void SetLatitude(String l){latitude = l;}
	public void SetLongitude(String l){longitude = l;}

	public CheckPoint(int s, String n, String la, String lo){
		sequenceID = s;
		name = n;
		latitude = la;
		longitude = lo;
	}
}