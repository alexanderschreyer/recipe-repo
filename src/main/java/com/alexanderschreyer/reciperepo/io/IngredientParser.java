package com.alexanderschreyer.reciperepo.io;

import com.alexanderschreyer.reciperepo.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientParser {

    public List<Ingredient> parseIngredients(String ingredients) {
        List<Ingredient> listOfIngredients = new ArrayList<>();
        String[] parts = ingredients.split(";");
        for (String part : parts) {
            int fstInt = findFirstIntInString(part);
            int sndInt = findLastIntInString(part);

            String name = part.substring(0, fstInt - 1);
            int quantity = Integer.parseInt(part.substring(fstInt, sndInt + 1));
            String unit = part.substring(sndInt + 2);

            listOfIngredients.add(new Ingredient(name, quantity, unit));
        }
        return listOfIngredients;
    }

    private int findFirstIntInString(String string) {
        char[] chars = string.toCharArray();
        int i = 0;
        while (i < string.length()) {
            try {
                Integer.parseInt(String.valueOf(chars[i]));
                return i;
            } catch (NumberFormatException e) {
                i++;
            }
        }
        return -1;
    }

    private int findLastIntInString(String string) {
        String reversed = new StringBuilder(string).reverse().toString();
        return string.length() - 1 - findFirstIntInString(reversed);
    }
}
