package com.khohlov.khohlov.validator;

import com.khohlov.khohlov.domain.model.dummies_forms.CountryForm;
import com.khohlov.khohlov.service.model.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CountryCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryCreateFormValidator.class);
    private final CountryService countryService;

    @Autowired
    public CountryCreateFormValidator(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(CountryForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        CountryForm form = (CountryForm) target;
        validateName(errors, form);
    }

    private void validateName(Errors errors, CountryForm form) {
        if (countryService.getOneByName(form.getName()).isPresent()){
            errors.reject("name.exists", "Country with this name already exists");
        }
    }
}
