package recipes.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeRequest {
    @NotNull
    @NotBlank
    @NotEmpty
    String name;
    @NotNull
    @NotBlank
    @NotEmpty
    String description;
    @NotNull
    @NotBlank
    @NotEmpty
    String category;
    LocalDateTime date;
    @Size(min = 1)
    @NotNull
    List<String> ingredients;
    @Size(min = 1)
    @NotNull
    List<String> directions;
}
