public interface IElevator {

    public String version();
    public int neutral();
    public int fullUp();
    public int fullDown();
    public int up(int degree);
    public int down(int degree);
}
