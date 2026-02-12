package io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import model.Recipe;

import tools.jackson.databind.ObjectMapper;

public class RecipeReader {
    private final String dirPathStr;

    public RecipeReader(String filePathStr) {
        this.dirPathStr = filePathStr;
    }
    
    public List<Recipe> readRecipesFromJSON() {
        List<Recipe> output = new ArrayList<>();
        Path dirPath = null;

        // Create directory path from directory path string
        try {
            dirPath = Path.of(new URI("file:///" + dirPathStr));
        } catch (URISyntaxException e) {
            System.err.println("ERROR: Malformed URI");
            // TODO: LOGGING
        }

        // Iterate over every file in the directory and map to a recipe object
        if (dirPath != null) {
            try {
                List<Path> files = Files.list(dirPath).toList();
                for (Path file : files) {
                    InputStream src = Files.newInputStream(file);
                    ObjectMapper mapper = new ObjectMapper();
                    Recipe recipe = mapper.readValue(src, Recipe.class);
                    output.add(recipe);
                }
            } catch (IOException e) {
                System.err.println("ERROR: Files could not be fetched");
                // TODO: LOGGING
            }
        } else {
            System.err.println("ERROR: dirPath was null");
            // TODO: LOGGING
        }

        return output;
    }
}
