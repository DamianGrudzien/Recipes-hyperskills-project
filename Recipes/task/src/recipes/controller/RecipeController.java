package recipes.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import recipes.model.ErrorDto;
import recipes.model.Recipe;
import recipes.service.RecipeService;

@Slf4j
@RestController
@AllArgsConstructor
public class RecipeController {

    RecipeService recipeService;

    @PostMapping("/api/recipe")
    @ResponseStatus(HttpStatus.OK)
    Recipe addRecipe(@RequestBody Recipe recipe){
        Recipe recipeResult = recipeService.addRecipe(recipe);
        log.info("{}",recipeResult);
        return recipeResult;
    }


    @GetMapping("/api/recipe")
    Recipe getRecipe(){
        return recipeService.getRecipe();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorDto errorHandler(RuntimeException ex){
        return new ErrorDto(ex.getMessage());
    }
}
