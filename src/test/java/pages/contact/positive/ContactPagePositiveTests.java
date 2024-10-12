package pages.contact.positive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.base.BaseTest;

import static constants.Constants.TestDataForContactForm.*;
import static constants.Constants.Urls.CONTACT_PAGE;

public class ContactPagePositiveTests extends BaseTest {

    @BeforeEach
    public void setUp(){
        basePage.goToUrl(CONTACT_PAGE);
    }

    @Test
    public void testValidContactFormSubmission(){
        contactPage
                .enterName(NAME)
                .enterEmail(EMAIL)
                .enterMessage(MESSAGE)
                .clickOnSendButton()
                .checkSendingMessage(SENDING_MESSAGE)
                .checkSentMessage(SENT_MESSAGE)
                .checkFieldsAreCleared();
    }

    @Test
    public void testLanguageSwitchToEnglish(){
        contactPage.clickOnAndCheckLangSwitcherEngLink();

    }

    @Test
    public void testLanguageSwitchToRussian(){
        contactPage.clickOnAndCheckLangSwitcherRuLink();
    }

    @Test
    public void testLogoLink(){
        contactPage.clickOnLogoLink();
    }

    @Test
    public void testPortfolioLink(){
        contactPage.clickOnPortfolioLink();
    }

    @Test
    public void testAboutMeLink(){
        contactPage.clickOnAboutMeLink();
    }

    @Test
    public void testContactsLink(){
        contactPage.clickOnContactsLink();
    }

    @Test
    public void testFacebookIcon(){
        contactPage.clickOnFacebookIcon();
    }

    @Test
    public void testInstagramIcon(){
        contactPage.clickOnInstagram();
    }

    @Test
    public void testWhatsappIcon(){
        contactPage.clickOnWhatsappIcon();
    }

}
