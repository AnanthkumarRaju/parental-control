package com.sky.ondemand.movies;

import com.sky.ondemand.movies.metadata.TechnicalFailureException;
import com.sky.ondemand.movies.metadata.TitleNotFoundException;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class ParentalControlControllerTest {
    
    @Mock
    private ParentalControlService parentalControlService;
    
    @InjectMocks
    private ParentalControlController controller;

    @Test
    public void testParentalControlServiceReturnsTrue() throws Exception {
        
        when(parentalControlService.movieWatchableFor("some rating", "some id")).thenReturn(true);
        
        assertThat(controller.parentalControlService("some id", "some rating").getBody(), is(true));
        assertThat(controller.parentalControlService("some id", "some rating").getStatusCode(), is(OK));
    }

    @Test
    public void testParentalControlServiceReturnsFalse() throws Exception {

        when(parentalControlService.movieWatchableFor("some rating", "some id")).thenReturn(false);

        assertThat(controller.parentalControlService("some id", "some rating").getBody(), is(false));
        assertThat(controller.parentalControlService("some id", "some rating").getStatusCode(), is(OK));
    }

    @Test
    public void testParentalControlServiceReturnsFalseAndNotFoundHttpStatusCode() throws Exception {

        when(parentalControlService.movieWatchableFor("some rating", "some id")).thenThrow(new TitleNotFoundException("not found"));

        assertThat(controller.parentalControlService("some id", "some rating").getBody(), is(false));
        assertThat(controller.parentalControlService("some id", "some rating").getStatusCode(), is(NOT_FOUND));
    }

    @Test
    public void testParentalControlServiceReturnsFalseAndServerErrorHttpStatusCode() throws Exception {

        when(parentalControlService.movieWatchableFor("some rating", "some id")).thenThrow(new TechnicalFailureException("not found"));

        assertThat(controller.parentalControlService("some id", "some rating").getBody(), is(false));
        assertThat(controller.parentalControlService("some id", "some rating").getStatusCode(), is(INTERNAL_SERVER_ERROR));
    }
}