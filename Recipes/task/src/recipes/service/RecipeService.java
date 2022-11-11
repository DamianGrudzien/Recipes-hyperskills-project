package recipes.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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
        IdResponse id = new IdResponse(recipeRepository.save(recipe));
        return id;
    }

    public Optional<RecipeResponse> getRecipeById(Long id) {

        return recipeRepository.getById(id);
    }
}
