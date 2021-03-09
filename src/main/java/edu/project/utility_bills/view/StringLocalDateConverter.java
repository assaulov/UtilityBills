package edu.project.utility_bills.view;
/*
Конвертор строки в дату
 */
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringLocalDateConverter extends StdConverter<String, LocalDate> {

    @Override
    public LocalDate convert(String value) {
        if (value==null || value.trim().isEmpty()) {
            return null;
        }
        return LocalDate.parse(value, DateTimeFormatter.ofPattern(LocalDateStringConverter.DATE_FORMAT));
    }
}
