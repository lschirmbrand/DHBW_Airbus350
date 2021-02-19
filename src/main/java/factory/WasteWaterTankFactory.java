package factory;

import configuration.Configuration;

public class WasteWaterTankFactory {
    public static Object build() {

        return Factory.build("WasteWaterTank", Configuration.instance.pathToWasteWaterTankJavaArchive);
    }
}
