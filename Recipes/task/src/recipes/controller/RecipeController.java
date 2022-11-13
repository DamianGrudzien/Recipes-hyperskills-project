package recipes.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.model.request.RecipeRequest;
import recipes.model.response.IdResponse;
import recipes.model.response.RecipeResponse;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class RecipeController {

    RecipeService recipeService;

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<HttpStatus> handleException(RuntimeException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/api/recipe/new")
    IdResponse addRecipe(@Valid @RequestBody RecipeRequest recipeRequest) {
        return recipeService.saveRecipe(recipeRequest);
    }

    @GetMapping("/api/recipe/{id}")
    RecipeResponse getRecipeById(@PathVariable("id") Long id) {
        return recipeService.getRecipeById(id)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/api/recipe/{id}")
    ResponseEntity<HttpStatus> deleteRecipeById(@PathVariable("id") Long id) {
        if (!recipeService.deleteRecipeById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/api/recipe/{id}")
    ResponseEntity<RecipeResponse> updateRecipeById(@PathVariable("id") Long id,@Valid @RequestBody RecipeRequest recipeRequest) {
        RecipeResponse recipeResponse = recipeService.updateRecipeById(id, recipeRequest)
                                                     .orElseThrow(
                                                             () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(recipeResponse,HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/recipe/search")
    @ResponseStatus(HttpStatus.OK)
    List<RecipeResponse> getRecipesByCategoryOrName(@RequestParam(required = false) String category, @RequestParam(required = false) String name) {
        if (category == null && name == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (category != null) {
            return recipeService.getRecipesBy("category", category);
        } else {
            return recipeService.getRecipesBy("name", name);
        }
    }


}
