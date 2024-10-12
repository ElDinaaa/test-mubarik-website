package constants;

import org.apache.commons.lang3.RandomStringUtils;

public class Constants {

    public static class TimeoutVariables{
        public static final int IMPLICIT_WAIT = 20;
        public static final int EXPLICIT_WAIT = 10;
    }

    public static class Urls{
        public static final String MAIN_PAGE = "https://www.mubarikelmurzaeva.ru/en/index";
        public static final String PORTFOLIO_PAGE = "https://www.mubarikelmurzaeva.ru/en/work";
        public static final String ABOUT_ME_PAGE = "https://www.mubarikelmurzaeva.ru/en/about";
        public static final String CONTACT_PAGE = "https://www.mubarikelmurzaeva.ru/en/contact";
        public static final String CONTACT_PAGE_RU = "https://www.mubarikelmurzaeva.ru/ru/contact";
    }

    public static class TestDataForContactForm{
        public final static String NAME = "Dina";
        public final static String EMAIL = "test" + RandomStringUtils.randomAlphanumeric(5) + "@gmail.com";
        public final static String MESSAGE = "Your art is amazing";
        public final static String INVALID_NAME_NUMBERS = "88888888";
        public final static String INVALID_NAME_SYMBOLS = "---";
        public final static String INVALID_EMAIL_MISSING_SIGN = "test3gmail.com";
        public final static String INVALID_EMAIL_MISSING_DOMAIN = "test3@gmail";
        public final static String INVALID_EMAIL_CYRILLIC_LETTERS = "тест@пьфшд.сщь";
        public final static String INVALID_EMAIL_SYMBOLS = "---@gmail.com";

        public final static String SENDING_MESSAGE = "Sending...";
        public final static String SENT_MESSAGE = "Message sent successfully!";
        public final static String ERROR_MESSAGE_NAME = "Please enter a valid name.";
        public final static String ERROR_MESSAGE_EMAIL = "Please enter a valid email address.";
        public final static String ERROR_MESSAGE_MESSAGE = "Please enter a message.";

    }
}
