package factory;

import configuration.Configuration;

public class VHFFactory {
    public static Object build() {

        return Factory.build("VHF", Configuration.instance.pathToVHFJavaArchive);
    }
}
