package src.io;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class JSONParser {

    public String readFromJSON(String filePathStr) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of(new URI("file:///" + filePathStr)));
            for (String string : lines) {
                System.out.println(string);
            }
            return null;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}