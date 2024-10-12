package pages.base;

import commonActions.CommonActions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;

import java.io.File;
import java.time.LocalTime;
import java.util.Objects;

import static commonActions.Config.*;

@ExtendWith(Listener.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    protected WebDriver driver;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    protected BasePage basePage;
    protected MainPage mainPage;
    protected PortfolioPage portfolioPage;
    protected AboutMePage aboutMePage;
    protected ContactPage contactPage;
    protected ContactPageRu contactPageRu;

    @BeforeAll
    void setUp(){
        driver = CommonActions.createDriver();
        initializePages();
        clearReports();
    }

    private void initializePages(){
        basePage = new BasePage(driver);
        mainPage = new MainPage(driver);
        portfolioPage = new PortfolioPage(driver);
        aboutMePage = new AboutMePage(driver);
        contactPage = new ContactPage(driver);
        contactPageRu = new ContactPageRu(driver);

        LOGGER.info("Pages initialized.");
    }

    private void clearReports(){
        LOGGER.info("START TIME:" + LocalTime.now());
        clearDirectory("allure-results");
        if (CLEAR_REPORTS_DIR){
            clearDirectory("build/reports/tests");
        }
    }

    private void clearDirectory(String path){
        File directory = new File(path);
        if (directory.exists() && directory.isDirectory()) {
            for (File item : Objects.requireNonNull(directory.listFiles())) {
                if (item.delete()) {
                    LOGGER.info("Deleted file: " + item.getName() + " from " + path);
                } else {
                    LOGGER.warn("Failed to delete file: " + item.getName() + " from " + path);
                }
            }
        } else {
            LOGGER.info("Directory '" + path + "' does not exist or is not a directory.");
        }
    }

    @AfterAll
    void clearCookiesAndLocalStorage(){
        if (CLEAR_COOKIES) {
            try {
                JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
                driver.manage().deleteAllCookies();
                javascriptExecutor.executeScript("window.sessionStorage.clear()");
                LOGGER.info("Cookies and session storage cleared.");
            } catch (Exception e){
                LOGGER.error("Error clearing cookies and local storage: " + e.getMessage(), e);
            }
        }
    }

    @AfterAll
    void close(){
        if (!HOLD_BROWSER_OPEN){
            try{
                driver.quit();
                LOGGER.info("Browser closed.");
            } catch (Exception e) {
                LOGGER.error("Error closing browser: " + e.getMessage(), e);
            }
        }
    }

}
