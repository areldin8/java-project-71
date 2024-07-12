package hexlet.code;

import java.io.IOException;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Parser {
    public static TreeMap<String, Object> parse(String fileData, String fileType) throws IOException {
        ObjectMapper objectMapper = chooseFormat(fileType);
        return objectMapper.readValue(fileData, new TypeReference<>() {
        });
    }

    private static ObjectMapper chooseFormat(String fileType) {
        switch (fileType.toLowerCase()) {
            case "json":
                return new ObjectMapper();
            case "yml":
            case "yaml":
                return new ObjectMapper(new YAMLFactory());
            default:
                throw new IllegalArgumentException("Unsupported file type: " + fileType);
        }
    }
}


