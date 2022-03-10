package ru.netology.rest.utils;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import ru.netology.rest.generate.Generation;

import java.util.Locale;

@UtilityClass
public class DataGenerator {

    @UtilityClass
    public static class Registration{
        public static Generation generation(String locale){
            Faker faker = new Faker(new Locale(locale));
            return new Generation(faker.address().city(), faker.date(), faker.name().fullName(), faker.phoneNumber().phoneNumber());
        }
    }
}
