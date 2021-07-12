package com.example.olxmasterdata.dto;

import java.io.Serializable;
import java.util.List;

import com.example.olxmasterdata.model.Category;

public class CategoriesResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7811624762227756471L;
	private List<Category> categories;

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
