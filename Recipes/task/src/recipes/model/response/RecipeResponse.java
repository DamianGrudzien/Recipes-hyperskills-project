package recipes.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import recipes.model.Recipe;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class RecipeResponse {
    String name;
    String description;
    String category;
    LocalDateTime date;
    List<String> ingredients;
    List<String> directions;

    public static RecipeResponse valueOf(Recipe recipe) {
        return RecipeResponse.builder()
                             .name(recipe.getName())
                             .category(recipe.getCategory())
                             .date(recipe.getDate())
                             .description(recipe.getDescription())
                             .ingredients(recipe.getIngredients())
                             .directions(recipe.getDirections())
                             .build();
    }
}
