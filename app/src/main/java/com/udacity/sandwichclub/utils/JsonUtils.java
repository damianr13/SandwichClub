package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    private static final String KEY_NAME = "name";
    private static final String KEY_MAIN_NAME = "mainName";
    private static final String KEY_ALTERNATIVE_NAMES = "alsoKnownAs";
    private static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IMAGE_URL = "image";
    private static final String KEY_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        try {
            return buildSandwichFromJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Sandwich buildSandwichFromJson(String json) throws JSONException {
        JSONObject sandwichAsJSON = new JSONObject(json);
        Sandwich result = new Sandwich();

        JSONObject nameObject = sandwichAsJSON.getJSONObject(KEY_NAME);
        result.setMainName(nameObject.getString(KEY_MAIN_NAME));

        JSONArray alternativeNames = nameObject.getJSONArray(KEY_ALTERNATIVE_NAMES);
        ArrayList<String> alternativeNamesList = new ArrayList<>();
        for (int i = 0; i < alternativeNames.length(); i++) {
            alternativeNamesList.add(alternativeNames.getString(i));
        }
        result.setAlsoKnownAs(alternativeNamesList);

        result.setDescription(sandwichAsJSON.getString(KEY_DESCRIPTION));

        JSONArray ingredients = sandwichAsJSON.getJSONArray(KEY_INGREDIENTS);
        ArrayList<String> ingredientsList = new ArrayList<>();
        for (int i = 0; i < ingredients.length(); i++) {
            ingredientsList.add(ingredients.getString(i));
        }
        result.setIngredients(ingredientsList);

        result.setImage(sandwichAsJSON.getString(KEY_IMAGE_URL));
        result.setPlaceOfOrigin(sandwichAsJSON.getString(KEY_PLACE_OF_ORIGIN));

        return result;
    }
}
