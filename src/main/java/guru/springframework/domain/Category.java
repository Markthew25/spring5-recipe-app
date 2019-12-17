package guru.springframework.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String description;
	
	@ManyToMany(mappedBy="categories")
	private Set<Recipe> recipes;

	protected Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	protected String getDescription() {
		return description;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

	protected Set<Recipe> getRecipes() {
		return recipes;
	}

	protected void setRecipes(Set<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	

}
