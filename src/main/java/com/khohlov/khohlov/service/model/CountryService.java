package com.khohlov.khohlov.service.model;

import com.khohlov.khohlov.domain.model.Country;
import com.khohlov.khohlov.domain.model.dummies_forms.CountryForm;

import java.util.Collection;
import java.util.Optional;


public interface CountryService {

    Collection<Country> getAllCountries();

    Country create(CountryForm countryForm);

    Optional<Country> getOneByName(String name);

    boolean remove(Integer id);

    void delete(Integer id);
}
