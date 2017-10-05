package com.libertymutual.goforcode.wimp.api;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.ActorWithMovies;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.services.ActorRepo;
import com.libertymutual.goforcode.wimp.services.MovieRepo;

@RestController
@RequestMapping("/api/actors")

public class ActorApiController {

	private ActorRepo actorRepo;
	private MovieRepo movieRepo;

	public ActorApiController(ActorRepo actorRepo) {
		this.actorRepo = actorRepo;

	}

	@GetMapping("")
	public List<Actor> getAll() {
		return actorRepo.findAll();
	}

	@GetMapping("{id}")
	public Actor getOne(@PathVariable long id) throws StuffNotFoundException {
		Actor actor = actorRepo.findOne(id);
		if (actor == null) {
			throw new StuffNotFoundException();
		}
		ActorWithMovies newActor = new ActorWithMovies();
		newActor.setActiveSinceYear(actor.getActiveSinceYear());
		newActor.setBirthDate(actor.getBirthDate());
		newActor.setMovies(actor.getMovies());
		newActor.setFirstName(actor.getFirstName());
		newActor.setLastName(actor.getLastName());
		newActor.setId(id);

		return newActor;
	}

	@PostMapping("")
	public Actor create(@RequestBody Actor actor) {
		return actorRepo.save(actor);
	}

	// @PostMapping("")
	// public Cereal create(@RequestBody Cereal cereal) {
	// return cerealRepo.save(cereal);
	// }

	@PutMapping("{id}")
	public Actor update(@RequestBody Actor actor, @PathVariable long id) {
		actor.setId(id);
		return actorRepo.save(actor);
	}

	@DeleteMapping("{id}")
	public Actor delete(@PathVariable long id) {
		try {
			Actor actor = actorRepo.findOne(id);
			actorRepo.delete(id);
			;
			return actor;
		} catch (org.springframework.dao.EmptyResultDataAccessException erdae) {
			return null;
		}
	}

}
