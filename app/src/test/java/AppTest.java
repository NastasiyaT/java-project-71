import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppTest {

    @Test
    public void testParserJson() throws Exception {
        Map<String, Object> expected = Map.of("item", "table",
                "quantity", 4,
                "color", "grey",
                "availability", true);

        Map<String, Object> result = Parser.parseFile("src/test/resources/TestParserJson.json");
        assertEquals(expected, result);
    }

    @Test
    public void testParserYaml() throws Exception {
        Map<String, Object> expected = Map.of("item", "table",
                "quantity", 4,
                "color", "grey",
                "availability", true);

        Map<String, Object> result = Parser.parseFile("src/test/resources/TestParserYaml.yml");
        assertEquals(expected, result);
    }

    @Test
    public void testParserNull() {
        Exception thrown = assertThrows(
                Exception.class,
                () -> Parser.parseFile("src/test/resources/TestParserNull.yml"));

        assertEquals("The file is empty!", thrown.getMessage());
    }

    @Test
    public void testGendiffException() {
        Exception thrown = assertThrows(
                Exception.class,
                () -> Differ.generate("src/test/resources/TestFile1.json",
                "src/test/resources/TestFile3.json"));

        assertEquals("File src/test/resources/TestFile3.json doesn't exist!", thrown.getMessage());
    }

    @Test
    public void testGendiffJson() throws Exception {
        String expected31 = """
                        {
                            chars1: [a, b, c]
                          - chars2: [d, e, f]
                          + chars2: false
                          - checked: false
                          + checked: true
                          - default: null
                          + default: [value1, value2]
                          - id: 45
                          + id: null
                          - key1: value1
                          + key2: value2
                            numbers1: [1, 2, 3, 4]
                          - numbers2: [2, 3, 4, 5]
                          + numbers2: [22, 33, 44, 55]
                          - numbers3: [3, 4, 5]
                          + numbers4: [4, 5, 6]
                          + obj1: {nestedKey=value, isNested=true}
                          - setting1: Some value
                          + setting1: Another value
                          - setting2: 200
                          + setting2: 300
                          - setting3: true
                          + setting3: none
                        }
                        """;
        String result31 = Differ.generate("src/test/resources/TestFile1.json",
                "src/test/resources/TestFile2.json", "stylish");
        assertEquals(expected31, result31);

        String expected32 = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'
                """;
        String result32 = Differ.generate("src/test/resources/TestFile1.json",
                "src/test/resources/TestFile2.json", "plain");
                assertEquals(expected32, result32);

        String expected33 = "[{\"change\":\"same\",\"key\":\"chars1\",\"value\":[\"a\",\"b\",\"c\"]},"
                + "{\"change\":\"no\",\"key\":\"chars2\",\"value\":[\"d\",\"e\",\"f\"]},"
                + "{\"change\":\"yes\",\"key\":\"chars2\",\"value\":false},"
                + "{\"change\":\"no\",\"key\":\"checked\",\"value\":false},"
                + "{\"change\":\"yes\",\"key\":\"checked\",\"value\":true},"
                + "{\"change\":\"no\",\"key\":\"default\",\"value\":null},"
                + "{\"change\":\"yes\",\"key\":\"default\",\"value\":[\"value1\",\"value2\"]},"
                + "{\"change\":\"no\",\"key\":\"id\",\"value\":45},"
                + "{\"change\":\"yes\",\"key\":\"id\",\"value\":null},"
                + "{\"change\":\"no\",\"key\":\"key1\",\"value\":\"value1\"},"
                + "{\"change\":\"yes\",\"key\":\"key2\",\"value\":\"value2\"},"
                + "{\"change\":\"same\",\"key\":\"numbers1\",\"value\":[1,2,3,4]},"
                + "{\"change\":\"no\",\"key\":\"numbers2\",\"value\":[2,3,4,5]},"
                + "{\"change\":\"yes\",\"key\":\"numbers2\",\"value\":[22,33,44,55]},"
                + "{\"change\":\"no\",\"key\":\"numbers3\",\"value\":[3,4,5]},"
                + "{\"change\":\"yes\",\"key\":\"numbers4\",\"value\":[4,5,6]},"
                + "{\"change\":\"yes\",\"key\":\"obj1\",\"value\":{\"nestedKey\":\"value\",\"isNested\":true}},"
                + "{\"change\":\"no\",\"key\":\"setting1\",\"value\":\"Some value\"},"
                + "{\"change\":\"yes\",\"key\":\"setting1\",\"value\":\"Another value\"},"
                + "{\"change\":\"no\",\"key\":\"setting2\",\"value\":200},"
                + "{\"change\":\"yes\",\"key\":\"setting2\",\"value\":300},"
                + "{\"change\":\"no\",\"key\":\"setting3\",\"value\":true},"
                + "{\"change\":\"yes\",\"key\":\"setting3\",\"value\":\"none\"}]\n";
        String result33 = Differ.generate("src/test/resources/TestFile1.json",
                "src/test/resources/TestFile2.json", "json");
        assertEquals(expected33, result33);
    }

    @Test
    public void testGendiffYaml() throws Exception {
        String expected41 = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }
                """;
        String result41 = Differ.generate("src/test/resources/TestFile5.yml",
                "src/test/resources/TestFile6.yml", "stylish");
        assertEquals(expected41, result41);

        String expected42 = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'
                """;

        String result42 = Differ.generate("src/test/resources/TestFile5.yml",
                "src/test/resources/TestFile6.yml", "plain");
        assertEquals(expected42, result42);

        String expected43 = "[{\"change\":\"same\",\"key\":\"chars1\",\"value\":[\"a\",\"b\",\"c\"]},"
                + "{\"change\":\"no\",\"key\":\"chars2\",\"value\":[\"d\",\"e\",\"f\"]},"
                + "{\"change\":\"yes\",\"key\":\"chars2\",\"value\":false},"
                + "{\"change\":\"no\",\"key\":\"checked\",\"value\":false},"
                + "{\"change\":\"yes\",\"key\":\"checked\",\"value\":true},"
                + "{\"change\":\"no\",\"key\":\"default\",\"value\":null},"
                + "{\"change\":\"yes\",\"key\":\"default\",\"value\":[\"value1\",\"value2\"]},"
                + "{\"change\":\"no\",\"key\":\"id\",\"value\":45},"
                + "{\"change\":\"yes\",\"key\":\"id\",\"value\":null},"
                + "{\"change\":\"no\",\"key\":\"key1\",\"value\":\"value1\"},"
                + "{\"change\":\"yes\",\"key\":\"key2\",\"value\":\"value2\"},"
                + "{\"change\":\"same\",\"key\":\"numbers1\",\"value\":[1,2,3,4]},"
                + "{\"change\":\"no\",\"key\":\"numbers2\",\"value\":[2,3,4,5]},"
                + "{\"change\":\"yes\",\"key\":\"numbers2\",\"value\":[22,33,44,55]},"
                + "{\"change\":\"no\",\"key\":\"numbers3\",\"value\":[3,4,5]},"
                + "{\"change\":\"yes\",\"key\":\"numbers4\",\"value\":[4,5,6]},"
                + "{\"change\":\"yes\",\"key\":\"obj1\",\"value\":{\"nestedKey\":\"value\",\"isNested\":true}},"
                + "{\"change\":\"no\",\"key\":\"setting1\",\"value\":\"Some value\"},"
                + "{\"change\":\"yes\",\"key\":\"setting1\",\"value\":\"Another value\"},"
                + "{\"change\":\"no\",\"key\":\"setting2\",\"value\":200},"
                + "{\"change\":\"yes\",\"key\":\"setting2\",\"value\":300},"
                + "{\"change\":\"no\",\"key\":\"setting3\",\"value\":true},"
                + "{\"change\":\"yes\",\"key\":\"setting3\",\"value\":\"none\"}]\n";
        String result43 = Differ.generate("src/test/resources/TestFile5.yml",
                "src/test/resources/TestFile6.yml", "json");
        assertEquals(expected43, result43);
    }

    @Test
    public void testGendiffNoFormat() throws Exception {
        String expected5 = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }
                """;
        String result5 = Differ.generate("src/test/resources/TestFile5.yml",
                "src/test/resources/TestFile6.yml");
        assertEquals(expected5, result5);
    }
}
