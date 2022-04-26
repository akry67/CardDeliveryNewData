package ru.netology.rest.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;
import lombok.Data;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
import static ru.netology.rest.utils.DataGenerator.Registration.generateDate;

public class CardDeliveryTest {



    @Test
    void shouldSubmitRequest() {

        String planningDate = generateDate(6);
        String planningDateNew = generateDate(8);

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder=\"Город\"]").setValue(DataGenerator.Registration.generateCity("ru"));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder=\"Дата встречи\"]").val(planningDate);
        $(By.name("name")).val(DataGenerator.Registration.generateName("ru"));
        $(By.name("phone")).val(DataGenerator.Registration.generatePhone("ru"));
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Запланировать")).click();
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15));
        $(byText("Запланировать")).click();
        $x("//input[@placeholder=\"Дата встречи\"]").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder=\"Дата встречи\"]").val(planningDateNew);
        $(byText("Перепланировать")).click();
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + planningDateNew), Duration.ofSeconds(15));
    }
}
