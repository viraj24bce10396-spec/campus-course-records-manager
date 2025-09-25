package edu.ccrm.config;

// Singleton Pattern for config
public class AppConfig {
    private static AppConfig instance;
    private String dataFolder;

    private AppConfig() {
        dataFolder = "./data";
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public String getDataFolder() {
        return dataFolder;
    }
}
