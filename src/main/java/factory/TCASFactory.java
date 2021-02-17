package factory;

import configuration.Configuration;

public class TCASFactory {
    public static Object build() {
        return Factory.build("TCAS", Configuration.instance.pathToTCASJavaArchive);
    }
}
