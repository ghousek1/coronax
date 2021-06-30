package com.ghouse.coronax.service.impl;

import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ghouse.coronax.model.Country;
import com.ghouse.coronax.model.Region;
import com.ghouse.coronax.repository.CountryRepository;
import com.ghouse.coronax.repository.RegionRepository;
import com.ghouse.coronax.service.CoronaStatsAggregatorService;
import com.ghouse.coronax.util.DataParserUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@EnableScheduling
public class CoronaStatsAggregatorServiceImpl implements CoronaStatsAggregatorService {

	@Autowired
	CountryRepository countryRepository;
	@Autowired
	RegionRepository regionRepository;
	@Autowired
	DataParserUtil dataParserUtil;

	@Scheduled(cron = "0 0 */6 * * *")
	public void aggregateCountryCoronaStats() {
		log.info("Aggregating Corona Statistics Data Country Wise");
		String CORONA_DATA_URI = "https://covid19.who.int/WHO-COVID-19-global-table-data.csv";

		// (WHO coronaStats)CSVRecord Header Format:
		String[] coronaStatsDataHeader = { "name", "region", "total cases", "total cases per 100k population",
				"new cases in last week", "new cases in last week per 100k population", "new cases in last 24 hours",
				"total deaths", "total deaths per 100k population", "new deaths in last week",
				"new deaths in last week per 100k population", "new deaths in last 24 hours",
				"transmission classification" };

		Iterable<CSVRecord> csvRecords = null;
		StringReader csvBodyReader;
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(CORONA_DATA_URI)).build();
		HttpResponse<String> httpResponse;
		try {
			httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

			csvBodyReader = new StringReader(httpResponse.body());

			csvRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().withHeader(coronaStatsDataHeader)
					.withIgnoreHeaderCase().withTrim().parse(csvBodyReader);

		} catch (Exception e) {
			log.error("CORONA_DATA_URI cannot be processed");
		};

		Set<Country> allCountriesCoronaStats = new HashSet<Country>();

