package com.swisscom.featuresmanagement.repository;

import com.swisscom.featuresmanagement.entity.FeatureEntity;

import java.util.List;

public interface FeatureSearchRepository {

    List<FeatureEntity> getAllFeatures(String technicalName, String customerId, Boolean enabled);
    List<FeatureEntity> getExpiredFeatures();
}
