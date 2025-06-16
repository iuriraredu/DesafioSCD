package br.com.iuriraredu.config;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public abstract class PropertiesConfig {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = PropertiesConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties não encontrado");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o config.properties", e);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static String getAuthEndpoint() {
        return getBaseUrl() + properties.getProperty("auth.endpoint") + "/login";
    }

    public static String getAuthProductsEndpoint() {
        return getBaseUrl() + properties.getProperty("auth.endpoint") + properties.getProperty("products.endpoint");
    }

    public static String getProductsEndpoint() {
        return getBaseUrl() + properties.getProperty("products.endpoint");
    }

    public static String getAddProductsEndpoint() {
        return getBaseUrl() + properties.getProperty("products.endpoint")  + "/add";
    }

    public static String getUsersEndpoint() {
        return getBaseUrl() + properties.getProperty("users.endpoint");
    }

    public static String getTestEndpoint() {
        return getBaseUrl() + properties.getProperty("test.endpoint");
    }

    public static String getValidUsername() {
        return properties.getProperty("valid.username");
    }

    public static String getValidPassword() {
        return properties.getProperty("valid.password");
    }

    public static String getInvalidUsername() {
        return properties.getProperty("invalid.username");
    }

    public static String getInvalidPassword() {
        return properties.getProperty("invalid.password");
    }

    public static String getExistingProductId(){
        return properties.getProperty("existing.product.id");
    }

    public static String getNonExistingProductId(){
        return properties.getProperty("non.existing.product.id");
    }

    public static String getReporName(){
        return properties.getProperty("report.name");
    }

    public static String getReporPath(){
        return properties.getProperty("report.path");
    }
}
