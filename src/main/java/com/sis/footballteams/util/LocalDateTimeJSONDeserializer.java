package com.sis.footballteams.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;


public class LocalDateTimeJSONDeserializer extends JsonDeserializer<LocalDateTime> {

    private final static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    private final static DateTimeFormatter formatter_2 = DateTimeFormat.forPattern("yyyy-MM-dd");

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {

        LocalDateTime date;
        if (jp.getText().isEmpty()) {
                return null;
        } else {
            try {
                date = formatter.parseLocalDateTime(jp.getText());
            }catch(IllegalArgumentException e){
                date=formatter_2.parseLocalDateTime(jp.getText());
            }
        }


        formatter.print(date);

        return date;
    }
}
