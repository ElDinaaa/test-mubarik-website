package pages;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

import java.time.Duration;
import java.util.Set;

import static constants.Constants.TimeoutVariables.EXPLICIT_WAIT;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactPage extends BasePage {

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    /*
      Header links
     */

    public final By headerLogoLink = By.xpath("//div[@class='logo']/a");
    public final By headerPortfolioLink = By.xpath("(//a[contains(text(),'Portfolio')])[1]");
    public final By headerAboutMeLink = By.xpath("(//a[contains(text(),'About me')])[1]");
    public final By headerContactsLink = By.xpath("(//a[contains(text(),'Contact')])[1]");
    public final By headerLangSwitcherEngLink = By.xpath("(//a[contains(text(),'en')])[1]");
    public final By headerLangSwitcherRuLink = By.xpath("(//a[contains(text(),'ru')])[1]");

    /*
        Contact form
     */

    public final By inputName = By.xpath("//input[@id='name']");
    public final By inputEmail = By.xpath("//input[@id='email']");
    public final By textareaEmail = By.xpath("//textarea[@id='message']");
    public final By buttonSend = By.xpath("//button[@type='submit']");

    /*
        Messages
     */

    public final By errorMessageName = By.xpath("//div[@id='error' and contains(text(), 'name')]");
    public final By errorMessageEmail = By.xpath("//div[@id='error' and contains(text(), 'email')]");
    public final By errorMessageTextarea = By.xpath("//div[@id='error' and contains(text(), 'message')]");
    public final By successMessageSent = By.xpath("//div[@id='formMessage' and contains(text(), 'sent')]");
    public final By successMessageSending = By.xpath("//div[@id='formMessage' and contains(text(), 'Sending')]");

    /*
        Footer links
     */

    public final By facebookIcon = By.xpath("((//div[@class='social-icons'])[2]/a)[1]");
    public final By instagramIcon = By.xpath("((//div[@class='social-icons'])[2]/a)[2]");
    public final By whatsappIcon = By.xpath("((//div[@class='social-icons'])[2]/a)[3]");

    /*
    Methods
     */

    // Универсальный метод для клика по элементам
    private void clickElement(By locator) {
        WebElement element = waitElementIsVisible(locator);
        element.click();
    }

    // Общий метод для ввода текста в поле
    private void enterText(By locator, String text) {
        WebElement inputField = waitElementIsVisible(locator);
        inputField.clear();  // Очищаем поле перед вводом (если нужно)
        inputField.sendKeys(text);
    }

    // Общий метод для проверки сообщений
    private void checkMessage(By locator, String expectedMessage, String errorMessage){
        WebElement messageElement = waitElementIsVisible(locator);
        String actualMessage = messageElement.getText();
        Assertions.assertEquals(expectedMessage, actualMessage, errorMessage);
    }

    // Метод для клика и переключения на новую вкладку
    private void clickAndSwitchToNewTab(By locator, String expectedUrlSubstring){
        WebElement icon = waitElementIsVisible(locator);
        icon.click();

        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        wait.until(driver1 -> allWindows.size() > 1);

        for (String windowHandle : driver.getWindowHandles()){
            if (!originalWindow.equals(windowHandle)){
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains(expectedUrlSubstring),
                "Expected URL to contain " + expectedUrlSubstring + " but was " + currentUrl);
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    // Метод для клика и проверки url на новую вкладку
    private void clickAndCheckUrl(By locator, String expectedUrlSubstring){
        WebElement link = waitElementIsVisible(locator);
        link.click();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains(expectedUrlSubstring),
                "Expected URL to contain " + expectedUrlSubstring + " but was " + currentUrl);
    }

    public ContactPage clearField(By locator) {
        WebElement field = waitElementIsVisible(locator);
        field.clear();
        return this;
    }

    public MainPage clickOnLogoLink(){
        clickAndCheckUrl(headerLogoLink, "en/index");
        return new MainPage(driver);
    }

    public PortfolioPage clickOnPortfolioLink(){
        clickAndCheckUrl(headerPortfolioLink, "en/work");
        return new PortfolioPage(driver);
    }

    public AboutMePage clickOnAboutMeLink(){
        clickAndCheckUrl(headerAboutMeLink, "en/about");
        return new AboutMePage(driver);
    }

    public ContactPage clickOnContactsLink(){
        clickAndCheckUrl(headerContactsLink, "en/contact");
        return this;
    }

    public ContactPage clickOnAndCheckLangSwitcherEngLink(){
        clickAndCheckUrl(headerLangSwitcherEngLink, "en");
        return this;
    }

    public ContactPageRu clickOnAndCheckLangSwitcherRuLink(){
        clickAndCheckUrl(headerLangSwitcherRuLink, "ru");
        return new ContactPageRu(driver);
    }

    public ContactPage enterName (String name){
        enterText(inputName, name);
        return this;
    }

    public ContactPage enterEmail (String email){
        enterText(inputEmail, email);
        return this;
    }
    public ContactPage enterMessage (String message){
        enterText(textareaEmail, message);
        return this;
    }

    public ContactPage clickOnSendButton(){
        clickElement(buttonSend);
        return this;
    }

    public ContactPage checkSendingMessage(String expectedMessage){
        checkMessage(successMessageSending, expectedMessage, "Sending message does not match the expected value.");
        return this;
    }

    public ContactPage checkSentMessage(String expectedMessage){
        checkMessage(successMessageSent, expectedMessage, "Sent message does not match the expected value.");
        return this;
    }

    public ContactPage checkFieldsAreCleared() {
        assertTrue(driver.findElement(inputName).getText().isEmpty(), "Name field should be empty");
        assertTrue(driver.findElement(inputEmail).getText().isEmpty(), "Email field should be empty");
        assertTrue(driver.findElement(textareaEmail).getText().isEmpty(), "Message field should be empty");
        return this;
    }

    public ContactPage checkErrorMessageName(String expectedMessage){
        checkMessage(errorMessageName, expectedMessage, "Error name message does not match the expected value.");
        return this;
    }

    public ContactPage checkErrorMessageEmail(String expectedMessage){
        checkMessage(errorMessageEmail, expectedMessage, "Error email message does not match the expected value.");
        return this;
    }

    public ContactPage checkErrorMessageTextarea(String expectedMessage){
        checkMessage(errorMessageTextarea, expectedMessage, "Error textarea message does not match the expected value.");
        return this;
    }

    public ContactPage checkSendButtonIsNotClickable(){
        WebElement button = waitElementIsVisible(buttonSend);
        Assertions.assertFalse(button.isEnabled(), "Send button should not be clickable");
        return this;
    }


    public ContactPage clickOnFacebookIcon(){
        clickAndSwitchToNewTab(facebookIcon, "facebook.com");
        return this;
    }

    public ContactPage clickOnInstagram(){
        clickAndSwitchToNewTab(instagramIcon, "instagram.com");
        return this;
    }

    public ContactPage clickOnWhatsappIcon(){
        clickAndSwitchToNewTab(whatsappIcon, "whatsapp.com");
        return this;
    }

}
