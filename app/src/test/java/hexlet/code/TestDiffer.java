package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestDiffer {

    private static String getCorrectResultString(String correctStringPath) throws Exception {
        Path correctStringAbsolutePath = Paths.get(correctStringPath).toAbsolutePath().normalize();
        return Files.readString(correctStringAbsolutePath).trim().replaceAll("\\r", "");
    }

    @Test
    public void testStylish() throws Exception {
        final String path1 = "./src/test/resources/files/file11.json";
        final String path2 = "./src/test/resources/files/file22.json";
        String pathResult = "./src/test/resources/fixtures/resultStylish";
        String generatedResult = Differ.generate(path1, path2, "stylish");
        var correctResult = getCorrectResultString(pathResult);
        assertEquals(correctResult, generatedResult, "Stylish format test failed");
    }


    @Test
    public void testPlain() throws Exception {
        final String path1 = "./src/test/resources/files/file11.yml";
        final String path2 = "./src/test/resources/files/file22.yml";
        String pathResult = "./src/test/resources/fixtures/resultPlain";
        String generatedResult = Differ.generate(path1, path2, "plain");
        var correctResult = getCorrectResultString(pathResult);
        assertEquals(correctResult, generatedResult, "Plain format test failed");
    }

    @Test
    public void testJson() throws Exception {
        final String path1 = "./src/test/resources/files/file11.yml";
        final String path2 = "./src/test/resources/files/file22.yml";
        String pathResult = "./src/test/resources/fixtures/resultJson";
        String generatedResult = Differ.generate(path1, path2, "json");
        var correctResult = getCorrectResultString(pathResult);
        assertEquals(correctResult, generatedResult, "Json format test failed");
    }

    @Test
    public void testCorrect() throws Exception {
        final String path1 = "./src/test/resources/files/file1.json";
        final String path2 = "./src/test/resources/files/file2.json";
        String pathResult = "./src/test/resources/fixtures/resultCorrect";
        String generatedResult = Differ.generate(path1, path2);
        var correctResult = getCorrectResultString(pathResult);
        assertEquals(correctResult, generatedResult);
    }

    @Test
    public void testFunctional() throws Exception {
        final String path1 = "./src/test/resources/files/file1.yml";
        final String path2 = "./src/test/resources/files/file2.yml";
        String pathResult = "./src/test/resources/fixtures/resultFunctional";
        String generatedResult = Differ.generate(path1, path2);
        var correctResult = getCorrectResultString(pathResult);
        assertEquals(correctResult, generatedResult);
    }
}

