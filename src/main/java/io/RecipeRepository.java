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
    private final Path dir;
    private final ObjectMapper objectMapper;

    public RecipeRepository() {
        // TODO: READ PATH FROM APPLICATION PROPERTIES (?)
        dir = Path.of("data");
        objectMapper = new ObjectMapper();
    }

    private void checkDirectory() {
        if (!Files.exists(dir)) {
            try {
                Files.createDirectory(dir);
            } catch (IOException e) {
                // TODO: LOGGING
                throw new RuntimeException("ERROR: Data directory could not be created.");
            }
        }
    }
    
    public List<Recipe> readRecipesFromJSON() {
        checkDirectory();
        List<Recipe> recipes = new ArrayList<>();
        try (Stream<Path> fileStream = Files.list(dir)) {
            List<Path> files = fileStream.toList();
            for (Path file : files) {
                InputStream src = Files.newInputStream(file);
                Recipe recipe = objectMapper.readValue(src, Recipe.class);
                recipes.add(recipe);
            }
        } catch (IOException e) {
            System.err.println("ERROR");
            // TODO: LOGGING
        }
        return recipes;
    }

    public void writeRecipeToJSON(Recipe recipe) {
        checkDirectory();
        objectMapper.writeValue(new File(dir + "/" + recipe.getId() + ".json"), recipe);
    }
}
