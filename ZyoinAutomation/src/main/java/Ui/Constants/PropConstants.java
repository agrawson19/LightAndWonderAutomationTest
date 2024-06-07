package Ui.Constants;

public class PropConstants {
    private final static String CONFIGPROP = System.getProperty("user.dir")
            + "/src/main/resources/Configuration.Properties";
    public static String getConfigPropPath() {
        return CONFIGPROP;
    }

}
