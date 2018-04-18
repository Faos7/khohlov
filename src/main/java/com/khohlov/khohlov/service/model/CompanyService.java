package com.khohlov.khohlov.service.model;



import com.khohlov.khohlov.domain.model.Company;
import com.khohlov.khohlov.domain.model.dummies_forms.CompanyForm;

import java.util.Collection;

public interface CompanyService {

    Company create(CompanyForm companyForm);

    Collection<Company> getAllCompanies();

    Collection<Company> getAllCompaniesWithName(String name);

    boolean remove(Integer id);

    void delete(Integer id);
}
