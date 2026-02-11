package io;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JSONParser {

    public HashMap<String, Object> readFromJSON(String filePathStr) {
        HashMap<String, Object> output = new HashMap<>();
        String jsonContent;
        try {
            jsonContent = Files.readString(Path.of(new URI("file:///" + filePathStr)));
            String[] parts = jsonContent.split(",");
            List<String> partsClean = new ArrayList<>();
            for (String part : parts) {
                String clean = part.replace("{", "");
                String cleaner = clean.replace("}", "");
                String cleanest = cleaner.replace("\n", "");
                partsClean.add(cleanest);
            }
            for (String partClean : partsClean) {
                String[] parts2 = partClean.split(":");
                List<String> parts2Clean = new ArrayList<>();
                for (String part2 : parts2) {
                    part2 = part2.replace("\"", "");
                    System.out.println(part2);
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return output;
    }
}