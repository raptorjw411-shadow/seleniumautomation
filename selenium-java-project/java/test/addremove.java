/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.seleniumlogin;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
/**
 *
 * @author HP
 */
public class addremove {
    void add(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        WebElement add=driver.findElement(By.xpath("//button[@onclick='addElement()']"));
        List<WebElement> itemsBefore = driver.findElements(By.cssSelector(".added-manually"));
        int countBefore = itemsBefore.size();
        add.click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            System.getLogger(addremove.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        List<WebElement> itemsAfter = driver.findElements(By.cssSelector(".added-manually"));
        int countAfter = itemsAfter.size();
        if(countAfter== countBefore+1)
        {
            System.out.println("add function working properly");
        }
        else
        {
            System.out.println("add function failed");
        }
        System.out.println(countBefore);
        System.out.println(countAfter);
        
        WebElement del=driver.findElement(By.xpath("//button[@class='added-manually']"));
        List<WebElement> beforearray=driver.findElements(By.cssSelector(".added-manually"));
        int beforecount=beforearray.size();
        del.click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            System.getLogger(addremove.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        List<WebElement> afterarray=driver.findElements(By.cssSelector(".added-manually"));
        int aftercount=afterarray.size();
        if(aftercount==beforecount-1)
        {
            System.out.println("delete function is working properly");
        }
        else
        {
            System.out.println("delete function is not working properly");
        }
        System.out.println(beforecount);
        System.out.println(aftercount);
        driver.quit();
    }
    

    

public static void main(String[] args){
addremove ad=new addremove();
ad.add();
}
}
