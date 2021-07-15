package com.ghouse.coronax.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghouse.coronax.exceptions.NoSuchCountryFound;
import com.ghouse.coronax.model.Country;
import com.ghouse.coronax.repository.CountryRepository;
import com.ghouse.coronax.service.CountryStatsService;

@Service
public class CountryStatsServiceImpl implements CountryStatsService {
	
	@Autowired
	CountryRepository countryRepository;
	
	
	@Override
	public List<Country> getCountriesCoronaStats() {
		List<Country> allCountriesCoronaStats = (List<Country>) countryRepository.findAll();
		return allCountriesCoronaStats;
	}

	@Override
	public Country getCountryCoronaStats(String countryName) {
		Optional<Country> countryCoronaStats = countryRepository.findById(countryName);
		if (countryCoronaStats.isEmpty()) {
			throw new NoSuchCountryFound("NO COUNTRY FOUND");
		} else {
			return countryCoronaStats.get();
		}
	}

}
