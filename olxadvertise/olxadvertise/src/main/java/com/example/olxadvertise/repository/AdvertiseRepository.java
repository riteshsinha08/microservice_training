package com.example.olxadvertise.repository;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.olxadvertise.model.Advertise;

@Repository
public interface AdvertiseRepository extends MongoRepository<Advertise, Integer> {
	@DeleteQuery(value="{'_id' : ?0}")
	public Advertise deleteAdvertiseById (int id);
}
