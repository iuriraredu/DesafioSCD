package br.com.iuriraredu.config;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe utilitária para carregar e fornecer as configurações da API a partir do arquivo config.properties.
 * Os métodos estáticos permitem acessar endpoints e credenciais de teste de forma centralizada.
 */
@Getter
public abstract class ApiConfig {
    private static final Properties properties = new Properties();

    // Bloco estático para carregar o arquivo de propriedades ao iniciar a classe
    static {
        try (InputStream input = ApiConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties não encontrado");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o config.properties", e);
        }
    }

    /**
     * Retorna a URL base da API.
     * @return String com a base URL.
     */
    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    /**
     * Retorna o endpoint de autenticação completo.
     * @return String com o endpoint de autenticação.
     */
    public static String getAuthEndpoint() {
        return getBaseUrl() + properties.getProperty("auth.endpoint");
    }

    /**
     * Retorna o endpoint de produtos completo.
     * @return String com o endpoint de produtos.
     */
    public static String getProductsEndpoint() {
        return getBaseUrl() + properties.getProperty("products.endpoint");
    }

    /**
     * Retorna o endpoint de usuários completo.
     * @return String com o endpoint de usuários.
     */
    public static String getUsersEndpoint() {
        return getBaseUrl() + properties.getProperty("users.endpoint");
    }

    /**
     * Retorna o endpoint de teste completo.
     * @return String com o endpoint de teste.
     */
    public static String getTestEndpoint() {
        return getBaseUrl() + properties.getProperty("test.endpoint");
    }

    /**
     * Retorna o nome de usuário válido para autenticação.
     * @return String com o nome de usuário válido.
     */
    public static String getValidUsername() {
        return properties.getProperty("valid.username");
    }

    /**
     * Retorna a senha válida para autenticação.
     * @return String com a senha válida.
     */
    public static String getValidPassword() {
        return properties.getProperty("valid.password");
    }

    /**
     * Retorna um nome de usuário inválido para testes negativos.
     * @return String com o nome de usuário inválido.
     */
    public static String getInvalidUsername() {
        return properties.getProperty("invalid.username");
    }

    /**
     * Retorna uma senha inválida para testes negativos.
     * @return String com a senha inválida.
     */
    public static String getInvalidPassword() {
        return properties.getProperty("invalid.password");
    }

    /**
     * Retorna o ID de um produto existente para testes.
     * @return String com o ID do produto existente.
     */
    public static String getExistingProductId(){
        return properties.getProperty("existing.product.id");
    }

    /**
     * Retorna o ID de um produto inexistente para testes negativos.
     * @return String com o ID do produto inexistente.
     */
    public static String getNonExistingProductId(){
        return properties.getProperty("non.existing.product.id");
    }

    /**
     * Retorna o nome do relatório de testes.
     * @return String com o nome do relatório.
     */
    public static String getReportName(){
        return properties.getProperty("report.name");
    }

    /**
     * Retorna o caminho onde o relatório de testes será salvo.
     * @return String com o caminho do relatório.
     */
    public static String getReportPath(){
        return properties.getProperty("report.path");
    }
}