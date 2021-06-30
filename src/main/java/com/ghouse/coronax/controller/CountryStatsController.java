package com.ghouse.coronax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ghouse.coronax.model.ApiServiceResponse;
import com.ghouse.coronax.model.Country;
import com.ghouse.coronax.service.CountryStatsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/coronax/api/country")
public class CountryStatsController {
	@Autowired
	CountryStatsService countryStatsService;

	@GetMapping("/all")
	public ResponseEntity<Object> getAllCountriesStats() {
		log.info("Coronax API - All Countries Stats Loaded");
		List<Country> allCountriesCoronaStats=countryStatsService.getCountriesCoronaStats();
		ApiServiceResponse<List<Country>> allCountriesCoronaStatsData = new ApiServiceResponse("success", allCountriesCoronaStats);
		return new ResponseEntity<Object>(allCountriesCoronaStatsData, HttpStatus.OK);
	}

	@GetMapping("/{countryName}")
	public ResponseEntity<Object> getCountryStats(@PathVariable String countryName) {
		log.info("Coronax API - Country Stats of {} Loaded",countryName);
		Country countryCoronaStats=countryStatsService.getCountryCoronaStats(countryName);
		ApiServiceResponse<List<Country>> countryCoronaStatsData = new ApiServiceResponse("success", countryCoronaStats);
		return new ResponseEntity<Object>(countryCoronaStatsData, HttpStatus.OK);
	}

}
