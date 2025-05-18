package com.placki.companyresources.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConverter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private DateConverter() {
        // Utility class, no instantiation allowed
    }

    /**
     * Converts a string to a LocalDate using the predefined format.
     *
     * @param dateString The date string to convert.
     * @return The parsed LocalDate, or null if parsing fails.
     */
    public static LocalDate stringToLocalDate(String dateString) {
        try {
            return LocalDate.parse(dateString.trim(), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }
}