package com.alexanderschreyer.reciperepo.controller;

import java.util.Arrays;
import java.util.List;

import com.alexanderschreyer.reciperepo.io.RecipeRepository;
import com.alexanderschreyer.reciperepo.model.Ingredient;
import com.alexanderschreyer.reciperepo.model.Recipe;

import com.alexanderschreyer.reciperepo.io.IngredientParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeRepoController {
    private RecipeRepository recipeRepository;
    private List<Recipe> recipes;

    @GetMapping("/")
    public String index(Model model) {
        recipeRepository = new RecipeRepository();
        recipes = recipeRepository.getRecipes();
        model.addAttribute("recipes", recipes);
        return "index";
    }

    @GetMapping("/recipes/{id}")
    public String recipe(@PathVariable String id, Model model) {
        for (Recipe recipe : recipes) {
            if (recipe.getId().equals(id)) {
                model.addAttribute("recipe", recipe);
            }
        }
        return "recipe";
    }

    @GetMapping("/new-recipe")
    public String form() {
        return "form";
    }

    @PostMapping("/new-recipe")
    public String saveRecipe(@RequestParam String id,
                             @RequestParam String name,
                             @RequestParam String description,
                             @RequestParam String ingredients,
                             @RequestParam String steps) {
        List<Ingredient> ingList = new IngredientParser().parseIngredients(ingredients);
        List<String> stepList = Arrays.stream(steps.split(";")).toList();
        recipeRepository.writeRecipeToJSON(new Recipe(id, name, description, ingList, stepList));
        return "redirect:/";
    }
}
