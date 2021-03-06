package tests;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import appmanager.ApplicationManager;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 23.02.2017.
 */
public class AdminLoginTest extends BaseTest{
    @Test
    public void loginToAdminConsoleTest(){
        driver.get(ADMIN_URL);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("loginAdmin")).click();
        assertThat(driver.getTitle(), is("My Store"));
    }

    @Test
    public void tryIETest(){
        WebDriver driver = ApplicationManager.getIEDriver();
        runTest(driver);
        driver.quit();
    }

    @Test
    public void tryFirefoxDriver(){
        WebDriver driver = ApplicationManager.getFirefoxDriver();
        runTest(driver);
        driver.quit();
    }

    @Test
    public void runFirefoxESRTest(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxDriver.MARIONETTE, false);
        WebDriver driver = new FirefoxDriver(
                new FirefoxBinary(new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe")),
                new FirefoxProfile(), caps);
        runTest(driver);
        driver.quit();
    }

    @Test
    public void runNightlyTest(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxDriver.MARIONETTE, true);
        WebDriver driver = new FirefoxDriver(
                new FirefoxBinary(new File("C:\\Program Files (x86)\\Nightly\\firefox.exe")),
                new FirefoxProfile(), caps);
        runTest(driver);
        driver.quit();
    }

    private void runTest(WebDriver driver){
        driver.get(ADMIN_URL);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("loginAdmin")).click();
        assertThat(driver.getTitle(), is("My Store"));
    }
}
