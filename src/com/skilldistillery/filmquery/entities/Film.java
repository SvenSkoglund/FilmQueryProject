package com.skilldistillery.filmquery.entities;

import java.util.List;

public class Film {
	private int id;
	private String title;
	private String description;
	private int releaseYear;
	private int languageID;
	private int rentalDuration;
	private double rentalRate;
	private int length;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	private List actors;
	private String language;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List getActors() {
		return actors;
	}

	public void setActors(List actors) {
		this.actors = actors;
	}

	public Film(int id, String title, String description, int releaseYear, int languageID, int rentalDuration,
			double rentalRate, int length, double replacementCost, String rating, String specialFeatures, String language) {
		this.id = id;  //1
		this.title = title; //2 
		this.description = description; //3
		this.releaseYear = releaseYear; //4
		this.languageID = languageID; //5 
		this.rentalDuration = rentalDuration; //6 
		this.rentalRate = rentalRate; //7
		this.length = length; //8 
		this.replacementCost = replacementCost; //9
		this.rating = rating; // 10
		this.specialFeatures = specialFeatures; //11
		this.language = language; //12

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	public void printFilm(){
		System.out.println("*****************************");
		System.out.println("       "+getTitle());
		System.out.println(getDescription());
		System.out.println("       "+getRating());
		System.out.println();


		System.out.println("*****************************");
	}

	@Override
	public String toString() {
		return "ID = " + id + ", Title = " + title + ", Description = " + description + ", Release Year = " + releaseYear
				+ ", Language = " + language + ", Rental Duration = " + rentalDuration + ", Rental Rate = " + rentalRate
				+ ", Length = " + length + ", Replacement Cost = " + replacementCost + ", Rating = " + rating
				+ ", Special Features = " + specialFeatures + ", Cast = " + actors ;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageID() {
		return languageID;
	}

	public void setLanguageID(int languageID) {
		this.languageID = languageID;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(short length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(int replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}
}
