/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.seleniumlogin;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 *
 * @author HP
 */
public class BrokenLinksTest {
    public static void main(String[] args){
    WebDriverManager.chromedriver().setup();
    WebDriver driver=new ChromeDriver();
     driver.get("https://www.eddymens.com/blog/page-with-broken-pages-for-testing-53049e870421");
     driver.manage().window().maximize();
     List<WebElement> links=driver.findElements(By.tagName("a"));
     System.out.println("total links found"+links.size());
     System.out.println("------------------------------------------------");
     for(WebElement link:links){
     String url=link.getAttribute("href");
     if(url == null || url.isEmpty()){
     System.out.println("skipped empty link");
     continue;
     }
     try {
                // Open connection to the link
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("HEAD");
                connection.connect();

                int responseCode = connection.getResponseCode();

                if (responseCode >= 400) {
                    System.out.println("❌ Broken link: " + url + " → " + responseCode);
                } else {
                    System.out.println("✅ Valid link:  " + url + " → " + responseCode);
                }

            } catch (Exception e) {
                System.out.println("⚠️ Exception checking: " + url + " → " + e.getMessage());
            }
        }

        driver.quit();
    }
}