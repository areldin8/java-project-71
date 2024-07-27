package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestDiffer {

    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;

    @BeforeAll
    public static void beforeAll() throws Exception {
        expectedStylish = getCorrectResultString("./src/test/resources/fixtures/resultStylish");
        expectedPlain = getCorrectResultString("./src/test/resources/fixtures/resultPlain");
        expectedJson = getCorrectResultString("./src/test/resources/fixtures/resultJson");
    }

    private static String getCorrectResultString(String correctStringPath) throws Exception {
        Path correctStringAbsolutePath = Paths.get(correctStringPath).toAbsolutePath().normalize();
        return Files.readString(correctStringAbsolutePath).trim().replaceAll("\\r", "");
    }

    @ParameterizedTest
    @ValueSource(strings = {"yml", "json"})
    public void testStylish(String fileType) throws Exception {
        String path1 = Paths.get("./src/test/resources/files/file11." + fileType).toString();
        String path2 = Paths.get("./src/test/resources/files/file22." + fileType).toString();
        String generatedResult = Differ.generate(path1, path2, "stylish");
        assertEquals(expectedStylish, generatedResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"yml", "json"})
    public void testPlain(String fileType) throws Exception {
        String path1 = Paths.get("./src/test/resources/files/file11." + fileType).toString();
        String path2 = Paths.get("./src/test/resources/files/file22." + fileType).toString();
        String generatedResult = Differ.generate(path1, path2, "plain");
        assertEquals(expectedPlain, generatedResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"yml", "json"})
    public void testJson(String fileType) throws Exception {
        String path1 = Paths.get("./src/test/resources/files/file11." + fileType).toString();
        String path2 = Paths.get("./src/test/resources/files/file22." + fileType).toString();
        String generatedResult = Differ.generate(path1, path2, "json");
        assertEquals(expectedJson, generatedResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"yml", "json"})
    public void testDefault(String fileType) throws Exception {
        String path1 = Paths.get("./src/test/resources/files/file11." + fileType).toString();
        String path2 = Paths.get("./src/test/resources/files/file22." + fileType).toString();
        String generatedResult = Differ.generate(path1, path2);
        assertEquals(expectedStylish, generatedResult);
    }
}

