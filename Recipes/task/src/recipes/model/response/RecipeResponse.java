package recipes.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecipeResponse {
    String name;
    String description;
    List<String> ingredients;
    List<String> directions;
}
