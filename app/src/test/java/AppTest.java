import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class AppTest {
    private static String stylish;
    private static String plain;
    private static String json;


    @BeforeAll
    public static void getResults() throws IOException {
        Function<String, Path> getPath = k -> Paths.get(k).toAbsolutePath().normalize();

        stylish = Files.readString(getPath.apply("src/test/resources/ResultStylish.txt"));
        plain = Files.readString(getPath.apply("src/test/resources/ResultPlain.txt"));
        json = Files.readString(getPath.apply("src/test/resources/ResultJson.txt"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    void testStylish(String suffix) throws Exception {
        String expected = stylish;
        String path1 = "src/test/resources/TestFile1." + suffix;
        String path2 = "src/test/resources/TestFile2." + suffix;
        String result = Differ.generate(path1, path2, "stylish");
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    void testPlain(String suffix) throws Exception {
        String expected = plain;
        String path1 = "src/test/resources/TestFile1." + suffix;
        String path2 = "src/test/resources/TestFile2." + suffix;
        String result = Differ.generate(path1, path2, "plain");
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    void testJson(String suffix) throws Exception {
        String expected = json;
        String path1 = "src/test/resources/TestFile1." + suffix;
        String path2 = "src/test/resources/TestFile2." + suffix;
        String result = Differ.generate(path1, path2, "json");
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    void testDefault(String suffix) throws Exception {
        String expected = stylish;
        String path1 = "src/test/resources/TestFile1." + suffix;
        String path2 = "src/test/resources/TestFile2." + suffix;
        String result = Differ.generate(path1, path2);
        assertEquals(expected, result);
    }
}
