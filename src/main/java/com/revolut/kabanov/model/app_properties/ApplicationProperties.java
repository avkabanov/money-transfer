package com.revolut.kabanov.model.app_properties;

/**
 * @author Kabanov Alexey
 */
public class ApplicationProperties {
    private String baseUrl;
    
    public ApplicationProperties(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
