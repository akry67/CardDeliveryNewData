package ru.netology.rest.utils;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import ru.netology.rest.generate.Generation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public class DataGenerator {

    private static Faker faker = new Faker(new Locale("ru"));


    @UtilityClass
    public static class Registration {

        public static String generateDate(int days) {
            // TODO: добавить логику для объявления переменной date и задания её значения, для генерации строки с датой
            // Вы можете использовать класс LocalDate и его методы для получения и форматирования даты
            return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public static String generateCity(String locale) {
            // TODO: добавить логику для объявления переменной city и задания её значения, генерацию можно выполнить
            // с помощью Faker, либо используя массив валидных городов и класс Random
            String city = faker.address().city();
            return city;
        }

        public static String generateName(String locale) {
            // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
            // использовать Faker
            String name = faker.name().name();
            return name;
        }

        public static String generatePhone(String locale) {
            // TODO: добавить логику для объявления переменной phone и задания её значения, для генерации можно
            // использовать Faker
            String phone = faker.phoneNumber().phoneNumber();
            return phone;
        }
    }

}
