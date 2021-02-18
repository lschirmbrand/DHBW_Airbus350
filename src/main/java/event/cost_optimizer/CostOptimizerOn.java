package event.cost_optimizer;

import base.PrimaryFlightDisplay;

public class CostOptimizerOn {
    public CostOptimizerOn(){
        PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer = 0;
        PrimaryFlightDisplay.instance.indexCostOptimizer = 0;
    }
    public String toString() {
        return "Event: CostOptimizer - On";
    }
}
