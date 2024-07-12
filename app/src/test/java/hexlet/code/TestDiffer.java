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
    public void testStylish1() throws Exception {
        final String path1 = "./src/test/resources/files/file11.json";
        final String path2 = "./src/test/resources/files/file22.json";
        String pathResult = "./src/test/resources/fixtures/resultDefault";
        String generatedResult = Differ.generate(path1, path2, "stylish");
        var correctResult = getCorrectResultString(pathResult);
        assertEquals(correctResult, generatedResult);
    }

    @Test
    public void testStylish2() throws Exception {
        final String path1 = "./src/test/resources/files/file11.yml";
        final String path2 = "./src/test/resources/files/file22.yml";
        String pathResult = "./src/test/resources/fixtures/resultDefault";
        String generatedResult = Differ.generate(path1, path2, "stylish");
        var correctResult = getCorrectResultString(pathResult);
        assertEquals(correctResult, generatedResult);
    }


    @Test
    public void testPlain1() throws Exception {
        final String path1 = "./src/test/resources/files/file11.yml";
        final String path2 = "./src/test/resources/files/file22.yml";
        String pathResult = "./src/test/resources/fixtures/resultPlain";
        String generatedResult = Differ.generate(path1, path2, "plain");
        var correctResult = getCorrectResultString(pathResult);
        assertEquals(correctResult, generatedResult);
    }

    @Test
    public void testPlain2() throws Exception {
        final String path1 = "./src/test/resources/files/file11.json";
        final String path2 = "./src/test/resources/files/file22.json";
        String pathResult = "./src/test/resources/fixtures/resultPlain";
        String generatedResult = Differ.generate(path1, path2, "plain");
        var correctResult = getCorrectResultString(pathResult);
        assertEquals(correctResult, generatedResult);
    }

    @Test
    public void testJson1() throws Exception {
        final String path1 = "./src/test/resources/files/file11.yml";
        final String path2 = "./src/test/resources/files/file22.yml";
        String pathResult = "./src/test/resources/fixtures/resultJson";
        String generatedResult = Differ.generate(path1, path2, "json");
        var correctResult = getCorrectResultString(pathResult);
        assertEquals(correctResult, generatedResult);
    }

    @Test
    public void testJson2() throws Exception {
        final String path1 = "./src/test/resources/files/file11.json";
        final String path2 = "./src/test/resources/files/file22.json";
        String pathResult = "./src/test/resources/fixtures/resultJson";
        String generatedResult = Differ.generate(path1, path2, "json");
        var correctResult = getCorrectResultString(pathResult);
        assertEquals(correctResult, generatedResult);
    }

    @Test
    public void testDefault1() throws Exception {
        final String path1 = "./src/test/resources/files/file11.yml";
        final String path2 = "./src/test/resources/files/file22.yml";
        String pathResult = "./src/test/resources/fixtures/resultDefault";
        String generatedResult = Differ.generate(path1, path2);
        var correctResult = getCorrectResultString(pathResult);
        assertEquals(correctResult, generatedResult);
    }

    @Test
    public void testDefault2() throws Exception {
        final String path1 = "./src/test/resources/files/file11.json";
        final String path2 = "./src/test/resources/files/file22.json";
        String pathResult = "./src/test/resources/fixtures/resultDefault";
        String generatedResult = Differ.generate(path1, path2);
        var correctResult = getCorrectResultString(pathResult);
        assertEquals(correctResult, generatedResult);
    }
}

