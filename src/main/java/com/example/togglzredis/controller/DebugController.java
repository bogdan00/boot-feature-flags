package com.example.togglzredis.controller;


import com.example.togglzredis.config.AvailableFeatures;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.repository.FeatureState;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/debug")
public class DebugController {

    private final FeatureManager featureManager;

    public DebugController(FeatureManager featureManager) {
        this.featureManager = featureManager;
    }


    @GetMapping("/checkFeature")
    public Map<String, Object> checkFeatureToggle() {
        return new HashMap<String, Object>() {{
            put("(1) is the secret feature enabled?", featureManager.isActive(AvailableFeatures.ENABLE_SECRET_FEATURE));
            put("(2) is the secret feature enabled?", AvailableFeatures.ENABLE_SECRET_FEATURE.isActive());
        }};
    }

    @GetMapping("/toggleFeature")
    public Map<String, Object> toggleFeature() {
        boolean currentFeatureStatus = AvailableFeatures.ENABLE_SECRET_FEATURE.isActive();
        featureManager.setFeatureState(new FeatureState(AvailableFeatures.ENABLE_SECRET_FEATURE, !currentFeatureStatus));
        return new HashMap<String, Object>() {{
            put("(2) is the secret feature enabled?", AvailableFeatures.ENABLE_SECRET_FEATURE.isActive());
        }};
    }
}