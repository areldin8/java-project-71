package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDiffer {

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

    private static Stream<Arguments> provideTestArguments() {
        return Stream.of(
                Arguments.of("./src/test/resources/files/file11.json", "./src/test/resources/files/file22.json", "stylish"),
                Arguments.of("./src/test/resources/files/file11.yml", "./src/test/resources/files/file22.yml", "stylish"),
                Arguments.of("./src/test/resources/files/file11.yml", "./src/test/resources/files/file22.yml", "plain"),
                Arguments.of("./src/test/resources/files/file11.json", "./src/test/resources/files/file22.json", "plain"),
                Arguments.of("./src/test/resources/files/file11.yml", "./src/test/resources/files/file22.yml", "json"),
                Arguments.of("./src/test/resources/files/file11.json", "./src/test/resources/files/file22.json", "json")
        );
    }


    @ParameterizedTest
    @MethodSource("provideTestArguments")
    public void testDiffer(String path1, String path2, String format) throws Exception {
        String generatedResult = Differ.generate(path1, path2, format);
        String correctResult = switch (format) {
            case "stylish" -> expectedStylish;
            case "plain" -> expectedPlain;
            case "json" -> expectedJson;
            default -> throw new IllegalArgumentException("Unknown format: " + format);
        };
        assertEquals(correctResult, generatedResult);
    }

    private static Stream<Arguments> provideDefaultTestArguments() {
        return Stream.of(
                Arguments.of("./src/test/resources/files/file11.yml", "./src/test/resources/files/file22.yml"),
                Arguments.of("./src/test/resources/files/file11.json", "./src/test/resources/files/file22.json")
        );
    }

    @ParameterizedTest
    @MethodSource("provideDefaultTestArguments")
    public void testDefault(String path1, String path2) throws Exception {
        String generatedResult = Differ.generate(path1, path2);
        assertEquals(expectedStylish, generatedResult);
    }
}

