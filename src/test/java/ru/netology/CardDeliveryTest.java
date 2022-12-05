package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {


    @BeforeEach
    void setUp() {

        open("http://localhost:9999");
    }

    @Test
    void cardTest1() {
        $("[data-test-id=city] input").setValue("Калининград");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        Calendar data = new GregorianCalendar();
        data.add(Calendar.DAY_OF_YEAR, 5);
        SimpleDateFormat miFormat = new SimpleDateFormat("dd.MM.yyyy");
        String miData = miFormat.format(data.getTime());
        $("[data-test-id=date] input").setValue(miData);
        $("[data-test-id=name] input").setValue("Ибн Аль-Банна Аль-Марракеши");
        $("[data-test-id=phone] input").setValue("+79993332211");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=notification")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + miData),
                        Duration.ofSeconds(15));
    }

    @Test
    void cardTest2() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        Calendar data = new GregorianCalendar();
        data.add(Calendar.DAY_OF_YEAR, 5);
        SimpleDateFormat miFormat = new SimpleDateFormat("dd.MM.yyyy");
        String miData = miFormat.format(data.getTime());
        $("[data-test-id=date] input").setValue(miData);
        $("[data-test-id=name] input").setValue("Як Йоала");
        $("[data-test-id=phone] input").setValue("+71231231122");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=notification")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + miData),
                        Duration.ofSeconds(15));
    }

    @Test
    void cardTest3() {
        $("[data-test-id=city] input").setValue("Вологда");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        Calendar data = new GregorianCalendar();
        data.add(Calendar.DAY_OF_YEAR, 5);
        SimpleDateFormat miFormat = new SimpleDateFormat("dd.MM.yyyy");
        String miData = miFormat.format(data.getTime());
        $("[data-test-id=date] input").setValue(miData);
        $("[data-test-id=name] input").setValue("Шаляпин Фёдор");
        $("[data-test-id=phone] input").setValue("+7456127892");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=notification")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + miData),
                        Duration.ofSeconds(15));
    }

    @Test
    void cardTest4() {
        $("[data-test-id=city] input").setValue("Kirov");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        Calendar data = new GregorianCalendar();
        data.add(Calendar.DAY_OF_YEAR, 5);
        SimpleDateFormat miFormat = new SimpleDateFormat("dd.MM.yyyy");
        String miData = miFormat.format(data.getTime());
        $("[data-test-id=date] input").setValue(miData);
        $("[data-test-id=name] input").setValue("Антон Антонов");
        $("[data-test-id=phone] input").setValue("+71598641375");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=city].input_invalid .input__sub")
                .shouldHave(Condition.text("Доставка в выбранный город недоступна"));
    }

    @Test
    void cardTest5() {
        $("[data-test-id=city] input").setValue("Киров");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        Calendar data = new GregorianCalendar();
        data.add(Calendar.DAY_OF_YEAR, 1);
        SimpleDateFormat miFormat = new SimpleDateFormat("dd.MM.yyyy");
        String miData = miFormat.format(data.getTime());
        $("[data-test-id=date] input").setValue(miData);
        $("[data-test-id=name] input").setValue("Петров-Водкин Иван");
        $("[data-test-id=phone] input").setValue("+71598641375");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=date] .input_invalid .input__sub")
                .shouldHave(Condition.text("Заказ на выбранную дату невозможен"));
    }

    @Test
    void cardTest6() {
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        Calendar data = new GregorianCalendar();
        data.add(Calendar.DAY_OF_YEAR, 5);
        SimpleDateFormat miFormat = new SimpleDateFormat("dd.MM.yyyy");
        String miData = miFormat.format(data.getTime());
        $("[data-test-id=date] input").setValue(miData);
        $("[data-test-id=name] input").setValue("Петров-Водкин Иван");
        $("[data-test-id=phone] input").setValue("+71598641375");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=city].input_invalid .input__sub")
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void cardTest7() {
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
    void cardTest8() {
        $("[data-test-id=city] input").setValue("Волгоград");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        Calendar data = new GregorianCalendar();
        data.add(Calendar.DAY_OF_YEAR, 4);
        SimpleDateFormat miFormat = new SimpleDateFormat("dd.MM.yyyy");
        String miData = miFormat.format(data.getTime());
        $("[data-test-id=date] input").setValue(miData);
        $("[data-test-id=phone] input").setValue("+71598641375");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=name].input_invalid .input__sub")
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void cardTest9() {
        $("[data-test-id=city] input").setValue("Брянск");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        Calendar data = new GregorianCalendar();
        data.add(Calendar.DAY_OF_YEAR, 5);
        SimpleDateFormat miFormat = new SimpleDateFormat("dd.MM.yyyy");
        String miData = miFormat.format(data.getTime());
        $("[data-test-id=date] input").setValue(miData);
        $("[data-test-id=name] input").setValue("Фиактистова Анна");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=phone].input_invalid .input__sub")
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void cardTest10() {
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=city].input_invalid .input__sub")
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void cardTest11() {
        $("[data-test-id=city] input").setValue("Калининград");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        Calendar data = new GregorianCalendar();
        data.add(Calendar.DAY_OF_YEAR, 4);
        SimpleDateFormat miFormat = new SimpleDateFormat("dd.MM.yyyy");
        String miData = miFormat.format(data.getTime());
        $("[data-test-id=date] input").setValue(miData);
        $("[data-test-id=name] input").setValue("Anna-Nicolle Smit");
        $("[data-test-id=phone] input").setValue("+72587413692");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=name].input_invalid .input__sub")
                .shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void cardTest12() {
        $("[data-test-id=city] input").setValue("Калининград");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        Calendar data = new GregorianCalendar();
        data.add(Calendar.DAY_OF_YEAR, 6);
        SimpleDateFormat miFormat = new SimpleDateFormat("dd.MM.yyyy");
        String miData = miFormat.format(data.getTime());
        $("[data-test-id=date] input").setValue(miData);
        $("[data-test-id=name] input").setValue("Анна-Николь Смит");
        $("[data-test-id=phone] input").setValue("+7258741369");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=phone].input_invalid .input__sub")
                .shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void cardTest13() {
        $("[data-test-id=city] input").setValue("Калининград");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        Calendar data = new GregorianCalendar();
        data.add(Calendar.DAY_OF_YEAR, 6);
        SimpleDateFormat miFormat = new SimpleDateFormat("dd.MM.yyyy");
        String miData = miFormat.format(data.getTime());
        $("[data-test-id=date] input").setValue(miData);
        $("[data-test-id=name] input").setValue("Анна-Николь Смит");
        $("[data-test-id=phone] input").setValue("+7000000000012");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=phone].input_invalid .input__sub")
                .shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void cardTest14() {
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        Calendar data = new GregorianCalendar();
        data.add(Calendar.DAY_OF_YEAR, 5);
        SimpleDateFormat miFormat = new SimpleDateFormat("dd.MM.yyyy");
        String miData = miFormat.format(data.getTime());
        $("[data-test-id=date] input").setValue(miData);
        $("[data-test-id=name] input").setValue("Антон Антонов");
        $("[data-test-id=phone] input").setValue("+71598641375");
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=agreement].input_invalid")
                .shouldHave(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void cardTest15() {
        $("[data-test-id=city] input").setValue("Калининград");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        Calendar data = new GregorianCalendar();
        data.add(Calendar.DAY_OF_YEAR, 6);
        SimpleDateFormat miFormat = new SimpleDateFormat("dd.MM.yyyy");
        String miData = miFormat.format(data.getTime());
        $("[data-test-id=date] input").setValue(miData);
        $("[data-test-id=name] input").setValue("Анна-Николь Смит");
        $("[data-test-id=phone] input").setValue("+phonenumber");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id=phone].input_invalid .input__sub")
                .shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

}
