package com.sky.ondemand.movies;

import com.sky.ondemand.movies.metadata.MovieService;
import com.sky.ondemand.movies.metadata.TechnicalFailureException;
import com.sky.ondemand.movies.metadata.TitleNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParentalControlServiceTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private ParentalControlService parentalControlService;

    @Test
    public void testTitleWatchableForULevel() throws Exception {
        String movieId = "some id";

        when(movieService.getParentalControlLevel(movieId)).thenReturn("U");

        assertThat(parentalControlService.movieWatchableFor("U", movieId), is(true));
        assertThat(parentalControlService.movieWatchableFor("PG", movieId), is(true));
        assertThat(parentalControlService.movieWatchableFor("12", movieId), is(true));
        assertThat(parentalControlService.movieWatchableFor("15", movieId), is(true));
        assertThat(parentalControlService.movieWatchableFor("18", movieId), is(true));
    }

    @Test
    public void testTitleWatchableForPGLevel() throws Exception {
        String movieId = "some id";

        when(movieService.getParentalControlLevel(movieId)).thenReturn("PG");

        assertThat(parentalControlService.movieWatchableFor("U", movieId), is(false));
        assertThat(parentalControlService.movieWatchableFor("PG", movieId), is(true));
        assertThat(parentalControlService.movieWatchableFor("12", movieId), is(true));
        assertThat(parentalControlService.movieWatchableFor("15", movieId), is(true));
        assertThat(parentalControlService.movieWatchableFor("18", movieId), is(true));
    }

    @Test
    public void testTitleWatchableFor12Level() throws Exception {
        String movieId = "some id";

        when(movieService.getParentalControlLevel(movieId)).thenReturn("12");

        assertThat(parentalControlService.movieWatchableFor("U", movieId), is(false));
        assertThat(parentalControlService.movieWatchableFor("PG", movieId), is(false));
        assertThat(parentalControlService.movieWatchableFor("12", movieId), is(true));
        assertThat(parentalControlService.movieWatchableFor("15", movieId), is(true));
        assertThat(parentalControlService.movieWatchableFor("18", movieId), is(true));
    }

    @Test
    public void testTitleWatchableFor15Level() throws Exception {
        String movieId = "some id";

        when(movieService.getParentalControlLevel(movieId)).thenReturn("15");

        assertThat(parentalControlService.movieWatchableFor("U", movieId), is(false));
        assertThat(parentalControlService.movieWatchableFor("PG", movieId), is(false));
        assertThat(parentalControlService.movieWatchableFor("12", movieId), is(false));
        assertThat(parentalControlService.movieWatchableFor("15", movieId), is(true));
        assertThat(parentalControlService.movieWatchableFor("18", movieId), is(true));
    }

    @Test
    public void testTitleWatchableFor18Level() throws Exception {
        String movieId = "some id";

        when(movieService.getParentalControlLevel(movieId)).thenReturn("18");

        assertThat(parentalControlService.movieWatchableFor("U", movieId), is(false));
        assertThat(parentalControlService.movieWatchableFor("PG", movieId), is(false));
        assertThat(parentalControlService.movieWatchableFor("12", movieId), is(false));
        assertThat(parentalControlService.movieWatchableFor("15", movieId), is(false));
        assertThat(parentalControlService.movieWatchableFor("18", movieId), is(true));
    }

    @Test(expected = TitleNotFoundException.class)
    public void testTitleWatchableWhenMovieNotFound() throws Exception {
        String movieId = "some id";

        when(movieService.getParentalControlLevel(movieId)).thenThrow(new TitleNotFoundException("title not found"));

        parentalControlService.movieWatchableFor("U", movieId);
    }

    @Test(expected = TechnicalFailureException.class)
    public void testTitleWatchableWhenSystemErrorOccurred() throws Exception {
        String movieId = "some id";

        when(movieService.getParentalControlLevel(movieId)).thenThrow(new TechnicalFailureException("system error"));

        parentalControlService.movieWatchableFor("U", movieId);
    }
}