/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.seleniumlogin;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatePickerAutomation {

    public static void main(String[] args) {
        // change targetDate as required (dd-MM-yyyy)
        String targetDate = "25-12-2025";

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless=new"); // uncomment to run headless
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.manage().window().maximize();
            driver.get("https://testautomationpractice.blogspot.com/");

            // Wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // The datepicker input on the page - id="datepicker"
            By dateInputLocator = By.id("datepicker");

            // Ensure the input is present and clickable
            wait.until(ExpectedConditions.elementToBeClickable(dateInputLocator));

            // Call helper to select the date
            selectDate(driver, wait, dateInputLocator, targetDate);

            // Verify selected value
            WebElement dateInput = driver.findElement(dateInputLocator);
            System.out.println("Selected date in input: " + dateInput.getAttribute("value"));

            // small pause to visually confirm (optional)
            Thread.sleep(1500);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    /**
     * Selects a date in jQuery UI DatePicker.
     * @param driver WebDriver instance
     * @param wait WebDriverWait
     * @param dateInputLocator locator for date input (click to open)
     * @param dateStr date in dd-MM-yyyy format
     */
    public static void selectDate(WebDriver driver, WebDriverWait wait, By dateInputLocator, String dateStr) {
        // parse target date
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        LocalDate target = LocalDate.parse(dateStr, fmt);

        // click input to open datepicker
        WebElement input = driver.findElement(dateInputLocator);
        input.click();

        // Locators inside the jQuery datepicker widget
        By datePickerDiv = By.id("ui-datepicker-div");
        By monthLocator = By.className("ui-datepicker-month");
        By yearLocator = By.className("ui-datepicker-year");
        By nextBtn = By.cssSelector(".ui-datepicker-next");
        By prevBtn = By.cssSelector(".ui-datepicker-prev");

        // Wait for datepicker to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(datePickerDiv));

        // Loop until the displayed month/year matches the target
        int safety = 0;
        while (true) {
            // avoid infinite loop
            if (safety++ > 60) {
                throw new RuntimeException("Unable to find target month/year in datepicker - aborting");
            }

            // read displayed month and year
            WebElement monthElem = driver.findElement(monthLocator);
            WebElement yearElem = driver.findElement(yearLocator);

            String displayedMonth = monthElem.getText(); // e.g., "December"
            String displayedYear = yearElem.getText();   // e.g., "2025"

            // parse displayed to month number and year
            DateTimeFormatter monthFmt = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);
            // build displayed date as first day of displayed month for comparison
            LocalDate displayed = LocalDate.parse("01-" + displayedMonth + "-" + displayedYear,
                    DateTimeFormatter.ofPattern("dd-MMMM-yyyy", Locale.ENGLISH));

            // Compare displayed month/year with target month/year
            if (displayed.getMonthValue() == target.getMonthValue() && displayed.getYear() == target.getYear()) {
                break; // reached desired month/year
            }

            // Decide whether to go next or prev
            if (displayed.isBefore(target.withDayOfMonth(1))) {
                // click next
                WebElement next = driver.findElement(nextBtn);
                next.click();
            } else {
                // click prev
                WebElement prev = driver.findElement(prevBtn);
                prev.click();
            }

            // wait a short while for calendar to update
            wait.until(ExpectedConditions.visibilityOfElementLocated(datePickerDiv));
        }

        // Now click the day cell
        // day numbers are in table cells: //a[text()='25']
        String dayToClick = String.valueOf(target.getDayOfMonth());
        By dayLocator = By.xpath("//div[@id='ui-datepicker-div']//a[text()='" + dayToClick + "']");

        wait.until(ExpectedConditions.elementToBeClickable(dayLocator));
        driver.findElement(dayLocator).click();

        // Optionally wait until input reflects the selected date
        wait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(dateInputLocator), "value"));
    }
}
