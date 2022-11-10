package recipes.service;

import org.springframework.stereotype.Service;
import recipes.model.Recipe;

@Service
public class RecipeService {

    Recipe recipe;
    public Recipe addRecipe(Recipe recipe) {
        this.recipe = recipe;
        return recipe;
    }

    public Recipe getRecipe() {
        return this.recipe;
    }
}
