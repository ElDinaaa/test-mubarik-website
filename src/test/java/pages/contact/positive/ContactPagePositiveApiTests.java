package pages.contact.positive;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.ContactForm;
import org.junit.jupiter.api.Test;

import static constants.Constants.TestDataForContactForm.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ContactPagePositiveApiTests {

    @Test
    public void testContactFormSubmissionReturns200(){
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
                .statusCode(200)
                .body("success", equalTo(true));
    }
}
