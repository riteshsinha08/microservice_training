package com.example.olxmasterdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.olxmasterdata.model.AdvertiseStatus;

@Repository
public interface AdvertiseStatusRepository extends MongoRepository<AdvertiseStatus , Integer> {

}
