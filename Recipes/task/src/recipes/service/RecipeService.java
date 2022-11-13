package recipes.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import recipes.model.Recipe;
import recipes.model.request.RecipeRequest;
import recipes.model.response.IdResponse;
import recipes.model.response.RecipeResponse;
import recipes.repository.RecipeRepository;
import recipes.util.MapperRecipe;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<RecipeResponse> updateRecipeById(Long id, RecipeRequest recipeRequest) {
        if (recipeRepository.existsById(id)) {
            Recipe recipe = recipeRepository.getById(id);
            MapperRecipe.updateRecipe(recipe, recipeRequest);
            recipeRepository.save(recipe);
            return Optional.of(RecipeResponse.valueOf(recipe));
        } else {
            return Optional.empty();
        }
    }

    public List<RecipeResponse> getRecipesBy(String search, String searchValue) {
        if (search.equals("category")) {
            List<Recipe> categoryRecipes = recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(searchValue);
            return categoryRecipes.stream()
                                  .map(RecipeResponse::valueOf)
                                  .collect(Collectors.toList());
        } else {
            List<Recipe> nameRecipes = recipeRepository.findByNameContainsIgnoreCaseOrderByDateDesc(searchValue);
            return nameRecipes.stream()
                              .map(RecipeResponse::valueOf)
                              .collect(Collectors.toList());
        }
    }
}
