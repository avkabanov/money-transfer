package com.revolut.kabanov.model.app_properties;

import com.revolut.kabanov.model.exception.ValidationException;

/**
 * @author Kabanov Alexey
 */
public class ApplicationPropertiesValidator {
    
    public void validateBaseUrl(String baseUrl) throws ValidationException {
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new ValidationException("Base Url should not be emtpy");
        }
    }
}
