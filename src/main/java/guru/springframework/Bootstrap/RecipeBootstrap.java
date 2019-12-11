package guru.springframework.Bootstrap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;

import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;

public class RecipeBootstrap implements CommandLineRunner {

	private RecipeRepository recipeRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;
	private CategoryRepository categoryRepository;
		
	public RecipeBootstrap(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository,
			CategoryRepository categoryRepository) {
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

	}
	
	private List<Recipe> getRecipes() {
				
		List<Recipe> recipes = new ArrayList<Recipe>(2);
		
		//get UOMs
		Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
		if (!eachUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found!");
		}
		Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
		if (!eachUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found!");
		}
		Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
		if (!eachUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found!");
		}
		Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
		if (!eachUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found!");
		}
		Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		if (!eachUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found!");
		}
		
		//get optionals
		UnitOfMeasure eachUom = eachUomOptional.get();
	}

}
