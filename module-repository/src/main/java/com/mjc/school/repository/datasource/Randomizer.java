package com.mjc.school.repository.datasource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class Randomizer {
    private static Random random = new Random();
    private Randomizer(){
    }

    public static List<String> getTextListFromFile(String filepath) {
        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filepath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            return bufferedReader.lines().toList();
        } catch (Exception e) {
            System.out.println("Exception: getTextListFromFile");
        }
        return null;
    }

    public static String getRandomText(List<String> textList) {
        int randomIndex = random.nextInt(textList.size());
        return textList.get(randomIndex);
    }

    public static LocalDateTime getRandomDateTime() {
        int endDay = 30;
        LocalDate day = LocalDate.now().plusDays(random.nextInt(endDay));
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        int second = random.nextInt(60);
        LocalTime time = LocalTime.of(hour, minute, second);
        return LocalDateTime.of(day, time);
    }
}