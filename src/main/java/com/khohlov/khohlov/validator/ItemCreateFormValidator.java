package com.khohlov.khohlov.validator;

import com.khohlov.khohlov.domain.model.Country;
import com.khohlov.khohlov.domain.model.dummies_forms.ItemForm;
import com.khohlov.khohlov.repository.model.CompanyRepository;
import com.khohlov.khohlov.repository.model.CountryRepository;
import com.khohlov.khohlov.service.model.Impl.ItemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;

@Component
public class ItemCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemCreateFormValidator.class);

    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public ItemCreateFormValidator(CompanyRepository companyRepository, CountryRepository countryRepository) {
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(ItemForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        ItemForm form = (ItemForm) target;
        validateStock(errors, form);
    }

    private void validateStock(Errors errors, ItemForm form){
        ArrayList<String> data = ItemServiceImpl.parseCompanyData(form.getCompany());
        if (!companyRepository.getByCountry_NameAndName(data.get(0),data.get(1)).isPresent()){
            errors.reject("wrong.data", "Company with such country and name does not exist");
        }
        if (!countryRepository.findOneByName(form.getCountry()).isPresent()){
            errors.reject("wrong.country", "Country with such name does not exist");
        }
    }



}
