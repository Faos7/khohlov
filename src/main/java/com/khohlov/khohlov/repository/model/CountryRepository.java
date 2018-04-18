package com.khohlov.khohlov.repository.model;

import com.khohlov.khohlov.domain.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Optional<Country> findOneByName(String name);
}
