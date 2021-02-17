public interface IRouteManagement {
    String version();

    boolean on();

    boolean off();

    int add(CheckPoint checkPoint);

    int remove(int checkPoint);

    void printCheckPoints();

    double setCostIndex(int value);
}