package cg.controller;

import cg.model.City;
import cg.model.Country;
import cg.service.ICityService;
import cg.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;

    @GetMapping
    public ModelAndView showCities() {
        ModelAndView modelAndView = new ModelAndView("list");
        Iterable<City> cities = cityService.FindAllCities();
        if (!cities.iterator().hasNext()) {
            modelAndView.addObject("message", "We don't have any city");
        }
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("create");
        Iterable<Country> countries = countryService.findAllCountries();
        modelAndView.addObject("countries", countries);
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

//    @PostMapping("/create")
//    public ModelAndView createCity(@ModelAttribute("city") City city) {
//        ModelAndView modelAndView = new ModelAndView("create");
//        cityService.save(city);
//        return modelAndView;
//    }

    @PostMapping("/save")
    public ModelAndView save(@Validated @ModelAttribute City city, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("create");
        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("message", "Create Fail !!!");
            return modelAndView;
        }
        City city1 = cityService.save(city);
        if (city1 != null) {
            modelAndView.addObject("message", "Create City Successfully !!!");
        }
        modelAndView.addObject("countries", countryService.findAllCountries());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        cityService.remove(id);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView detail(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Optional<City> city = cityService.findById(id);
        modelAndView.addObject("city", city.get());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Optional<City> city = cityService.findById(id);
        Iterable<Country> countries = countryService.findAllCountries();
        modelAndView.addObject("countries", countries);
        modelAndView.addObject("city", city.get());
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView update(@PathVariable Long id, @ModelAttribute City city) {
        ModelAndView modelAndView = new ModelAndView("edit");
        city.setId(id);
        City cityEdit = cityService.save(city);
        if (cityEdit != null) {
            modelAndView.addObject("countries", countryService.findAllCountries());
            modelAndView.addObject("message", "Update City Successfully");
        }
        return modelAndView;
    }
}
