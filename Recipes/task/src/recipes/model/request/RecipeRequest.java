package recipes.model.request;

import lombok.Value;

import java.util.List;

@Value
public class RecipeRequest {
    String name;
    String description;
    List<String> ingredients;
    List<String> directions;
}
