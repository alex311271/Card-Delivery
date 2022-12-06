package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @BeforeEach
    void setUp() {

        open("http://localhost:9999");
    }

    @Test
    void largeName() {
        $("[data-test-id=city] input").setValue("Калининград");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(generateDate(4));
        $("[data-test-id=name] input").setValue("Ибн Аль-Банна Аль-Марракеши");
        $("[data-test-id=phone] input").setValue("+79993332211");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=notification")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + generateDate(4)),
                        Duration.ofSeconds(15));
    }

    @Test
    void filledInCorrectly1() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(generateDate(4));
        $("[data-test-id=name] input").setValue("Як Йоала");
        $("[data-test-id=phone] input").setValue("+71231231122");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=notification")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + generateDate(4)),
                        Duration.ofSeconds(15));
    }

    @Test
    void filledInCorrectly2() {
        $("[data-test-id=city] input").setValue("Вологда");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(generateDate(5));
        $("[data-test-id=name] input").setValue("Шаляпин Фёдор");
        $("[data-test-id=phone] input").setValue("+7456127892");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=notification")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + generateDate(5)),
                        Duration.ofSeconds(15));
    }

    @Test
    void latinCity() {
        $("[data-test-id=city] input").setValue("Kirov");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(generateDate(5));
        $("[data-test-id=name] input").setValue("Антон Антонов");
        $("[data-test-id=phone] input").setValue("+71598641375");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=city].input_invalid .input__sub")
                .shouldHave(Condition.text("Доставка в выбранный город недоступна"));
    }

    @Test
    void wrongDate() {
        $("[data-test-id=city] input").setValue("Киров");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(generateDate(1));
        $("[data-test-id=name] input").setValue("Петров-Водкин Иван");
        $("[data-test-id=phone] input").setValue("+71598641375");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=date] .input_invalid .input__sub")
                .shouldHave(Condition.text("Заказ на выбранную дату невозможен"));
    }

    @Test
    void noCity() {
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(generateDate(6));
        $("[data-test-id=name] input").setValue("Петров-Водкин Иван");
        $("[data-test-id=phone] input").setValue("+71598641375");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=city].input_invalid .input__sub")
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void noDate() {
        $("[data-test-id=city] input").setValue("Вологда");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+71598641375");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=date] .input_invalid .input__sub")
                .shouldHave(Condition.text("Неверно введена дата"));
    }

    @Test
    void noName() {
        $("[data-test-id=city] input").setValue("Волгоград");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(generateDate(4));
        $("[data-test-id=phone] input").setValue("+71598641375");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=name].input_invalid .input__sub")
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void noPhone() {
        $("[data-test-id=city] input").setValue("Брянск");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(generateDate(5));
        $("[data-test-id=name] input").setValue("Фиактистова Анна");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=phone].input_invalid .input__sub")
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void emptyForm() {
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='agreement']").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=city].input_invalid .input__sub")
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void latinName() {
        $("[data-test-id=city] input").setValue("Калининград");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(generateDate(5));
        $("[data-test-id=name] input").setValue("Anna-Nicolle Smit");
        $("[data-test-id=phone] input").setValue("+72587413692");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=name].input_invalid .input__sub")
                .shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shortPhone() {
        $("[data-test-id=city] input").setValue("Калининград");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(generateDate(5));
        $("[data-test-id=name] input").setValue("Анна-Николь Смит");
        $("[data-test-id=phone] input").setValue("+7258741369");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=phone].input_invalid .input__sub")
                .shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void longPhone() {
        $("[data-test-id=city] input").setValue("Калининград");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(generateDate(5));
        $("[data-test-id=name] input").setValue("Анна-Николь Смит");
        $("[data-test-id=phone] input").setValue("+7000000000012");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=phone].input_invalid .input__sub")
                .shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void noCheckAgreement() {
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(generateDate(5));
        $("[data-test-id=name] input").setValue("Антон Антонов");
        $("[data-test-id=phone] input").setValue("+71598641375");
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=agreement].input_invalid")
                .shouldHave(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void literInPhone() {
        $("[data-test-id=city] input").setValue("Калининград");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(generateDate(5));
        $("[data-test-id=name] input").setValue("Анна-Николь Смит");
        $("[data-test-id=phone] input").setValue("+phonenumber");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=phone].input_invalid .input__sub")
                .shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

}
