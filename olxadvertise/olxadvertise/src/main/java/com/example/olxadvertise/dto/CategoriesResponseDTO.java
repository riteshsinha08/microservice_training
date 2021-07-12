package com.example.olxadvertise.dto;

import java.util.List;

import com.example.olxadvertise.model.Category;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CategoriesResponseDTO {

	
	private List<Category> categories;

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
