package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help.ColorScheme;
import picocli.CommandLine.Help.Ansi.Style;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App {

    public static void greeting() {
        System.out.println("Hello World!");
    }

    public static void main(String[] args) {
        ColorScheme colorScheme = new ColorScheme.Builder()
                .commands    ()
                .options     (Style.fg_blue)
                .parameters  (Style.fg_blue)
                .optionParams(Style.italic)
                .errors      (Style.fg_red, Style.bold)
                .stackTraces (Style.italic)
                .applySystemProperties()
                .build();

        new CommandLine(new App())
                .setColorScheme(colorScheme) // use this color scheme in the usage help message
                .execute(args);
    }
}
