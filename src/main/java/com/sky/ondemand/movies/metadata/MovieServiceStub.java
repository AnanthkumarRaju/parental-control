package com.sky.ondemand.movies.metadata;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MovieServiceStub implements MovieService {
    private Map<String, String> movieParentalRating = new HashMap() {{
        put("id1", "U");
        put("id2", "PG");
        put("id3", "12");
        put("id4", "15");
        put("id5", "18");
        put("id6", "error");
    }};

    @Override
    public String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {
        String rating = movieParentalRating.get(movieId);

        if (rating == null) {
            throw new TitleNotFoundException("The movie service could not find the given movie for id " + movieId);
        } else if (rating.equals("error")) {
            throw new TechnicalFailureException("System error for given movie id " + movieId);
        }

        return rating;
    }
}
