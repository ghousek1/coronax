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
import com.ghouse.coronax.model.Region;
import com.ghouse.coronax.service.RegionStatsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/coronax/api/region")
public class RegionStatsController {
	@Autowired
	RegionStatsService regionStatsService;

	@GetMapping("/all")
	public ResponseEntity<Object> getAllRegionsStats() {
		log.info("Coronax API - All Regions Corona Stats Loaded");
		List<Region> allRegionsCoronaStats=regionStatsService.getRegionsCoronaStats();
		ApiServiceResponse<List<Region>> allRegionsCoronaStatsData = new ApiServiceResponse("success", allRegionsCoronaStats);
		return new ResponseEntity<Object>(allRegionsCoronaStatsData, HttpStatus.OK);
	}

	@GetMapping("/{regionName}")
	public ResponseEntity<Object> getRegionStats(@PathVariable String regionName) {
		log.info("Coronax API - Region Corona Stats of {} Loaded",regionName);
		Region regionCoronaStats=regionStatsService.getRegionCoronaStats(regionName);
		ApiServiceResponse<List<Region>> regionCoronaStatsData = new ApiServiceResponse("success", regionCoronaStats);
		return new ResponseEntity<Object>(regionCoronaStatsData, HttpStatus.OK);
	}

}
