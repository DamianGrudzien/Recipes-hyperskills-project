package recipes.model.request;

import lombok.Value;
import org.hibernate.validator.constraints.Length;
import recipes.validation.ExtendedEmailValidator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
public class UserRequest {
    @ExtendedEmailValidator
    @NotNull
    String email;

    @Length(min = 8)
    @NotBlank @NotNull @NotEmpty
    String password;
}
