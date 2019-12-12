package guru.springframework.Bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Category;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.enums.Difficulty;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

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
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeRepository.saveAll(getRecipes());
	}

	private List<Recipe> getRecipes() {
				
		List<Recipe> recipes = new ArrayList<Recipe>(2);
		
		//get UOMs
		Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
		if (!eachUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found!");
		}
		Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
		if (!dashUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found!");
		}
		Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
		if (!cupUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found!");
		}
		Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
		if (!tableSpoonUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found!");
		}
		Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		if (!teaSpoonUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found!");
		}
		
		//get optionals
		UnitOfMeasure eachUom = eachUomOptional.get();
		UnitOfMeasure dashUomO = dashUomOptional.get();
		UnitOfMeasure cupUom = cupUomOptional.get();
		UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
		UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
		
		//get categories
		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
		
		if(!americanCategoryOptional.isPresent()) {
			throw new RuntimeException("Expected Category Not Found");
		}
				
		Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
		
		if(!mexicanCategoryOptional.isPresent()) {
			throw new RuntimeException("Expected Category Not Found");
		}
		
		Category americanCategory = americanCategoryOptional.get();
		Category mexicanCategory = mexicanCategoryOptional.get();
		
		Recipe guacRecipe = new Recipe();
		guacRecipe.setDescription("Perfect Guacamole");
		guacRecipe.setPrepTime(10);
		guacRecipe.setCookTime(0);
		guacRecipe.setDiffictulty(Difficulty.EASY);
		guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\r\n" + 
				"\r\n" + 
				"2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\r\n" + 
				"\r\n" + 
				"3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\r\n" + 
				"\r\n" + 
				"Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\r\n" + 
				"\r\n" + 
				"Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\r\n" + 
				"\r\n" + 
				"4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\r\n" + 
				"\r\n" + 
				"Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");
		
		Notes guacNotes = new Notes();
		
		guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\r\n" + 
				"\r\n" + 
				"Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\r\n" + 
				"\r\n" + 
				"The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\r\n" + 
				"\r\n" + 
				"To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\r\n" + 
				"\r\n" + 
				"For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");
		
		guacRecipe.setNotes(guacNotes);
		guacNotes.setRecipe(guacRecipe);
		
		guacRecipe.getIngredients().add(new Ingredient("Ripe Avocados", new BigDecimal(2), eachUom));
		guacRecipe.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal(.5), teaSpoonUom));
		guacRecipe.getIngredients().add(new Ingredient("Fresh lime juice or lemon juice", new BigDecimal(1), tableSpoonUom));
		guacRecipe.getIngredients().add(new Ingredient("Minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom));
		guacRecipe.getIngredients().add(new Ingredient("Serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
		guacRecipe.getIngredients().add(new Ingredient("Cilantro(leaves and tender stems), finely chopped", new BigDecimal(2), tableSpoonUom));
		guacRecipe.getIngredients().add(new Ingredient("Freshly grated black pepper", new BigDecimal(1), dashUomO));
		guacRecipe.getIngredients().add(new Ingredient("Ripe tomato, seeds and pulp removed, chopped", new BigDecimal(2), eachUom));
		
		guacRecipe.getCategories().add(americanCategory);
		guacRecipe.getCategories().add(mexicanCategory);
		
		//add to return list
		recipes.add(guacRecipe);
		
		//Yummy Tacos
		Recipe tacoRecipe = new Recipe();
		
		tacoRecipe.setDescription("Spicy Grilled chicken taco");
		tacoRecipe.setCookTime(9);
		tacoRecipe.setPrepTime(20);
		tacoRecipe.setDiffictulty(Difficulty.MODERATE);
		
		tacoRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\r\n" + 
				"\r\n" + 
				"2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\r\n" + 
				"\r\n" + 
				"Set aside to marinate while the grill heats and you prepare the rest of the toppings.\r\n" + 
				"\r\n" + 
				"Spicy Grilled Chicken Tacos\r\n" + 
				"\r\n" + 
				"3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\r\n" + 
				"\r\n" + 
				"4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\r\n" + 
				"\r\n" + 
				"Wrap warmed tortillas in a tea towel to keep them warm until serving.\r\n" + 
				"\r\n" + 
				"5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
		
		Notes tacoNotes = new Notes();
		
		tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\r\n" + 
				"\r\n" + 
				"Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.");
		
		tacoRecipe.setNotes(tacoNotes);
		tacoNotes.setRecipe(tacoRecipe);
		
		tacoRecipe.getIngredients().add(new Ingredient("Ancho chilli powder", new BigDecimal(2), tableSpoonUom));
		tacoRecipe.getIngredients().add(new Ingredient("Holy water", new BigDecimal(2), cupUom));
		
		tacoRecipe.getCategories().add(mexicanCategory);
		
		recipes.add(tacoRecipe);
		
		System.out.println(guacRecipe);
		
		//return list
		return recipes;
		
	}

}
