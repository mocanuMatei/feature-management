package com.swisscom.featuresmanagement.service;

import com.swisscom.featuresmanagement.entity.FeatureEntity;
import com.swisscom.featuresmanagement.repository.FeatureRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
public class FeatureServiceTest {

    @Autowired
    private FeatureService featureService;

    @MockBean
    private FeatureRepository featureRepository;

    @Test
    void getAllFeatures(){
        Mockito.when(featureRepository.getAllFeatures(Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean()))
                .thenReturn(List.of(new FeatureEntity()));

        List<FeatureEntity> featureEntities = featureService.getAllFeatures("technicalName", "customerId", true);
        Assertions.assertNotNull(featureEntities);
        Assertions.assertEquals(1, featureEntities.size());

        Mockito.verify(featureRepository).getAllFeatures(Mockito.eq("technicalName"), Mockito.eq("customerId"), Mockito.eq(true));
    }
}
