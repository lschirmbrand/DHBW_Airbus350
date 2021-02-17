public class CheckPoint{
	int sequenceID;
	String name;
	String latitude;
	String longitude;

	public GetSequenceID(){return sequenceID;}
	public GetName(){return name;}
	public GetLatitude(){return latitude;}
	public GetLongitude(){return longitude;}

	public SetSequenceID(int s){this.sequenceID = s}
	public SetName(String n){name = n;}
	public SetLatitude(String l){latitude = l;}
	public SetLongitude(String l){longitude = l;}

	public CheckPoint(int s, String n, String la, String lo){
		sequenceID = s;
		name = n;
		latitude = la;
		longitude = lo;
	}
}