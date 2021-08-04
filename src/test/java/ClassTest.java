import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ClassTest {

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
        //SelenideElement form = $("[data-test-id=callback-form]");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").setValue(timeStamp);
        $("[data-test-id=name] input").setValue("Василий Пупкин");
        $("[data-test-id=phone] input").setValue("+791601234560");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
       $(withText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(20));
    }
}
