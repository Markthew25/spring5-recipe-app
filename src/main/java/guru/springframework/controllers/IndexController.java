package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jt on 6/1/17.
 */
@Slf4j
@Controller
public class IndexController {
	
	public final RecipeService recipeService;
			
    public IndexController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
    	log.debug("I'm in index requestMapping");
		model.addAttribute("recipes", recipeService.getRecipes());
		
        return "index";
    }
}
