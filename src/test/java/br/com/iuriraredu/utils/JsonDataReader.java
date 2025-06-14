package br.com.iuriraredu.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class JsonDataReader {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T readTestData(String filePath, String dataKey, Class<T> valueType) throws IOException {
        InputStream inputStream = JsonDataReader.class.getClassLoader().getResourceAsStream(filePath);
        Map<String, Object> dataMap = mapper.readValue(inputStream, new TypeReference<>() {});
        return mapper.convertValue(dataMap.get(dataKey), valueType);
    }

    public static <T> T readTestData(String filePath, TypeReference<T> typeReference) throws IOException {
        InputStream inputStream = JsonDataReader.class.getClassLoader().getResourceAsStream(filePath);
        return mapper.readValue(inputStream, typeReference);
    }
}