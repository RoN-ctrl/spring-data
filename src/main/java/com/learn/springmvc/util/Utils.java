package com.learn.springmvc.util;

import com.learn.springmvc.model.Ticket;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    private Utils() {
    }

    public static List<String> readLines(String filePath) {
        try (Stream<String> lines = Files.lines(Path.of(filePath))) {
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static Ticket.Category parseCategory(String category) {
        switch (category) {
            case "STANDARD":
                return Ticket.Category.STANDARD;
            case "PREMIUM":
                return Ticket.Category.PREMIUM;
            case "BAR":
                return Ticket.Category.BAR;
            default:
                return null;
        }
    }

    @SneakyThrows
    public static Date getFormattedDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(date);
        return dateFormat.parse(formattedDate);
    }
}
