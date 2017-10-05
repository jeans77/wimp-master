package com.libertymutual.goforcode.wimp.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@Entity
public class Actor {

	// @ManyToOne
	// private CerealManufacturer manufacturer;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 75)
	private String firstName;

	@Column(nullable = true, length = 75)
	private String lastName;

	@Column(nullable = true)
	private long activeSinceYear;

	@Column(nullable = true)
	private String distributor;

	@Column(nullable = true)
	private Date birthDate;

	public List<Movie> getMovies() {
		return movies;
	}

	public Actor() {
	}

	public Actor(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getActiveSinceYear() {
		return activeSinceYear;
	}

	public void setActiveSinceYear(long activeSinceYear) {
		this.activeSinceYear = activeSinceYear;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@JsonIgnore
	@ManyToMany(mappedBy = "actors")
	private List<Movie> movies;

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

}
