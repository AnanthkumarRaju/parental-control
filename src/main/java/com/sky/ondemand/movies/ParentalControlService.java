package com.sky.ondemand.movies;

import com.sky.ondemand.movies.metadata.MovieService;
import com.sky.ondemand.movies.metadata.TechnicalFailureException;
import com.sky.ondemand.movies.metadata.TitleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ParentalControlService {

    private MovieService movieService;

    private Map<String, Integer> levelMap = new HashMap(){{
        put("U", 1);
        put("PG", 2);
        put("12", 3);
        put("15", 4);
        put("18", 5);
    }};

    @Autowired
    public ParentalControlService(MovieService movieService) {
        this.movieService = movieService;
    }

    public boolean movieWatchableFor(String parentalControlLevelPreference, String movieId) throws TechnicalFailureException, TitleNotFoundException {
        return getLevel(parentalControlLevelPreference) >= getLevel(movieService.getParentalControlLevel(movieId));
    }

    private Integer getLevel(String level) {
        return levelMap.entrySet().stream().filter(entry -> entry.getKey().equals(level)).map(Map.Entry::getValue).findFirst().orElse(-1);
    }
}