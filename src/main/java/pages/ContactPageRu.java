package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;

public class ContactPageRu extends BasePage {
    public ContactPageRu(WebDriver driver) {
        super(driver);
    }

    private final By textFromPage = By.xpath("//div[@class='bringing-visions']");

    public void verifyTextFromPageIsDisplayed(){
        WebElement line = waitElementIsVisible(textFromPage);
    }
}
