/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.seleniumlogin;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
/**
 *
 * @author HP
 */
public class checkbox {
    void check(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        WebElement checkbox1 = driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]"));
        WebElement checkbox2 = driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]"));
         if (!checkbox1.isSelected()) {
            checkbox1.click();
        }

        // Unselect checkbox2 if selected
        if (!checkbox2.isSelected()) {
            checkbox2.click();
        }
        System.out.println("Checkbox 1 selected? " + checkbox1.isSelected());
        System.out.println("Checkbox 2 selected? " + checkbox2.isSelected());
        driver.quit();
    }
        public static void main(String[] args){
            checkbox result=new checkbox();
                    result.check();
        }
        
}