package dev.backend.playwright.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class StartDateReader {
    public static LocalDate readStartDate(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int year = Integer.parseInt(reader.readLine().trim());
            int month = Integer.parseInt(reader.readLine().trim());
            int day = Integer.parseInt(reader.readLine().trim());

            return LocalDate.of(year, month, day);
        }
    }
}
