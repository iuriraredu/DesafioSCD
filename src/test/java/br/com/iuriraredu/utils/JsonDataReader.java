package br.com.iuriraredu.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Utilitário para leitura de dados de arquivos JSON para uso em testes.
 * Permite carregar objetos de teste a partir de arquivos localizados em src/test/resources.
 */
public class JsonDataReader {
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Lê um objeto de teste de um arquivo JSON, buscando pelo nome da chave.
     *
     * @param filePath Caminho do arquivo JSON dentro de resources.
     * @param dataKey  Chave do objeto a ser lido.
     * @param valueType Classe do objeto a ser retornado.
     * @param <T> Tipo do objeto de retorno.
     * @return Objeto do tipo informado, populado com os dados do JSON.
     * @throws IOException Se ocorrer erro ao ler o arquivo.
     */
    public static <T> T readTestData(String filePath, String dataKey, Class<T> valueType) throws IOException {
        InputStream inputStream = JsonDataReader.class.getClassLoader().getResourceAsStream(filePath);
        Map<String, Object> dataMap = mapper.readValue(inputStream, new TypeReference<>() {});
        return mapper.convertValue(dataMap.get(dataKey), valueType);
    }

    /**
     * Lê um objeto de teste de um arquivo JSON usando um TypeReference.
     *
     * @param filePath Caminho do arquivo JSON dentro de resources.
     * @param typeReference Referência de tipo para desserialização.
     * @param <T> Tipo do objeto de retorno.
     * @return Objeto do tipo informado, populado com os dados do JSON.
     * @throws IOException Se ocorrer erro ao ler o arquivo.
     */
    public static <T> T readTestData(String filePath, TypeReference<T> typeReference) throws IOException {
        InputStream inputStream = JsonDataReader.class.getClassLoader().getResourceAsStream(filePath);
        return mapper.readValue(inputStream, typeReference);
    }
}