package recipes.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import recipes.model.Recipe;
import recipes.model.request.RecipeRequest;
import recipes.model.response.IdResponse;
import recipes.repository.RecipeRepository;
import recipes.model.response.RecipeResponse;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RecipeService {

    RecipeRepository recipeRepository;
    public IdResponse saveRecipe(RecipeRequest recipe) {
        Recipe recipeSaved = recipeRepository.save(RecipeRequest.mapRecipe(recipe));
        return new IdResponse(recipeSaved.getId());
    }

    public Optional<RecipeResponse> getRecipeById(Long id) {
        Optional<Recipe> recipeById = recipeRepository.findRecipeById(id);
        if (recipeById.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(RecipeResponse.valueOf(recipeById.get()));
    }

    public boolean deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
        boolean exists = recipeRepository.existsById(id);
        return exists;
    }
}
