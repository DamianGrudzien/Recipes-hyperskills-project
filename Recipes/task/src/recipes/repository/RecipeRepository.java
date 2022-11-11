package recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipes.model.Recipe;

import java.util.*;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    Optional<Recipe> findRecipeById(Long id);
    void deleteById(Long id);

    boolean existsById(Long id);

}
