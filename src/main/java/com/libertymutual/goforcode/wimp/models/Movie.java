package com.libertymutual.goforcode.wimp.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Movie {

	@Id

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 300)
	private String title;

	@Column(nullable = true)
	private Date releaseDate;

	@Column(nullable = true)
	private long budget;

	@Column(nullable = false, length = 500)
	private String distributor;

	public Movie() {
	};

	public Movie(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public long getBudget() {
		return budget;
	}

	public void setBudget(long budget) {
		this.budget = budget;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	@ManyToMany
	private List<Actor> actors;

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void addActor(Actor actor) {
		if (actors == null) {
			actors = new ArrayList<Actor>();
		}
		actors.add(actor);
	}

}
