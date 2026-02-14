package com.alexanderschreyer.reciperepo.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import com.alexanderschreyer.reciperepo.model.Recipe;

import tools.jackson.databind.ObjectMapper;

public class RecipeRepository {
    private final Path dir;
    private final ObjectMapper objectMapper;

    private List<Recipe> recipes;

    public RecipeRepository() {
        // TODO: READ PATH FROM APPLICATION PROPERTIES (?)
        dir = Path.of("data");
        objectMapper = new ObjectMapper();
        recipes = new ArrayList<>();
    }

    public List<Recipe> getRecipes() {
        checkDirectory();
        readRecipesFromJSON();
        sortRecipes();
        return Collections.unmodifiableList(recipes);
    }

    private void checkDirectory() {
        if (!Files.exists(dir)) {
            try {
                Files.createDirectory(dir);
            } catch (IOException e) {
                Logger.logFatal(this, "Data directory could not be created.");
                throw new RuntimeException("FATAL: Data directory could not be created.");
            }
        }
    }
    
    private void readRecipesFromJSON() {

        try (Stream<Path> fileStream = Files.list(dir)) {
            List<Path> files = fileStream.toList();
            for (Path file : files) {
                try (InputStream src = Files.newInputStream(file)) {
                    Recipe recipe = objectMapper.readValue(src, Recipe.class);
                    if (!recipes.contains(recipe)) {
                        recipes.add(recipe);
                    }
                } catch (IOException e) {
                    Logger.logWarning(this, "'" + file + "'" + " was skipped due to an issue." +
                            "Please make sure the file exists and is formatted properly.");
                }
            }
        } catch (IOException e) {
            Logger.logError(this, "One ore more JSON files could not be read.");
        }
        sortRecipes();
    }

    private void sortRecipes() {
        recipes = recipes.stream()
                .sorted(Comparator.comparing(r -> r.getName().toLowerCase()))
                .toList();
    }

    public void writeRecipeToJSON(Recipe recipe) {
        checkDirectory();
        objectMapper.writeValue(new File(dir + "/" + recipe.getId() + ".json"), recipe);
    }
}