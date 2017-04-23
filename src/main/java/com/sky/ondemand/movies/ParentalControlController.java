package com.sky.ondemand.movies;

import com.sky.ondemand.movies.metadata.TechnicalFailureException;
import com.sky.ondemand.movies.metadata.TitleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class ParentalControlController {

    private ParentalControlService parentalControlService;

    @Autowired
    public ParentalControlController(ParentalControlService parentalControlService) {
        this.parentalControlService = parentalControlService;
    }

    @RequestMapping(value = "/movie/parentalcontrol/{movieId}/{level}", method = GET)
    public ResponseEntity<Boolean> parentalControlService(@PathVariable("movieId") String movieId, @PathVariable("level") String level) {
        try {
            return ok(parentalControlService.movieWatchableFor(level, movieId));
        } catch (TitleNotFoundException e) {
            return status(NOT_FOUND).body(false);
        } catch (TechnicalFailureException e) {
            return status(INTERNAL_SERVER_ERROR).body(false);
        }
    }
}


