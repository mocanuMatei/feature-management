package com.swisscom.featuresmanagement.repository;

import com.swisscom.featuresmanagement.entity.FeatureEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FeatureSearchRepositoryImpl implements FeatureSearchRepository {

    private final MongoTemplate mongoTemplate;

    public FeatureSearchRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Find all features based on criteria
     * @return all the features found
     */
    @Override
    public List<FeatureEntity> getAllFeatures(String technicalName,
                                              String customerId,
                                              Boolean enabled) {
        Criteria andQuery = new Criteria();
        List<Criteria> andExpression = new ArrayList<>();

        if (technicalName != null) {
            andExpression.add(Criteria.where("technicalName").is(technicalName));
        }

        if (customerId != null) {
            andExpression.add(Criteria.where("customerIds").in(customerId));
        }

        if(enabled != null){
            andExpression.add(Criteria.where("enabled").is(enabled));
        }

        if(!andExpression.isEmpty()){
            andQuery = andQuery.andOperator(andExpression);
        }
        Sort sortByCreatedDateDesc = Sort.by(Sort.Direction.DESC, "created");

        return this.mongoTemplate.find(new Query(andQuery).with(sortByCreatedDateDesc), FeatureEntity.class);
    }

    @Override
    public List<FeatureEntity> getExpiredFeatures() {
        Criteria andQuery = new Criteria();
        List<Criteria> andExpression = new ArrayList<>();

        andExpression.add(Criteria.where("expired").lt(LocalDateTime.now()));
        andQuery = andQuery.andOperator(andExpression);

        return this.mongoTemplate.find(new Query(andQuery), FeatureEntity.class);
    }
}
