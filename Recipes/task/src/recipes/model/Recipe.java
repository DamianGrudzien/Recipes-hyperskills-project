package recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Recipe {
    Long id;
    String name;
    String description;
    List<String> ingredients;
    List<String> directions;
}
