package cg.service.impl;

import cg.model.Country;
import cg.repository.ICountryRepository;
import cg.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceImpl implements ICountryService {
    @Autowired
    private ICountryRepository iCountryRepository;

    @Override
    public Iterable<Country> findAllCountries() {
        return iCountryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return iCountryRepository.findById(id);
    }
}
