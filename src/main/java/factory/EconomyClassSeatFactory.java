package factory;

import configuration.Configuration;

public class EconomyClassSeatFactory {
    public static Object build() {
        return Factory.build("EconomyClassSeat", Configuration.instance.pathToEconomyClassSeatJavaArchive);
    }
}
