package com.alexanderschreyer.reciperepo;

import com.alexanderschreyer.reciperepo.io.RecipeRepository;
import com.alexanderschreyer.reciperepo.model.Ingredient;
import com.alexanderschreyer.reciperepo.model.Recipe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class RecipeRepoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeRepoApplication.class, args);
    }
}