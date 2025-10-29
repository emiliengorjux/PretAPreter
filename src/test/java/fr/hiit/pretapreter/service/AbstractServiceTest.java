package fr.hiit.pretapreter.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class AbstractServiceTest {
    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // les méthodes ici servent à simplifier le passage de en String en localDate et inversement !

    protected LocalDate stringToLocalDate(String dateString) {
        if (dateString == null) {
            return null;
        }
        return LocalDate.parse(dateString, DATE_FORMATTER);
    }

    protected String localDateToString(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(DATE_FORMATTER);
    }


}
