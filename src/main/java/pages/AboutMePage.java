package pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;

public class AboutMePage extends BasePage {

    public AboutMePage(WebDriver driver) {
        super(driver);
    }

    private final By textFromPage = By.xpath("//span[@class='title-ubarik']");

    public void verifyTextFromPageIsDisplayed(){
        WebElement line = waitElementIsVisible(textFromPage);
    }
}
