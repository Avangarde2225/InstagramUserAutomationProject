import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class testClass {
    private WebDriver driver;
    @BeforeClass
    @Parameters({"username", "password", "path"})
    public void setUp(String username, String password, String path) {

        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.get("https://www.instagram.com");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[@aria-label='Phone number, username, or email']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@aria-label='Password']")).sendKeys(password);
    }
        @AfterClass
        public void quit(){
            //driver.quit();
        }

        @Test
        public void test() throws InterruptedException {
        driver.findElement(By.xpath("//div[text()='Log In']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='Not Now']")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[text()='Not Now']")).click();

        driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("Cagri Taner");
        driver.findElement(By.xpath("//span[text()='cagritaner']")).click();

        driver.findElement(By.xpath("//a[@href='/cagritaner/followers/']")).click();


        List<WebElement> clickOnFollowButton = driver.findElements(By.xpath("//button[contains(text(),'Follow')]"));
            for (int i = 0; i < clickOnFollowButton.size() ; i++) {
                WebElement element = driver.findElements(By.xpath("//button[contains(text(),'Follow')]")).get(i);
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
                element.click();

                WebElement requested = driver.findElement(By.xpath("//button[contains(text(),'Requested')]"));
                WebElement following = driver.findElement(By.xpath("//button[contains(text(),'Following')]"));
                if(element.equals(requested) || element.equals(following)){
                    continue;
                }

            }
    }
}
