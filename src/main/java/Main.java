import io.RecipeRepository;
import model.Ingredient;
import model.Recipe;

import java.util.List;

public class Main {
    public static String dirPathStr = "/Users/alexanderschreyer/Documents/08-personal-projects/05-programming/java/recipe-app/src/main/resources/data/";

    public static void main(String[] args) {
        RecipeRepository rr = new RecipeRepository(dirPathStr);
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
    }
}