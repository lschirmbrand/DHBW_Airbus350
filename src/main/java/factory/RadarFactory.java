package factory;

import configuration.Configuration;

public class RadarFactory {
    public static Object build() {
        return Factory.build("Radar", Configuration.instance.pathToRadarJavaArchive);
    }
}
