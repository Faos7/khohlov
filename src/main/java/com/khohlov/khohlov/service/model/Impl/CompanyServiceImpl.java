package com.khohlov.khohlov.service.model.Impl;


import com.khohlov.khohlov.domain.model.Company;
import com.khohlov.khohlov.domain.model.Item;
import com.khohlov.khohlov.domain.model.dummies_forms.CompanyForm;
import com.khohlov.khohlov.repository.model.CompanyRepository;
import com.khohlov.khohlov.repository.model.CountryRepository;
import com.khohlov.khohlov.repository.model.ItemRepository;
import com.khohlov.khohlov.service.model.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);

    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository,
                              CountryRepository countryRepository, ItemRepository itemRepository) {
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Company create(CompanyForm companyForm) {
        Company company = new Company();
        company.setAdres(companyForm.getAdres());
        company.setCountry(countryRepository.findOneByName(companyForm.getCountry()).get());
        company.setName(companyForm.getName());
        company.setPhone(companyForm.getPhone());
        company.setSite(companyForm.getSite());
        return companyRepository.save(company);
    }

    @Override
    public Collection<Company> getAllCompanies() {
        LOGGER.debug("Getting all companies");
        return companyRepository.findAll(new Sort("name"));
    }

    @Override
    public Collection<Company> getAllCompaniesWithName(String name) {
        return companyRepository.getAllByName(name);
    }

    @Override
    public boolean remove(Integer id) {
        Company company = companyRepository.getOne(id);
        List<Item> itemList = itemRepository.findAll();
        for (Item item : itemList) {
            if (item.getCompany().equals(company)) {
                return false;
            }
        }
        companyRepository.delete(company);
        return true;
    }

    @Override
    public void delete(Integer id) {
        companyRepository.delete(companyRepository.getOne(id));
    }
}
