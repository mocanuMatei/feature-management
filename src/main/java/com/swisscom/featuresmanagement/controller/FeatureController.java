package com.swisscom.featuresmanagement.controller;

import com.swisscom.featuresmanagement.entity.FeatureEntity;
import com.swisscom.featuresmanagement.service.FeatureService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/features")
public class FeatureController {

    private static final Logger logger = LoggerFactory.getLogger(FeatureController.class);

    private final FeatureService featureService;

    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    @GetMapping
    public ResponseEntity<List<FeatureEntity>> getAllFeatures(@RequestParam(required = false) String technicalName,
                                                              @RequestParam(required = false) String customerId,
                                                              @RequestParam(required = false) Boolean enabled) {
        logger.info("Received request to get all features with technicalName = {}, customerId = {}, enabled = {}", technicalName, customerId, enabled);
        List<FeatureEntity> features = featureService.getAllFeatures(technicalName, customerId, enabled);
        logger.info("{} features were found", features.size());
        return new ResponseEntity<>(features, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeatureEntity> getFeatureById(@PathVariable String id) {
        logger.info("Received request to get feature with id {}", id);
        FeatureEntity feature = featureService.getFeatureById(new ObjectId(id));

        if (feature == null) {
            logger.info("No feature with id {} was found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        logger.info("Feature with id {} was found: {}", id, feature);
        return new ResponseEntity<>(feature, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FeatureEntity> createFeature(@RequestBody FeatureEntity feature) {
        logger.info("Received request to create a new feature: {}", feature);
        FeatureEntity createdFeature = featureService.createFeature(feature);
        logger.info("A new feature was created: {}", createdFeature);
        return new ResponseEntity<>(createdFeature, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<FeatureEntity> updateFeature(@RequestBody FeatureEntity feature) {
        if (feature.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't update the feature, id is missing");
        }
        logger.info("Received request to update the feature with id {}: {}", feature.getId(), feature);
        FeatureEntity updatedFeature = featureService.updateFeature(feature);
        logger.info("Feature with id {} was updated: {}", updatedFeature.getId(), updatedFeature);
        return new ResponseEntity<>(updatedFeature, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeature(@PathVariable String id) {
        logger.info("Received request to delete the feature with id {}", id);
        featureService.deleteFeature(new ObjectId(id));
        logger.info("Feature with id {} was deleted", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
