package com.ghouse.coronax.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghouse.coronax.exceptions.NoSuchRegionFound;
import com.ghouse.coronax.model.Region;
import com.ghouse.coronax.repository.RegionRepository;
import com.ghouse.coronax.service.RegionStatsService;

@Service
public class RegionStatsServiceImpl implements RegionStatsService {

	@Autowired
	RegionRepository regionRepository;
	
	@Override
	public List<Region> getRegionsCoronaStats() {
		List<Region> allRegionsCoronaStats = (List<Region>) regionRepository.findAll();
		return allRegionsCoronaStats;
	}

	@Override
	public Region getRegionCoronaStats(String regionName) {
		Optional<Region> regionCoronaStats = regionRepository.findById(regionName);
		if (regionCoronaStats.isEmpty()) {
			throw new NoSuchRegionFound("NO REGION FOUND");
		} else {
			return regionCoronaStats.get();
		}
	}
	
}
