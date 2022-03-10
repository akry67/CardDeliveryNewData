package ru.netology.rest.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;
import lombok.Data;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ru.netology.rest.generate.Generation;
import ru.netology.rest.utils.DataGenerator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class CardDeliveryTest {

    private static Faker faker;

    String generateDate(int days) {

        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }


    @BeforeAll
    static void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }

    @UtilityClass
    public static class Registration {
        public static Generation generation(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new Generation(faker.address().city(), faker.date(), faker.name().fullName(), faker.phoneNumber().phoneNumber());
        }
    }


    @Test
    void shouldSubmitRequest() {

        String planningDate = generateDate(6);

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder=\"Город\"]").setValue(faker.address().city());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder=\"Дата встречи\"]").val(planningDate);
        $(By.name("name")).val(faker.name().name());
        $(By.name("phone")).val(faker.phoneNumber().phoneNumber());
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Запланировать")).click();
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15));
        $(byText("Запланировать")).click();
        $(byText("Перепланировать")).click();
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15));
    }


}
