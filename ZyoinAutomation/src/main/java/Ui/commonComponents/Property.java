package Ui.commonComponents;

import Ui.Constants.PropConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Property {
    private static Map<String, String> map = new HashMap<>();
    private static Properties property = new Properties();
    static {
        try {
            FileInputStream file = new FileInputStream(PropConstants.getConfigPropPath());
            property.load(file);

            for (Map.Entry<Object, Object> entry : property.entrySet()) {
                map.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String  Key) {
        return map.get(Key);
    }
}
