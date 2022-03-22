package cg.service.impl;

import cg.model.City;
import cg.repository.ICityRepository;
import cg.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    private ICityRepository iCityRepository;

//    @Override
//    public List<City> findAllC() {
//        return iCityRepository.findAll();
//    }


    @Override
    public List<City> FindAllCities() {
        return iCityRepository.findAll();
    }

    @Override
    public Optional<City> findById(Long id) {
        return iCityRepository.findById(id);
    }

    @Override
    public City save(City city) {
        return iCityRepository.save(city);
    }

    @Override
    public void remove(Long id) {
        iCityRepository.deleteById(id);
    }
}
