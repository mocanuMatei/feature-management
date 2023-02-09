package com.swisscom.featuresmanagement.service;

import com.swisscom.featuresmanagement.entity.FeatureEntity;
import com.swisscom.featuresmanagement.repository.FeatureRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@EnableScheduling
public class FeatureService {

    private final static Logger logger = LoggerFactory.getLogger(FeatureService.class);

    private final FeatureRepository featureRepository;

    public FeatureService(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    public List<FeatureEntity> getAllFeatures(String technicalName, String customerId, Boolean enabled) {
        return featureRepository.getAllFeatures(technicalName, customerId, enabled);
    }

    public FeatureEntity getFeatureById(ObjectId id) {
        return featureRepository.findById(id).orElse(null);
    }

    public FeatureEntity createFeature(FeatureEntity feature) {
        Optional<FeatureEntity> optionalFeature = featureRepository.findByTechnicalName(feature.getTechnicalName());
        if (optionalFeature.isPresent()) {
            logger.info("A feature with the same technical name is already inserted: {}", feature);
            throw new ResponseStatusException(HttpStatus.CONFLICT, "There is already a feature called " + feature.getTechnicalName());
        }
        feature.setCreated(LocalDateTime.now());
        return featureRepository.save(feature);
    }

    public FeatureEntity updateFeature(FeatureEntity feature) {
        return featureRepository.save(feature);
    }

    public void deleteFeature(ObjectId id) {
        featureRepository.deleteById(id);
    }

    /**
     * Run once per hour and disable the expired features
     */
    @Scheduled(cron = "0 0 0 * * *")
    private void disableExpiredFeatures() {
        List<FeatureEntity> expiredFeatures = featureRepository.getExpiredFeatures()
                .stream()
                .peek(featureEntity -> featureEntity.setEnabled(false))
                .toList();
        featureRepository.saveAll(expiredFeatures);
    }
}
