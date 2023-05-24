package Chapter3;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;




public class DefaultExecutionOrder_Class
{
    WebDriver driver;

    @BeforeClass
    public void setUp () throws Exception
    {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\VWICKSA\\Downloads\\chromedriver_win32\\chromedriver.exe");


        ChromeOptions options= new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable notifications");
        DesiredCapabilities cp = new DesiredCapabilities();
        cp.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(cp);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();


       //driver.get("https://mlm.pearson.com/global/");
        driver.get("https://login.pearson.com/v1/piapi/piui/signin");
    }
    @Test()
    public void signIn ()
    {


        driver.findElement(By.xpath("/html/body/section[1]/div[1]/div[2]/div/p[2]/a")).click();
        driver.manage().timeouts().implicitlyWait(1500, TimeUnit.SECONDS);


        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("salindame");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Sunday123");
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"mainButton\"]")).click();
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);



        System.out.println("1. Sign In");
    }
    @Test(dependsOnMethods = "signIn" )
    public void findElement ()
    {



        WebElement HelpIcon = driver.findElement(By.xpath("//*[@id=\"helpButton\"]"));
        Assert.assertTrue(HelpIcon.isDisplayed(),"Help is not displayed");


    }
    @Test (dependsOnMethods = "signIn" )
    public void signOut ()
    {

        driver.findElement(By.xpath("//*[@id=\"signOut\"]")).click();
        System.out.println("3. Sign Out");
    }

    @AfterClass
    public void tearDown () throws Exception
    {
        driver.quit();
    }
}