		if (csvRecords != null) {
			for (CSVRecord record : csvRecords) {
				Country countryCoronaStats = new Country();
				countryCoronaStats
				    .setCountryName(dataParserUtil.extractAlphaNumericString(record.get("name")));
				countryCoronaStats
				    .setRegion(record.get("region"));
				countryCoronaStats
				    .setTotalCases(dataParserUtil.parseLong(record.get("total cases")));
				countryCoronaStats
				    .setTodayCases(dataParserUtil.parseLong(record.get("new cases in last 24 hours")));
				countryCoronaStats
				    .setLastWeekCases(dataParserUtil.parseLong(record.get("new cases in last week")));
				countryCoronaStats
				    .setTotalCasesPerMillion(Precision.round(dataParserUtil.parseDouble(record.get("total cases per 100k population")) * 10.00, 2));
				countryCoronaStats
				    .setLastWeekCasesPerMillion(Precision.round(dataParserUtil.parseDouble(record.get("new cases in last week per 100k population")) * 10.00,2));
				countryCoronaStats
				    .setTotalDeaths(dataParserUtil.parseLong(record.get("total deaths")));
				countryCoronaStats
				    .setTodayDeaths(dataParserUtil.parseLong(record.get("new deaths in last 24 hours")));
				countryCoronaStats
				    .setLastWeekDeaths(dataParserUtil.parseLong(record.get("new deaths in last week")));
				countryCoronaStats
				    .setTotalDeathsPerMillion(Precision.round(dataParserUtil.parseDouble(record.get("total deaths per 100k population")) * 10.00, 2));
				countryCoronaStats
				    .setLastWeekDeathsPerMillion(Precision.round(dataParserUtil.parseDouble(record.get("new deaths in last week per 100k population")) * 10.00,2));
				countryCoronaStats
				    .setTransmissionClassification(record.get("transmission classification"));
				
				if(countryCoronaStats.getCountryName().equals("Global")) {
					countryCoronaStats.setRegion("Global");
				}
                
				allCountriesCoronaStats.add(countryCoronaStats);
			}
			countryRepository.saveAll(allCountriesCoronaStats);

		}

	}

	@Scheduled(cron = "0  0 */6  * * *")
	public void aggregateRegionCoronaStats() {
		log.info("Aggregating Corona Statistics Data Region Wise");
		List<Country> allCountriesCoronaStats = (List<Country>) countryRepository.findAll();
		Map<String, List<Country>> groupedRegions = allCountriesCoronaStats.stream()
				.collect(Collectors.groupingBy(Country::getRegion));
		Set<Region> allRegionsCoronaStats = new HashSet<Region>();
		// aggregate data for individual region
		groupedRegions.entrySet().forEach(groupedRegion -> {
			Region region=new Region();
			String regionName = groupedRegion.getKey();
			List<Country> regionCountriesCoronaStats = groupedRegion.getValue();
			long regionTotalCases=regionCountriesCoronaStats
					 .stream()
					 .map(Country::getTotalCases)
					 .collect(Collectors.summingLong(Long::longValue));
			long regionTodayCases=regionCountriesCoronaStats
					.stream()
					.map(Country::getTodayCases)
					.collect(Collectors.summingLong(Long::longValue));
			long regionLastWeekCases=regionCountriesCoronaStats
					.stream()
					.map(Country::getLastWeekCases)
					.collect(Collectors.summingLong(Long::longValue));
			long regionTotalDeaths=regionCountriesCoronaStats
					.stream()
					.map(Country::getTotalDeaths)
					.collect(Collectors.summingLong(Long::longValue));
			long regionTodayDeaths=regionCountriesCoronaStats
					.stream()
					.map(Country::getTodayDeaths)
					.collect(Collectors.summingLong(Long::longValue));
			long regionLastWeekDeaths=regionCountriesCoronaStats
					.stream()
					.map(Country::getLastWeekDeaths)
					.collect(Collectors.summingLong(Long::longValue));
			double regionTotalCasesPerMillion=regionCountriesCoronaStats
					.stream()
					.map(Country::getTotalCasesPerMillion)
					.collect(Collectors.averagingDouble(Double::doubleValue));
			double regionLastWeekCasesPerMillion=regionCountriesCoronaStats
					.stream()
					.map(Country::getLastWeekCasesPerMillion)
					.collect(Collectors.averagingDouble(Double::doubleValue));
			double regionTotalDeathsPerMillion=regionCountriesCoronaStats
					.stream()
					.map(Country::getTotalDeathsPerMillion)
					.collect(Collectors.averagingDouble(Double::doubleValue));
			double regionLastWeekDeathsPerMillion=regionCountriesCoronaStats
					.stream()
					.map(Country::getLastWeekDeathsPerMillion)
					.collect(Collectors.averagingDouble(Double::doubleValue));
			
			//add the retrieved data to individual region
			region.setRegionName(regionName);
			region.setTotalCases(regionTotalCases);
			region.setTodayCases(regionTodayCases);
			region.setLastWeekCases(regionLastWeekCases);
			region.setTotalDeaths(regionTotalDeaths);
			region.setTodayDeaths(regionTodayDeaths);
			region.setLastWeekDeaths(regionLastWeekDeaths);
			region.setTotalCasesPerMillion(Precision.round(regionTotalCasesPerMillion,2));
			region.setLastWeekCasesPerMillion(Precision.round(regionLastWeekCasesPerMillion,2));
			region.setTotalDeathsPerMillion(Precision.round(regionTotalDeathsPerMillion,2));
			region.setLastWeekDeathsPerMillion(Precision.round(regionLastWeekDeathsPerMillion,2));
			allRegionsCoronaStats.add(region);
			
		});
		regionRepository.saveAll(allRegionsCoronaStats);

	}

}
