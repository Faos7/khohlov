package com.khohlov.khohlov.repository.model;

import com.khohlov.khohlov.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Collection<Company> getAllByName(String name);

    Optional<Company> getByCountry_NameAndName(String country_name, String name);
}
