package com.central.movies.centralmovies.scraping.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {

    private static Properties properties = PropertiesFileReader.findProperties();

    private static Properties findProperties() {
        String systemPath = System.getProperty("user.dir");
        String propertiesFilePath = systemPath + "/config.properties";

        Properties properties = new Properties();

        try (InputStream stream = new FileInputStream(propertiesFilePath)) {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String getProperty(String property) {
        return properties.getProperty(property);
    }
}