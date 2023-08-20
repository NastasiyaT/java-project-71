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

    private static String path1;
    private static String path2;

    @BeforeAll
    public static void getResults() throws IOException {
        Function<String, Path> getPath = k -> Paths.get(k).toAbsolutePath().normalize();

        stylish = Files.readString(getPath.apply("src/test/resources/ResultStylish.txt"));
        plain = Files.readString(getPath.apply("src/test/resources/ResultPlain.txt"));
        json = Files.readString(getPath.apply("src/test/resources/ResultJson.txt"));

        path1 = getPath.apply("src/test/resources/TestFile1").toString();
        path2 = getPath.apply("src/test/resources/TestFile2").toString();
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    void testStylish(String suffix) throws Exception {
        String expected = stylish;
        String file1 = path1 + suffix;
        String file2 = path2 + suffix;
        String actual = Differ.generate(file1, file2, "stylish");
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    void testPlain(String suffix) throws Exception {
        String expected = plain;
        String file1 = path1 + suffix;
        String file2 = path2 + suffix;
        String actual = Differ.generate(file1, file2, "plain");
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    void testJson(String suffix) throws Exception {
        String expected = json;
        String file1 = path1 + suffix;
        String file2 = path2 + suffix;
        String actual = Differ.generate(file1, file2, "json");
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    void testDefault(String suffix) throws Exception {
        String expected = stylish;
        String file1 = path1 + suffix;
        String file2 = path2 + suffix;
        String actual = Differ.generate(file1, file2);
        assertEquals(expected, actual);
    }
}
