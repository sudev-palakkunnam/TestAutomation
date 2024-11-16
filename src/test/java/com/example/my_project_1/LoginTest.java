package com.example.my_project_1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterAll;

public class LoginTest {
    private WebDriver driver = null;
    private final String username = "student";
    private final String password = "Password123";
    private final String successfulUrl = "https://practicetestautomation.com/logged-in-successfully/";
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        WebDriverManager.chromedriver().setup();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @AfterEach
    void tearDown() throws Exception {
       driver.quit();
    }

    @Test
    void testPositiveLogin() {
        // Login process
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("submit"));
        loginButton.click();
        
        String currentUrl = driver.getCurrentUrl();
        assertEquals(successfulUrl, currentUrl, "Login failed: URL mismatch");
        if(successfulUrl.equalsIgnoreCase(currentUrl))
        System.out.println("Login Success");
        
        WebElement element = driver.findElement(By.xpath("/html/body/div/div/section/div/div/article/div[2]/p[1]/strong"));
        String messageText = element.getText();
        assertEquals("Congratulations student. You successfully logged in!",messageText, "Login success message not found");
        System.out.println("URL same");
        
        WebElement logoutButton = driver.findElement(By.xpath("/html/body/div/div/section/div/div/article/div[2]/div/div/div/a"));
        assertTrue(logoutButton.isDisplayed(),"Logout button not displayed");
        System.out.println("Logout Button displayed");
    }
    @Test
    void testNegativeLoginPassword() {
        // Login process
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("student");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("password124");

        WebElement loginButton = driver.findElement(By.id("submit"));
        loginButton.click();
        
        
        WebElement element = driver.findElement(By.id("error"));
        assertTrue(element.isDisplayed(),"error msg not displayed");
        assertEquals("Your password is invalid!", element.getText(), "error does not match");
        
    }
    @Test
    void testNegativeLoginUser() {
        // Login process
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("students");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Password123");

        WebElement loginButton = driver.findElement(By.id("submit"));
        loginButton.click();
        
        
        WebElement element = driver.findElement(By.id("error"));
        System.out.println(element.getText());
        assertTrue(element.isDisplayed(),"error msg not displayed");
        assertEquals("Your username is invalid!", element.getText(), "error does not match");

    }
}