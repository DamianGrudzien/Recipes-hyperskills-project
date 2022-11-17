package recipes.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.model.Recipe;
import recipes.model.request.RecipeRequest;
import recipes.model.response.IdResponse;
import recipes.model.response.RecipeResponse;
import recipes.repository.RecipeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeService {

    RecipeRepository recipeRepository;
    ModelMapper modelMapper;

    public IdResponse saveRecipe(RecipeRequest recipeRequest) {
        String currentUser = UserService.getCurrentUser();
        Recipe recipe = modelMapper.map(recipeRequest, Recipe.class);
        recipe.setAuthor(currentUser);
        recipe.setDate(LocalDateTime.now());
        Recipe recipeSaved = recipeRepository.save(recipe);
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
        Optional<Recipe> recipeOptional = recipeRepository.findRecipeById(id);
        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            if (!recipe.getAuthor().equals(UserService.getCurrentUser())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        }
        recipeRepository.deleteById(id);
        return recipeRepository.existsById(id);
    }

    public Optional<RecipeResponse> updateRecipeById(Long id, RecipeRequest recipeRequest) {
        if (recipeRepository.existsById(id)) {
            Recipe recipe = recipeRepository.getById(id);
            if (recipe.getAuthor().equals(UserService.getCurrentUser())) {
                Recipe mappedRecipe = modelMapper.map(recipeRequest, Recipe.class);
                mappedRecipe.setDate(recipe.getDate());
                mappedRecipe.setId(id);
                mappedRecipe.setAuthor(recipe.getAuthor());
                recipeRepository.save(mappedRecipe);
                return Optional.of(RecipeResponse.valueOf(recipe));
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
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
