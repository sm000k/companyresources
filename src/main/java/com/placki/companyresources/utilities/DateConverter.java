package com.placki.companyresources.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConverter {
    public static LocalDate stringToLocalDate(String dateString) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(dateString.trim(),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.err.println("Błąd parsowania daty: " + e.getMessage());
            return null;
        }
    }
}