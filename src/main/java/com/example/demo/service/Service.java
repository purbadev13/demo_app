package com.example.demo.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.Output;
import com.example.demo.dto.Starship;

@Component
public class Service {

	Log logger = LogFactory.getLog(this.getClass());
	static final String leia_organa = "https://swapi.dev/api/people/5";
	static final String darth_vader = "https://swapi.dev/api/people/4";
	static final String death_star = "https://swapi.dev/api/starships/9";

	public Output getInfo() throws Exception {
		Output output = new Output();
		try {

			RestTemplate rest = new RestTemplate();
			logger.info("setting Starship details");
			checkStarships(output, rest);
			logger.info("checking Planet Name");
			checkPlanet(output, rest);
			logger.info("checking Crew value of Death StarF");
			checkCrew(output, rest);
		} catch (Exception ex) {
			logger.error("some error occured", ex);
			throw ex;
		}
		return output;
	}

	/**
	 * method to check death star crew
	 * 
	 * @param output
	 * @param rest
	 */
	@SuppressWarnings("unchecked")
	private void checkCrew(Output output, RestTemplate rest) {
		Map<String, String> map5 = new LinkedHashMap<>();
		map5 = rest.getForObject(death_star, Map.class);
		String crew = map5.get("crew");
		if (StringUtils.hasText(crew)) {
			crew = crew.replace(",", "");
			output.setCrew(Integer.parseInt(crew));
		} else {
			output.setCrew(0);
		}
	}

	/**
	 * method to check planet
	 * 
	 * @param output
	 * @param rest
	 */
	@SuppressWarnings("unchecked")
	private void checkPlanet(Output output, RestTemplate rest) {
		Map<String, String> map3 = new LinkedHashMap<>();
		map3 = rest.getForObject(leia_organa, Map.class);
		String planet = map3.get("homeworld");
		Map<String, String> map4 = new LinkedHashMap<>();
		map4 = rest.getForObject(planet, Map.class);
		if ("Alderaan".equalsIgnoreCase(map4.get("name"))) {
			output.setLeiaOnPlanet(true);
		} else {
			output.setLeiaOnPlanet(false);
		}
	}

	/**
	 * method to check Starships
	 * 
	 * @param output
	 * @param rest
	 */
	@SuppressWarnings("unchecked")
	private void checkStarships(Output output, RestTemplate rest) {
		Map<String, List<String>> map1;
		map1 = rest.getForObject(darth_vader, Map.class);
		List<String> starships = map1.get("starships");
		if (!starships.isEmpty()) {
			Map<String, String> map2 = new LinkedHashMap<>();
			map2 = rest.getForObject(starships.get(0), Map.class);
			Starship starship = new Starship();
			starship.setName(map2.get("name"));
			starship.setModel(map2.get("model"));
			output.setStarship(starship);

		} else {
			Starship starship = new Starship();
			output.setStarship(starship);
		}
	}

}
