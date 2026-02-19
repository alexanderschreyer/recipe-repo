package com.alexanderschreyer.reciperepo.io;

import com.alexanderschreyer.reciperepo.io.IngredientParser;
import com.alexanderschreyer.reciperepo.model.Ingredient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IngredientParserTest {

    @Test
    public void parseIngredients() {
        String input = "Red Lentils 150 g;" +
                "Coconut Milk 150 ml;" +
                "White Rice 200 g;" +
                "Water 300 ml;";

        IngredientParser ingredientParser = new IngredientParser();
        List<Ingredient> ingredients = ingredientParser.parseIngredients(input);

        assertTrue(ingredients.get(0).getName().equals("Red Lentils"));
        assertTrue(ingredients.get(1).getName().equals("Coconut Milk"));
        assertTrue(ingredients.get(2).getName().equals("White Rice"));
        assertTrue(ingredients.get(3).getName().equals("Water"));

        assertTrue(ingredients.get(0).getQuantity() == 150);
        assertTrue(ingredients.get(1).getQuantity() == 150);
        assertTrue(ingredients.get(2).getQuantity() == 200);
        assertTrue(ingredients.get(3).getQuantity() == 300);

        assertTrue(ingredients.get(0).getUnit().equals("g"));
        assertTrue(ingredients.get(1).getUnit().equals("ml"));
        assertTrue(ingredients.get(2).getUnit().equals("g"));
        assertTrue(ingredients.get(3).getUnit().equals("ml"));
    }
}
