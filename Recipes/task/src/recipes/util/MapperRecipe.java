package recipes.util;

import recipes.model.Recipe;
import recipes.model.request.RecipeRequest;

import java.time.LocalDateTime;

public class MapperRecipe {
    private MapperRecipe(){}
    public static void updateRecipe(Recipe recipeDB,RecipeRequest recipeRequest) {
        recipeDB.setName(recipeRequest.getName());
        recipeDB.setDescription(recipeRequest.getDescription());
        recipeDB.setCategory(recipeDB.getCategory());
        recipeDB.setDate(LocalDateTime.now());
        recipeDB.setIngredients(recipeRequest.getIngredients());
        recipeDB.setDirections(recipeRequest.getDirections());
    }
}
