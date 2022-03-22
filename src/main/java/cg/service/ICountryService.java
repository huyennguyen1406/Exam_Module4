package cg.service;


import cg.model.Country;

import java.util.Optional;

public interface ICountryService {
    Iterable<Country> findAllCountries();

    Optional<Country> findById(Long id);

}
