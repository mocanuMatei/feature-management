package com.swisscom.featuresmanagement.repository;

import com.swisscom.featuresmanagement.entity.FeatureEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeatureRepository extends MongoRepository<FeatureEntity, ObjectId>, FeatureSearchRepository {

    Optional<FeatureEntity> findByTechnicalName(String technicalName);
}

