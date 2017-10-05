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

import com.libertymutual.goforcode.wimp.api.MovieApiController;
import com.libertymutual.goforcode.wimp.api.StuffNotFoundException;
import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.services.ActorRepo;
import com.libertymutual.goforcode.wimp.services.MovieRepo;

public class MovieApiControllerTest {

	private MovieRepo movieRepo;
	private ActorRepo actorRepo;
	private MovieApiController controller;

	@Before
	public void setUp() {
		movieRepo = mock(MovieRepo.class);
		actorRepo = mock(ActorRepo.class);
		controller = new MovieApiController(movieRepo, actorRepo);
	}

	@Test
	public void test_getAll_returs_all_movies_returned_by_the_repo() {

		// Arrange
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie());
		movies.add(new Movie());

		when(movieRepo.findAll()).thenReturn(movies);

		// MovieApiController controller = new MovieApiController(MovieRepo);

		// Act (atual is waht is returned)
		List<Movie> actual = controller.getAll();

		// Assert
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(movies.get(0));
		verify(movieRepo).findAll();
		// verify(kidRepo).findOne(3l); to prove it fails

	}

	@Test
	public void test_getOne_returs_kids_from_repo() throws Exception {
		// Arrange
		Movie tactonic = new Movie();
		when(movieRepo.findOne(7777L)).thenReturn(tactonic);

		// Act
		Movie actual = controller.getOne(7777L);

		// Assert
		assertThat(actual).isSameAs(tactonic);
		verify(movieRepo).findOne(7777L);
	}

	@Test
	public void test_getOne_throws_StuffNotFound_when_no_ators_returned() {
		try {
			controller.getOne(1);
			fail("The controller did not throw the StuffNotFoundException");
		} catch (StuffNotFoundException snfe) {
		}

	}

	@Test
	public void test_create_returns_movie() {
		Movie movie = new Movie();
		when(movieRepo.save(movie)).thenReturn(movie);

		// Act
		Movie actual = controller.create(movie);

		// Assert
		assertThat(movie).isSameAs(actual);
	}

	@Test
	public void test_update_returns_movie_updated() {
		Movie movie = new Movie();
		when(movieRepo.save(movie)).thenReturn(movie);

		// Act
		Movie actual = controller.update(movie, 3l);

		// Assert
		assertThat(movie).isSameAs(actual);
		verify(movieRepo).save(movie); /* that the same mock movir was updated */
	}

	@Test
	public void test_delete_returs_movie_deleted_when_found() {
		Movie movie = new Movie();
		when(movieRepo.findOne(3l)).thenReturn(movie);

		// Act
		Movie actual = controller.delete(3l);

		// Assert
		assertThat(movie).isSameAs(actual);
		verify(movieRepo).delete(3l); /* that the mock kid was deleted */
		verify(movieRepo).findOne(3l); /* that the */
	}

	@Test
	public void test_that_null_is_returned_when_findOne_throws_Empty_Result() {

		// Arrange
		when(movieRepo.findOne(8l)).thenThrow(new EmptyResultDataAccessException(0));

		// Act
		Movie actual = controller.delete(8l);

		// Assert
		assertThat(actual).isNull();
		verify(movieRepo).findOne(8l);
	}

	@Test
	public void test_associate_movie_with_actor() {
		// Arrange
		Movie movie = new Movie();
		movie.setId(3l);
		Actor actor = new Actor();
		actor.setId(3l);
		when(actorRepo.findOne(3l)).thenReturn(actor);
		when(movieRepo.findOne(3l)).thenReturn(movie);

		// Act
		Movie actual = controller.associateAnActor(3l, actor);

		// Assert
		assertThat(movie).isSameAs(actual);
		verify(movieRepo).findOne(3l);
		verify(actorRepo).findOne(3l);
	}

}
