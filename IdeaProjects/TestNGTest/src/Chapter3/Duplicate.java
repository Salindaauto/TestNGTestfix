package Chapter3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class Duplicate {

    public static WebDriver driver;

    @Test(dataProviderClass = DataRrovider.class,dataProvider = "usernameProvider")
    public static void signIn (String Username,String Password,Boolean success)
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\VWICKSA\\Downloads\\chromedriver_win32\\chromedriver.exe");

        driver = new ChromeDriver ();
        driver.manage().window().maximize();

        driver.get("https://mlm.pearson.com/global/");


        driver.findElement(By.xpath("/html/body/section[1]/div[1]/div[2]/div/p[2]/a")).click();
        driver.manage().timeouts().implicitlyWait(1500, TimeUnit.SECONDS);


        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(Username);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(Password);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"mainButton\"]")).click();
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);

        System.out.println("Sign In Credentials: " + "\n" +
                "  Username = " + Username + "\n" +
                "  Password = " + Password + "\n" +
                "  Successful Sign In = " + success + "\n" );




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
