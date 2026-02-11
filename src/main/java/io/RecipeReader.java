package io;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import model.Recipe;

public class RecipeReader {
    
    public List<Recipe> readRecipesFromJSON(String filePathStr) {
        String jsonContent;
        try {
            jsonContent = Files.readString(Path.of(new URI("file:///" + filePathStr)));
            String[] parts = jsonContent.split("");
            return null;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
