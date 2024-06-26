package dat3.recipe.api;

import dat3.recipe.dto.RecipeDto;
import dat3.recipe.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

  private RecipeService recipeService;

  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @GetMapping
  public List<RecipeDto> getAllRecipes(@RequestParam(required = false) String category) {
    if(category != null) {
      System.out.println("Category: " + category);
    }
    return recipeService.getAllRecipes(category);
  }

  @GetMapping(path ="/{id}")
  public RecipeDto getRecipeById(@PathVariable int id) {
    return recipeService.getRecipeById(id);
  }

  @PostMapping
  public RecipeDto addRecipe(@RequestBody RecipeDto request, Principal p) {
    request.setOwner(p.getName()); //If someone tried to sneak in a name other than themself, it will be overwritten here
    return recipeService.addRecipe(request);
  }

  @PutMapping(path = "/{id}")
  public RecipeDto editRecipe(@RequestBody RecipeDto request,@PathVariable int id) {
    return recipeService.editRecipe(request,id);
  }

  @DeleteMapping(path="/{id}")
  public void deleteRecipe(@PathVariable int id){
    recipeService.deleteRecipe(id);
  }

}
