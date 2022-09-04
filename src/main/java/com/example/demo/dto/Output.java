package com.example.demo.dto;

public class Output {

	private Starship starship;
	private Integer crew;
	private boolean isLeiaOnPlanet;

	public Starship getStarship() {
		return starship;
	}

	public void setStarship(Starship starship) {
		this.starship = starship;
	}

	public Integer getCrew() {
		return crew;
	}

	public void setCrew(Integer crew) {
		this.crew = crew;
	}

	public boolean isLeiaOnPlanet() {
		return isLeiaOnPlanet;
	}

	public void setLeiaOnPlanet(boolean isLeiaOnPlanet) {
		this.isLeiaOnPlanet = isLeiaOnPlanet;
	}

}
