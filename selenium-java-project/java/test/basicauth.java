/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.seleniumlogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 *
 * @author HP
 */
public class basicauth {
public static void main(String[] args){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Pass username and password in URL: https://username:password@URL
           String url = "https://admin:admin@the-internet.herokuapp.com/basic_auth";
            driver.get(url);
       
            // Verify login success by checking page content
            String pageText = driver.findElement(By.tagName("p")).getText();
            if (pageText.contains("Congratulations! You must have the proper credentials.")) {
                System.out.println("✅ Basic authentication successful");
            } else {
                System.out.println("❌ Basic authentication failed");
            }
        } catch (Exception e) {
            System.out.println("❌ Test failed: " + e.getMessage());
        } finally {
            // Close browser
            driver.quit();
        }
        
}
}
    
