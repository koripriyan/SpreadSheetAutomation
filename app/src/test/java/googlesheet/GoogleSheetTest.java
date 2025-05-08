package googlesheet;

import googlesheet.utils.Wrappers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.*;
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

import java.util.logging.Level;
import java.time.LocalDate;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;



//import demo.wrappers.Wrappers;

  public class GoogleSheetTest 
  {
    
    ChromeDriver driver;
    Wrappers wrapper;

    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private static final List<String> SCOPES =
    Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/home/koripriyan/Documents/Crio_Career/Levich/app/src/test/resources/credentials.json";



    @BeforeTest
    public void startBrowser() throws InterruptedException,IOException,GeneralSecurityException
    {
        
       wrapper = new Wrappers(driver);       

    } 

    @Test(priority = 1)
    public void NavigateToLoginPage()
    {
        wrapper.navigateTo("https://sheets.google.com");
        
        wrapper.waitForVisibility(By.xpath("//input[@type='email']"));
        wrapper.sendKeys(By.xpath("//input[@type='email']"),"koripriyantesting@gmail.com");
        wrapper.click(By.xpath("//span[text()='Next']"));
        
        wrapper.waitForVisibility(By.xpath("//input[@type='password']"));
        wrapper.sendKeys(By.xpath("//input[@type='password']"),"Kori#apr2025");
        wrapper.click(By.xpath("//span[text()='Next']")); 
    }

    @Test(priority = 2)
    public void ClickBlankSpreadSheet()
    {
        wrapper.waitForVisibility(By.xpath("//img[@src='https://ssl.gstatic.com/docs/templates/thumbnails/sheets-blank-googlecolors.png']"));
        wrapper.click(By.xpath("//img[@src='https://ssl.gstatic.com/docs/templates/thumbnails/sheets-blank-googlecolors.png']"));
    }    

    @Test(priority = 3)
    public void RenameSheet()
    {
        wrapper.rightClick(By.xpath("//span[@class='docs-sheet-tab-name' and text()='Sheet1']"));
        wrapper.click(By.xpath("//div[@class='goog-menuitem-content' and text()='Rename']"));
        wrapper.waitForVisibility(By.xpath("//span[@class='docs-sheet-tab-name' and text()='Sheet1']"));
        wrapper.sendKeys(By.xpath("//span[@class='docs-sheet-tab-name' and text()='Sheet1']"),"MySheet");
        wrapper.sendKeysWithKey(By.xpath("//span[@class='docs-sheet-tab-name' and text()='MySheet']"),Keys.ENTER);
    }    
    

    @Test(priority = 4) 
    public void WriteDataToSpreadSheet() throws InterruptedException,IOException,GeneralSecurityException
    {
        String currentUrl = wrapper.GetURL();
        System.out.println(currentUrl);
        String spreadsheetId = extractSpreadsheetId(currentUrl);

        if (spreadsheetId != null) {
            System.out.println("Sheet ID: " + spreadsheetId);
            //GoogleSheetsService.UpdateExcelValues(spreadsheetId); 
            //wrapper.MoveToElement(By.xpath("//div[@aria-label='A1']"));
        }

        else
        {
            System.out.println("No match found");
        }
    }
    
    /*
    @AfterTest
    public void endTest()
    {
        System.out.println("Closing Browser Session..");
        driver.close();
        driver.quit();
    }
    */

    private String extractSpreadsheetId(String url) {
        String regex = "https://docs.google.com/spreadsheets/d/([a-zA-Z0-9-_]+)";
        Matcher matcher = Pattern.compile(regex).matcher(url);
        return matcher.find() ? matcher.group(1) : null;
    } 
}
    

    
 
    
   