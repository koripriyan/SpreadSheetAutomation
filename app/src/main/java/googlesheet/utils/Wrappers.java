package googlesheet.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.logging.Level;

public class Wrappers
{
    WebDriver driver;
    WebDriverWait wait;

    public Wrappers(WebDriver driver)
    {
        
        System.out.println("start before test");
        System.setProperty("java.util.logging.config.file", "logging.properties");    
        
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");
        
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");  
      
        this.driver = new ChromeDriver(options);
        this.wait = new WebDriverWait(this.driver,Duration.ofSeconds(10));  
    } 

     public void navigateTo(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public String GetURL()
    {
       String url = driver.getCurrentUrl();
       return url; 
    }

    public void sendKeys(By locator, String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(value);
    }

    public void sendKeysWithKey(By locator, Keys key) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(key);
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void rightClick(By locator) {
        Actions actions = new Actions(driver);
        actions.contextClick(driver.findElement(locator)).perform();
    }

    public void MoveToElement(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator)).click().perform();
    }

    public void CLickAndMoveByOffSet() {
        Actions actions = new Actions(driver);
        actions.moveByOffset(200,200).click().perform();
    }

     public void ActionSendData(String str) {
        Actions actions = new Actions(driver);
        actions.sendKeys(str).perform();
    }

    public void ActionSendKeys(Keys key) {
        Actions actions = new Actions(driver);
        actions.sendKeys(key).perform();
    }
}