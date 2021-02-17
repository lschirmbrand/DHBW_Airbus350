package event.cost_optimizer;

public class CostOptimizerValidate {
    private int costIndex = 0;

    public CostOptimizerValidate(int costIndex){
        this.costIndex = costIndex;
    }

    public int getCostIndex(){
        return this.costIndex;
    }

    public void setCostIndex(int costIndex){
        this.costIndex = costIndex;
    }

    public String toString() {
        return "Event: CostOptimizer - Validate";
    }
}
