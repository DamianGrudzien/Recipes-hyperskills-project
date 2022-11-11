package recipes.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import recipes.model.Recipe;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class RecipeResponse {
    String name;
    String description;
    List<String> ingredients;
    List<String> directions;

    public static RecipeResponse valueOf(Recipe recipe) {
        return RecipeResponse.builder()
                             .name(recipe.getName())
                             .description(recipe.getDescription())
                             .ingredients(recipe.getIngredients())
                             .directions(recipe.getDirections())
                             .build();
    }
}
