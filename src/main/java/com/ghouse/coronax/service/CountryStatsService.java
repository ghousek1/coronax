package com.ghouse.coronax.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ghouse.coronax.model.Country;

@Service
public interface CountryStatsService {


	List<Country> getCountriesCoronaStats();

	Country getCountryCoronaStats(String countryName);

}
