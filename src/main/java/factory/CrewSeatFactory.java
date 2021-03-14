package factory;

import configuration.Configuration;

public class CrewSeatFactory {
    public static Object build() {
        return Factory.build("CrewSeat", Configuration.instance.pathToCrewSeatJavaArchive);
    }
}
