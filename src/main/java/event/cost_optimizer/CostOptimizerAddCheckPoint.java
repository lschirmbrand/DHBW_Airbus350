package event.cost_optimizer;

public class CostOptimizerAddCheckPoint {
    CheckPoint cp;
    public String toString() {
        return "Event: CostOptimizer - Add";
    }

    public CostOptimizerAddCheckPoint(CheckPoint cp){
        this.cp = cp;
    }

    public CheckPoint getCheckPoint(){
        return cp;
    }

    public void setCheckPoint(CheckPoint cp){
        this.cp = cp;
    }
}
