package com.ghouse.coronax.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ghouse.coronax.model.Region;

@Service
public interface RegionStatsService {

	List<Region> getRegionsCoronaStats();

	Region getRegionCoronaStats(String regionName);

}
