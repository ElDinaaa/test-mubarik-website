package pages.contact.negative;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.ContactForm;
import org.junit.jupiter.api.Test;

import java.net.SocketTimeoutException;

import static constants.Constants.TestDataForContactForm.*;
import static io.restassured.RestAssured.given;
import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContactPageNegativeApiTests {

    @Test
    public void testApiTimeoutHandling() {
        RestAssured.baseURI = "https://www.mubarikelmurzaeva.ru";

        ContactForm contactForm = ContactForm.builder()
                .name(NAME)
                .email(EMAIL)
                .message(MESSAGE)
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(contactForm)
        .when()
                .post("/contact")
        .then()
                .time(lessThan(4000L))
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test
    public void testTimeoutResponseHandling() {
        assertThrows(SocketTimeoutException.class, () -> {
            RestAssured.baseURI = "https://www.mubarikelmurzaeva.ru";
            ContactForm contactForm = ContactForm.builder()
                    .name(NAME)
                    .email(EMAIL)
                    .message(MESSAGE)
                    .build();

            given()
                    .contentType(ContentType.JSON)
                    .body(contactForm)
                    .config(RestAssured.config()
                            .httpClient(httpClientConfig()
                                    .setParam("http.connection.timeout", 1000) // 1 секунда
                                    .setParam("http.socket.timeout", 1000))) // 1 секунда
            .when()
                    .post("/contact")
            .then()
                    .statusCode(200);
        });
    }

    @Test
    public void testNotFoundResponse(){
        RestAssured.baseURI = "https://www.mubarikelmurzaeva.ru";

        given()
        .when()
                .get("/nonexistingpage")
        .then()
                .statusCode(404)
                .body(containsString("Page Not Found"));
    }

}
