package com.ghouse.coronax.service;

import org.springframework.stereotype.Service;

@Service
public interface CoronaStatsAggregatorService {
	public void aggregateCountryCoronaStats();
	public void aggregateRegionCoronaStats();
}
