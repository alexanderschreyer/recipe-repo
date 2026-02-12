package io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.Recipe;

import tools.jackson.databind.ObjectMapper;

public class RecipeRepository {
    private final Path dir = Path.of("data");
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RecipeRepository() {
        // TODO: READ PATH FROM APPLICATION PROPERTIES (?)
    }

    private void checkDirectory() {
        if (!Files.exists(dir)) {
            try {
                Files.createDirectory(dir);
            } catch (IOException e) {
                // TODO: LOGGING
                throw new RuntimeException("Data directory could not be created.");
            }
        }
    }

    public void writeRecipeToJSON(Recipe recipe) {
        checkDirectory();
        objectMapper.writeValue(new File(dir + "/" + recipe.getId() + ".json"), recipe);
    }
    
    public List<Recipe> readRecipesFromJSON() {
        checkDirectory();

        List<Recipe> output = new ArrayList<>();

        // Iterate over every file in the directory and map to a recipe object
        if (dir != null) {
            try (Stream<Path> files = Files.list(dir)) {
                List<Path> fileList = files.toList();
                for (Path file : fileList) {
                    InputStream src = Files.newInputStream(file);
                    Recipe recipe = objectMapper.readValue(src, Recipe.class);
                    output.add(recipe);
                }
            } catch (IOException e) {
                System.err.println("ERROR");
                // TODO: LOGGING
            }
        } else {
            System.err.println("ERROR");
            // TODO: LOGGING
        }

        return output;
    }
}
