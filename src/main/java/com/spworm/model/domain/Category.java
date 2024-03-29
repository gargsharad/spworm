package com.spworm.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@SuppressWarnings("serial")
@Entity
public class Category implements DomainObject {

	private Long id;
	private String categoryName;
	private String categoryDescription;
	private Set<ArtEntity> artEntities = new HashSet<ArtEntity>();

	public Category() {
	}

	@Id
	@GeneratedValue
	public final Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	@ManyToMany
	public Set<ArtEntity> getArtEntities() {
		return artEntities;
	}

	public void setArtEntities(Set<ArtEntity> artEntities) {
		this.artEntities = artEntities;
	}

	/**
	 * This method controls the adding of the art_to_categories many-to-many
	 * association
	 * 
	 * @param art
	 * @return success
	 */
	public boolean addArtToCategory(ArtEntity art) {
		art.getCategories().add(this);
		return this.getArtEntities().add(art);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Category))
			return false;

		Category category = (Category) o;
		if (categoryName != null ? !categoryName.equals(category.categoryName)
				: category.categoryName != null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int hashCode() {
		return categoryName != null ? categoryName.hashCode() : 0;
	}
}
