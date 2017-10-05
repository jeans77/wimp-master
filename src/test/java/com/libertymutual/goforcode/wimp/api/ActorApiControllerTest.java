package com.libertymutual.goforcode.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.wimp.api.ActorApiController;
import com.libertymutual.goforcode.wimp.api.StuffNotFoundException;
import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.services.ActorRepo;
import com.libertymutual.goforcode.wimp.services.MovieRepo;

public class ActorApiControllerTest {

	private ActorRepo actorRepo;
	private MovieRepo movieRepo;
	private ActorApiController controller;

	@Before
	public void setUp() {
		actorRepo = mock(ActorRepo.class);
		controller = new ActorApiController(actorRepo);
	}

	@Test
	public void test_getAll_returs_all_actors_returned_by_the_repo() {

		// Arrange
		ArrayList<Actor> actors = new ArrayList<Actor>();
		actors.add(new Actor());
		actors.add(new Actor());
		actors.add(new Actor());

		when(actorRepo.findAll()).thenReturn(actors);

		ActorApiController controller = new ActorApiController(actorRepo);

		// Act (atual is waht is returned)
		List<Actor> actual = controller.getAll();

		// Assert
		assertThat(actual.size()).isEqualTo(3);
		assertThat(actual.get(0)).isSameAs(actors.get(0));
		verify(actorRepo).findAll();
		// verify(kidRepo).findOne(3l); to prove it fails

	}

	@Test
	public void test_getOne_returs_kids_from_repo() throws Exception {
		// Arrange
		ArrayList<Movie> movies = new ArrayList<Movie>();
		Actor woodddy = new Actor();
		woodddy.setId(7777l);
		woodddy.setFirstName("Woodddy");
		woodddy.setMovies(movies);
		when(actorRepo.findOne(7777L)).thenReturn(woodddy);

		// Act
		Actor actual = controller.getOne(7777L);

		// Assert
		assertThat(actual.getFirstName()).isSameAs("Woodddy");
		assertThat(actual.getId()).isEqualTo(7777l);
		assertThat(actual.getMovies()).isSameAs(movies);
		verify(actorRepo).findOne(7777L);
	}

	@Test
	public void test_getOne_throws_StuffNotFound_when_no_ators_returned() {
		try {
			controller.getOne(1);
			fail("The controller did not throw the StuffNotFoundException");
		} catch (StuffNotFoundException snfe) {
		}
		// Arrange
		// Act
		// Assert

	}

	@Test
	public void test_create_returns_actor() {
		Actor actor = new Actor();
		when(actorRepo.save(actor)).thenReturn(actor);

		// Act
		Actor actual = controller.create(actor);

		// Assert
		assertThat(actor).isSameAs(actual);
	}

	@Test
	public void test_update_returns_actor_updated() {
		Actor actor = new Actor();
		when(actorRepo.save(actor)).thenReturn(actor);

		// Act
		Actor actual = controller.update(actor, 3l);

		// Assert
		assertThat(actor).isSameAs(actual);
		verify(actorRepo).save(actor); /* that the same mock actor was updated */
	}

	@Test
	public void test_delete_returns_actor_deleted_when_found() {
		Actor actor = new Actor();
		when(actorRepo.findOne(3l)).thenReturn(actor);

		// Act
		Actor actual = controller.delete(3l);

		// Assert
		verify(actorRepo).delete(3l); /* that the mock kid was deleted */
	}

	@Test
	public void test_that_null_is_returned_when_findOne_throws_Empty_Result() {

		// Arrange
		when(actorRepo.findOne(8l)).thenThrow(new EmptyResultDataAccessException(0));

		// Act
		Actor actual = controller.delete(8l);

		// Assert
		assertThat(actual).isNull();
		verify(actorRepo).findOne(8l);
	}

}
