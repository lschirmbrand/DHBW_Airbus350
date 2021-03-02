package factory;

import configuration.Configuration;

public class DeIcingSystemFactory {

    public static Object build() {
        return Factory.build("DeIcingSystemFactory", Configuration.instance.pathToDeIcingSystemJavaArchive);
    }
}

