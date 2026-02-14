package com.alexanderschreyer.reciperepo.controller;

import java.util.List;

import com.alexanderschreyer.reciperepo.io.RecipeRepository;
import com.alexanderschreyer.reciperepo.model.Recipe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeRepoController {

    @GetMapping("/")
    public String index(Model model) {
        List<Recipe> recipes = getRecipes();
        model.addAttribute("recipes", recipes);
        return "index";
    }

    private List<Recipe> getRecipes() {
        RecipeRepository rr = new RecipeRepository();
        return rr.getRecipes();
    }
}
