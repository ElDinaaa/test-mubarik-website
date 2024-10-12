package pages.contact.negative;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.base.BaseTest;

import static constants.Constants.TestDataForContactForm.*;
import static constants.Constants.Urls.CONTACT_PAGE;

public class ContactPageNegativeTests extends BaseTest {

    @BeforeEach
    public void setUp(){
        basePage.goToUrl(CONTACT_PAGE);
    }

    @Test
    public void testErrorMessageWithEmptyName(){
        contactPage
                .enterName(NAME)
                .clearField(contactPage.inputName)
                .enterEmail(EMAIL)
                .checkErrorMessageName(ERROR_MESSAGE_NAME);
    }

    @Test
    public void testErrorMessageWithEmptyEmail(){
        contactPage
                .enterEmail(EMAIL)
                .clearField(contactPage.inputEmail)
                .enterMessage(MESSAGE)
                .checkErrorMessageEmail(ERROR_MESSAGE_EMAIL);
    }

    @Test
    public void testErrorMessageWithEmptyTextArea(){
        contactPage
                .enterMessage(MESSAGE)
                .clearField(contactPage.textareaEmail)
                .clickOnSendButton()
                .checkErrorMessageTextarea(ERROR_MESSAGE_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {INVALID_NAME_NUMBERS,
                            INVALID_NAME_SYMBOLS})
    public void testErrorMessageWithInvalidName(String name){
        contactPage
                .enterName(name)
                .enterEmail(EMAIL)
                .checkErrorMessageName(ERROR_MESSAGE_NAME);
    }

    @ParameterizedTest
    @ValueSource(strings = {INVALID_EMAIL_MISSING_SIGN,
                            INVALID_EMAIL_MISSING_DOMAIN,
                            INVALID_EMAIL_CYRILLIC_LETTERS,
                            INVALID_EMAIL_SYMBOLS})
    public void testErrorMessageWithInvalidEmail(String email){
        contactPage
                .enterName(NAME)
                .enterEmail(email)
                .enterMessage(MESSAGE)
                .checkErrorMessageEmail(ERROR_MESSAGE_EMAIL);
    }


}
