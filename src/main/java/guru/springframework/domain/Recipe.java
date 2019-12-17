package guru.springframework.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import guru.springframework.enums.Difficulty;
import lombok.Data;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;

	@Lob
	private String directions;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients = new HashSet<>();

	@Lob
	private Byte[] image;

	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes;

	// we change the default behavior of enumerated to EnumType.STRING , so that we
	// will see string values not Integers
	@Enumerated(value = EnumType.STRING)
	private Difficulty diffictulty;

	@ManyToMany
	@JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), 
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();

	public void setNotes(Notes notes) {
		notes.setRecipe(this);
		this.notes = notes;
	}

	public Recipe addIngredient(Ingredient i) {
		i.setRecipe(this);
		this.ingredients.add(i);
		return this;
	}

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

	protected Integer getPrepTime() {
		return prepTime;
	}

	protected void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}

	protected Integer getCookTime() {
		return cookTime;
	}

	protected void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}

	protected Integer getServings() {
		return servings;
	}

	protected void setServings(Integer servings) {
		this.servings = servings;
	}

	protected String getSource() {
		return source;
	}

	protected void setSource(String source) {
		this.source = source;
	}

	protected String getUrl() {
		return url;
	}

	protected void setUrl(String url) {
		this.url = url;
	}

	protected String getDirections() {
		return directions;
	}

	protected void setDirections(String directions) {
		this.directions = directions;
	}

	protected Set<Ingredient> getIngredients() {
		return ingredients;
	}

	protected void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	protected Byte[] getImage() {
		return image;
	}

	protected void setImage(Byte[] image) {
		this.image = image;
	}

	protected Difficulty getDiffictulty() {
		return diffictulty;
	}

	protected void setDiffictulty(Difficulty diffictulty) {
		this.diffictulty = diffictulty;
	}

	protected Set<Category> getCategories() {
		return categories;
	}

	protected void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	protected Notes getNotes() {
		return notes;
	}
	

}
