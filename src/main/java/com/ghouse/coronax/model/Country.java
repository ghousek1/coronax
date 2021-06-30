package com.ghouse.coronax.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Country {
	@Id
	private String countryName;
	private String region;
	private long totalCases;
	private long todayCases;
	private long lastWeekCases;
	private double totalCasesPerMillion;
	private double lastWeekCasesPerMillion;
	private long totalDeaths;
	private long todayDeaths;
	private long lastWeekDeaths;
	private double totalDeathsPerMillion;
	private double lastWeekDeathsPerMillion;
	private String transmissionClassification;

}
