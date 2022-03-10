package ru.netology.rest.generate;
import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;
import lombok.Data;
import lombok.Value;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
@Data
public class Generation {
    private final String city;
    private final DateAndTime data;
    private final String name;
    private final String phone;
}


