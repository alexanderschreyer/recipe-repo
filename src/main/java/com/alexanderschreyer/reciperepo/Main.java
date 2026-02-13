package com.alexanderschreyer.reciperepo;

import com.alexanderschreyer.reciperepo.io.RecipeRepository;
import com.alexanderschreyer.reciperepo.model.Ingredient;
import com.alexanderschreyer.reciperepo.model.Recipe;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        RecipeRepository rr = new RecipeRepository();
        List<Recipe> rs = rr.readRecipesFromJSON();

        // Print out recipes to check if read operation was successful
        for (Recipe r : rs) {
            System.out.println(r.getName());
            List<Ingredient> is = r.getIngredients();
            for (Ingredient i : is) {
                System.out.println("- " + i.getName() + ", " + i.getQuantity() + " " + i.getUnit());
            }
            System.out.println("\n");
        }

        // Recipe recipe = new Recipe("scrambled-eggs", "Scrambled Eggs");
        // rr.writeRecipeToJSON(recipe);
    }
}