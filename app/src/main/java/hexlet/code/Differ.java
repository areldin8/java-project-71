package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        String data1 = getFile(filepath1);
        String data2 = getFile(filepath2);

        String fileType1 = getFileType(filepath1);
        String fileType2 = getFileType(filepath2);

        Map<String, Object> map1 = Parser.parse(data1, fileType1);
        Map<String, Object> map2 = Parser.parse(data2, fileType2);

        List<Map<String, Object>> result = Comparator.compareFiles(map1, map2);

        return Formatter.formatStyle(result, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String getFile(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        return Files.readString(path);
    }

    public static String getFileType(String filepath) {
        int dotIndex = filepath.lastIndexOf(".");
        if (dotIndex == -1) {
            throw new IllegalArgumentException("File does not have an extension: " + filepath);
        }
        return filepath.substring(dotIndex + 1);
    }

}




