package src.model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String id;
    private String name;
    private String description;
    
    private int servings;

    private List<Ingredient> ingredients;
    private List<String> steps;

    public Recipe(String id, String name) {
        this.id = id;
        this.name = name;
        this.ingredients = new ArrayList<>();
        this.steps = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        // Find index of ingredient and remove it from the list if it exists
        // this.ingredients.remove(index);
    }

    public void addStep(String step) {
        this.steps.add(step);
    }

    public void removeStep(int index) {
        this.steps.remove(index);
    }
}
