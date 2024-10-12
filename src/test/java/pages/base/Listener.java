package pages.base;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class Listener  implements TestWatcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    public void testFailed(ExtensionContext context, Throwable cause){
        LOGGER.info("Test {} - FAILED with exception: {}", context.getTestMethod().get().getName(), cause.getMessage());
        String screenshotName = context.getTestMethod().get().getName() +
                String.valueOf(System.currentTimeMillis()).substring(9, 13);
        LOGGER.info("Trying to trace screenshot");
        TakesScreenshot ts = (TakesScreenshot) ((BaseTest) context.getRequiredTestInstance()).driver;

        File source = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(source, new File("build/reports/tests" + screenshotName + ".png"));
        } catch (IOException e) {
            LOGGER.info("Exception on saving screenshot");
            throw new RuntimeException(e);
        }
        attachScreenshotsToAllure(ts);
    }

    @Attachment
    public byte[] attachScreenshotsToAllure(TakesScreenshot takesScreenshot){
        return takesScreenshot.getScreenshotAs(OutputType.BYTES);
    }
}
