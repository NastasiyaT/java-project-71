package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help.ColorScheme;
import picocli.CommandLine.Help.Ansi.Style;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<String> {

    @Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "format"}, description = "output format [default: stylish]", defaultValue = "stylish")
    private String format;

    @Override
    public String call() throws Exception {
        String diff = Differ.generate(filepath1, filepath2);
        System.out.print(Formatter.chooseFormat(format, diff));
        return null;
    }

    public static void main(String[] args) {
        ColorScheme colorScheme = new ColorScheme.Builder()
                .commands()
                .options(Style.fg_blue)
                .parameters(Style.fg_blue)
                .optionParams(Style.italic)
                .errors(Style.fg_red, Style.bold)
                .stackTraces(Style.italic)
                .applySystemProperties()
                .build();

        new CommandLine(new App())
                .setColorScheme(colorScheme)
                .execute(args);
    }
}
