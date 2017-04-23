package com.sky.ondemand.movies.metadata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceStubTest {

    private MovieServiceStub movieServiceStub = new MovieServiceStub();
    ;

    @Test
    public void testGetParentalControlLevel() throws Exception {
        assertThat(movieServiceStub.getParentalControlLevel("id1"), is("U"));
        assertThat(movieServiceStub.getParentalControlLevel("id2"), is("PG"));
        assertThat(movieServiceStub.getParentalControlLevel("id3"), is("12"));
        assertThat(movieServiceStub.getParentalControlLevel("id4"), is("15"));
        assertThat(movieServiceStub.getParentalControlLevel("id5"), is("18"));
    }

    @Test(expected = TitleNotFoundException.class)
    public void testGetParentalControlLevelThrowsTitleNotFoundException() throws Exception {
        movieServiceStub.getParentalControlLevel("none");
    }

    @Test(expected = TechnicalFailureException.class)
    public void testGetParentalControlLevelThrowsTechnicalFailureException() throws Exception {
        movieServiceStub.getParentalControlLevel("id6");
    }
}