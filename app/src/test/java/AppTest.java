import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
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

    @Test
    public void testJsonStylish() throws Exception {
        String expected1 = stylish;
        String result1 = Differ.generate("src/test/resources/TestFile1.json",
                "src/test/resources/TestFile2.json", "stylish");
        assertEquals(expected1, result1);
    }

    @Test
    public void testJsonPlain() throws Exception {
        String expected2 = plain;
        String result2 = Differ.generate("src/test/resources/TestFile1.json",
                "src/test/resources/TestFile2.json", "plain");
        assertEquals(expected2, result2);
    }

    @Test
    public void testJsonJson() throws Exception {
        String expected3 = json;
        String result3 = Differ.generate("src/test/resources/TestFile1.json",
                "src/test/resources/TestFile2.json", "json");
        assertEquals(expected3, result3);
    }

    @Test
    public void testJsonDefault() throws Exception {
        String expected4 = stylish;
        String result4 = Differ.generate("src/test/resources/TestFile1.json",
                "src/test/resources/TestFile2.json");
        assertEquals(expected4, result4);
    }

    @Test
    public void testYamlStylish() throws Exception {
        String expected1 = stylish;
        String result1 = Differ.generate("src/test/resources/TestFile5.yml",
                "src/test/resources/TestFile6.yml", "stylish");
        assertEquals(expected1, result1);
    }

    @Test
    public void testYamlPlain() throws Exception {
        String expected2 = plain;
        String result2 = Differ.generate("src/test/resources/TestFile5.yml",
                "src/test/resources/TestFile6.yml", "plain");
        assertEquals(expected2, result2);
    }

    @Test
    public void testYamlJson() throws Exception {
        String expected3 = json;
        String result3 = Differ.generate("src/test/resources/TestFile5.yml",
                "src/test/resources/TestFile6.yml", "json");
        assertEquals(expected3, result3);
    }

    @Test
    public void testYamlDefault() throws Exception {
        String expected4 = stylish;
        String result4 = Differ.generate("src/test/resources/TestFile5.yml",
                "src/test/resources/TestFile6.yml");
        assertEquals(expected4, result4);
    }
}
