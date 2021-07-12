package com.example.olxlogin.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.olxlogin.model.UserEntity;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, Integer> {

	@Query(value="{'username' : ?0}")
	List<UserEntity> findByUserName(String username);
}
