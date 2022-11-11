package recipes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundRecipeException extends ResponseStatusException {
    public NotFoundRecipeException() {
        super(HttpStatus.NOT_FOUND);
    }
}
