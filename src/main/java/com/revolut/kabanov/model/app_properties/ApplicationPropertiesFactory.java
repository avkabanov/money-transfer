package com.revolut.kabanov.model.app_properties;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.revolut.kabanov.model.exception.ValidationException;

/**
 * @author Kabanov Alexey
 */
public class ApplicationPropertiesFactory {
    private String baseUrl;
    private ApplicationPropertiesValidator validator;

    @Inject
    public ApplicationPropertiesFactory(@Named("base_url") String baseUrl,
                                        ApplicationPropertiesValidator validator) {
        this.baseUrl = baseUrl;
        this.validator = validator;
    }

    public ApplicationProperties createApplicationProperties() throws ValidationException {
        validator.validateBaseUrl(baseUrl);
        return new ApplicationProperties(baseUrl);
    }
}
