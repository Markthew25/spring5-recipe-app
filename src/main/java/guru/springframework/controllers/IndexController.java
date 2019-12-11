package guru.springframework.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;

/**
 * Created by jt on 6/1/17.
 */
@Controller
public class IndexController {

	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;	
	
	public IndexController(CategoryRepository categoryRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}
	
    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(){
    	Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
    	
    	Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
    	
    	System.out.println(categoryOptional.get().getId());
    	
    	System.out.println(unitOfMeasureOptional.get().getId());
        return "index";
    }
}
