package com.revolut.kabanov.model.app_properties;

import org.junit.Test;

import com.revolut.kabanov.model.exception.ValidationException;

/**
 * @author Kabanov Alexey
 */
public class ApplicationPropertiesValidatorTest {

    private ApplicationPropertiesValidator validator = new ApplicationPropertiesValidator();
    
    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenBaseUrlIsNull() throws ValidationException {
        validator.validateBaseUrl(null);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenBaseUrlIsEmpty() throws ValidationException {
        validator.validateBaseUrl("");
    }

    @Test
    public void shouldNotThrowExceptionWhenBaseUrlIsFilled() throws ValidationException {
        validator.validateBaseUrl("http://localhost:8080/");
    }
}