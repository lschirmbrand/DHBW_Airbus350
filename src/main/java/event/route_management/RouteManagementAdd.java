package event.route_management;

public class RouteManagementAdd {
    CheckPoint checkPoint;

    public void setCheckPoint(CheckPoint checkPoint) {
        this.checkPoint = checkPoint;
    }

    public CheckPoint getCheckPoint() {
        return checkPoint;
    }

    public RouteManagementAdd(CheckPoint checkPoint){
        this.checkPoint = checkPoint;
    }

    public String toString() {
        return "Event: RouteManagement - Add";
    }
}
