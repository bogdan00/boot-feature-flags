package com.example.togglzredis.config;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

public enum AvailableFeatures implements Feature {
    @Label("Activates the secret feature")
    ENABLE_SECRET_FEATURE;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}
