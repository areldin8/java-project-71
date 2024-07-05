package hexlet.code;

import java.io.IOException;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Parser {
    public static TreeMap<String, Object> parse(String fileData, String fileType) throws IOException {
        ObjectMapper objectmapper = chooseFormat(fileType);
        return objectmapper.readValue(fileData, new TypeReference<>() { });
    }

    private static ObjectMapper chooseFormat(String fileType) {
        return "json".equals(fileType) ? new ObjectMapper() : new ObjectMapper(new YAMLFactory());
    }
}

