package recipes.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

@Slf4j
@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

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

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity handleException(RuntimeException ex){
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


}
