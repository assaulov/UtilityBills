package edu.project.utility_bills.view;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateStringConverter extends StdConverter<LocalDate, String> {

    static final String DATE_FORMAT = "dd.MM.yyyy";
    @Override
    public String convert(LocalDate value) {
        return value == null ? "" :value.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
