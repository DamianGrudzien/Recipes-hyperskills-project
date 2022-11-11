package recipes.repository;

import org.springframework.stereotype.Repository;
import recipes.model.Recipe;
import recipes.model.request.RecipeRequest;
import recipes.model.response.RecipeResponse;

import java.util.*;

@Repository
public class RecipeRepository {
    Map<Long,Recipe> recipes = new HashMap<>();
    public Long save(RecipeRequest recipeRequest) {
        Recipe recipe = new Recipe(generatedId(), recipeRequest.getName(), recipeRequest.getDescription() , recipeRequest.getIngredients(),recipeRequest.getDirections());
        recipes.put(recipe.getId(), recipe);
        return recipe.getId();
    }

    public long generatedId(){
        return recipes.size();
    }

    public Optional<RecipeResponse> getById(Long id) {
        Recipe recipe = recipes.get(id);
        if (recipe == null) {
            return Optional.empty();
        }
        RecipeResponse recipeResponse = new RecipeResponse(recipe.getName(), recipe.getDescription(), recipe.getIngredients(), recipe.getDirections());
        return Optional.of(recipeResponse);
    }
}
