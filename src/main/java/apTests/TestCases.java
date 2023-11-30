
package apTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
// Selenium Imports
import java.util.logging.Level;
import javax.lang.model.util.ElementScanner6;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
///
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
// []
public class TestCases {
    WebDriver driver;

    public TestCases() throws MalformedURLException {
        System.out.println("Constructor: TestCases");

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);


        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {

        System.out.println("Start Test case: testCase01");
        driver.get("https://www.amazon.in/");
        String title = driver.getTitle();
        if(title.contains("Amazon"))
            System.out.println("Successfully opened the Amazon page.");
          
        else
         System.out.println("Error in opening the Amazion page.");
        System.out.println("end Test case: testCase01");
        
    }

    public void testCase02() throws InterruptedException{

        System.out.println("Start Test case: testCase02");
        Thread.sleep(20000);
        try{
            WebElement searchBox = driver.findElement(By.id("twotabsearchbox"));
            searchBox.clear();
            searchBox.sendKeys("laptop");
            
            WebElement submitBtn = driver.findElement(By.id("nav-search-submit-button"));
            submitBtn.click();

            List<WebElement> searchResults = driver.findElements(By.xpath( "//*[@data-component-type]"));
            for(WebElement searchResult: searchResults){
                if(searchResult.getText().contains("laptop") || searchResult.getText().contains("Laptop")){
                    System.out.println("Text: Laptop");
                    break;
                }

            }
        }
        catch(Exception e){
            System.out.println("product title does not contain the search item:laptop");
        }
        System.out.println("end Test case: testCase02");
    }
    
    public void testCase03(){

        System.out.println("Start Test case: testCase03");
        try{
            List<WebElement> navigationMenus = driver.findElements(By.xpath("//*[normalize-space(@class)='nav-a']" ));
            for(WebElement navigationMenu: navigationMenus){
            if(navigationMenu.getText().strip().equalsIgnoreCase("electronics")){
                navigationMenu.click();
                String newPage = driver.getCurrentUrl();
                if(newPage.contains("electronics")){
                    System.out.println("Url:"+ newPage);
                    break;
                }

            }
        }
        
    }
    catch(Exception e){
        System.out.println("new page url does not contain the search item:'electronics'");

    }
    System.out.println("end Test case: testCase03");
} 

}

