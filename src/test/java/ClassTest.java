import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ClassTest {

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
//        Date currentDate = new Date();
//        Calendar c = Calendar.getInstance();
//        c.setTime(currentDate);
//        c.add(Calendar.DATE, 4);
//        Date currentDatePlusFour = c.getTime();
//        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(currentDatePlusFour);
        String timeStamp = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=city] input").setValue("Москва");

        $("[data-test-id=date] input").sendKeys( Keys.CONTROL +"A",Keys.DELETE);
        $("[data-test-id=date] input").setValue(timeStamp);
        $("[data-test-id=name] input").setValue("Василий Пупкин");
        $("[data-test-id=phone] input").setValue("+79160123456");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
      // $(withText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно забронирована на " + timeStamp));
        //$("[data-test-id=success-notification]").find(timeStamp);
    }
}
