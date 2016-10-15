package com.sis.footballteams.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;

import java.io.IOException;

public final class Json {
 
	private static final ObjectMapper mapper = ObjectMapperFactory.createObjectMapper();

	public static String toJson(final Object obj) throws JsonException {
		if (obj == null) {
			return null;
		}

        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JsonException("Error formatting object into Json.", e);
        }
    }

	private Json() { }

}
