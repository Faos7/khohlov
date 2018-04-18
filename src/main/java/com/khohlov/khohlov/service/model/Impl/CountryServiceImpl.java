package com.khohlov.khohlov.service.model.Impl;


import com.khohlov.khohlov.domain.model.Company;
import com.khohlov.khohlov.domain.model.Country;
import com.khohlov.khohlov.domain.model.dummies_forms.CountryForm;
import com.khohlov.khohlov.repository.model.CompanyRepository;
import com.khohlov.khohlov.repository.model.CountryRepository;
import com.khohlov.khohlov.service.model.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    private final CountryRepository countryRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, CompanyRepository companyRepository) {
        this.countryRepository = countryRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public Collection<Country> getAllCountries() {
        LOGGER.debug("Getting all countries");
        return countryRepository.findAll(new Sort("name"));
    }

    @Override
    public Country create(CountryForm countryForm) {
        Country country = new Country();
        country.setName(countryForm.getName());
        return countryRepository.save(country);
    }

    @Override
    public Optional<Country> getOneByName(String name) {
        return countryRepository.findOneByName(name);
    }

    @Override
    public boolean remove(Integer id) {
        Country country = countryRepository.getOne(id);
        List<Company> companyList = companyRepository.findAll();
        for (Company c :
                companyList) {
            if (c.getCountry().equals(country)) {
                return false;
            }
        }
        countryRepository.delete(country);
        return true;
    }

    @Override
    public void delete(Integer id) {
        countryRepository.delete(countryRepository.getOne(id));
    }
}
