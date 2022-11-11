package recipes.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import recipes.exceptions.NotFoundRecipeException;
import recipes.model.ErrorDto;
import recipes.model.request.RecipeRequest;
import recipes.model.response.IdResponse;
import recipes.model.response.RecipeResponse;
import recipes.service.RecipeService;

@Slf4j
@RestController
@AllArgsConstructor
public class RecipeController {

    RecipeService recipeService;

    @PostMapping("/api/recipe/new")
    IdResponse addRecipe(@RequestBody RecipeRequest recipeRequest){
        return recipeService.saveRecipe(recipeRequest);
    }


    @GetMapping("/api/recipe/{id}")
    RecipeResponse getRecipeById(@PathVariable("id") Long id){
        return recipeService.getRecipeById(id).orElseThrow(NotFoundRecipeException::new);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorDto errorHandler(RuntimeException ex){
        return new ErrorDto(ex.getMessage());
    }
}
