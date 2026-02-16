package com.alexanderschreyer.reciperepo.controller;

import java.util.List;

import com.alexanderschreyer.reciperepo.io.RecipeRepository;
import com.alexanderschreyer.reciperepo.model.Recipe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RecipeRepoController {
    List<Recipe> recipes;

    @GetMapping("/")
    public String index(Model model) {
        RecipeRepository rr = new RecipeRepository();
        recipes = rr.getRecipes();
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
}
