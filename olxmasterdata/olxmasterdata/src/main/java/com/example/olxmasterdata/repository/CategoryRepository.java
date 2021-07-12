package com.example.olxmasterdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.olxmasterdata.model.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, Integer> {

}
