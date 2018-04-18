package com.khohlov.khohlov.validator;

import com.khohlov.khohlov.domain.model.Company;
import com.khohlov.khohlov.domain.model.dummies_forms.CompanyForm;
import com.khohlov.khohlov.service.model.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Collection;

@Component
public class CompanyCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyCreateFormValidator.class);
    private final CompanyService companyService;

    @Autowired
    public CompanyCreateFormValidator(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(CompanyForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        CompanyForm form = (CompanyForm) target;
        validateNumber(errors, form);
        validatePhone(errors,form);
    }

    private void validateNumber(Errors errors, CompanyForm form) {
        if (form.getName() == ""){
            errors.reject("name.wrong", "Company name cant be empty");
        }
        Collection<Company> companies = companyService.getAllCompaniesWithName(form.getName());
        for (Company company :
                companies) {
            if (company.getCountry().getName().equals(form.getCountry())){
                errors.reject("name.exists", "Company with this name already exists in this country");
            }
        }
    }

    private void validatePhone(Errors errors, CompanyForm form){
        if (!regex(form.getPhone())){
            errors.reject("wrong.format", "Wrong phone number. Number should contain only numbers(0-9)");
        }
    }

    static boolean regex(final String text){
        for(int i=0; i<text.length(); i++) {
            if(text.charAt(i)<'0' || text.charAt(i)>'9') {
                return false;
            }
        }
        return true;
    }
}
