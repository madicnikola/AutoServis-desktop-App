package fon.ai.np.mvnautoservisserver.util;

import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author Nikola
 */
public class SettingsLoader {

    private static SettingsLoader instance;
    private Properties properties;

    private SettingsLoader() {
        loadProperties();
    }

    public static SettingsLoader getInstance() {
        if (instance == null) {
            instance = new SettingsLoader();
        }
        return instance;
    }

    public void loadProperties() {
        try {
            FileInputStream fis = new FileInputStream("settings.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (Exception ex) {
            System.out.println("Greska kod ucitavanja podesavanja");
        }

    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }

}
