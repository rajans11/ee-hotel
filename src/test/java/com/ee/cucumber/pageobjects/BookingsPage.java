package com.ee.cucumber.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingsPage extends AbstractPage {

    /**
     * In an ideal situation, it would be good to speak with developers with regards to page dom model. Ideally
     * there would be sensible and recognisable locator names which makes automation slightly easier.
     *
     * With more understanding and business logic it would be good to understand the variations in different fields
     * a product page could have.
     */
    @FindBy(id = "bookings")
    private WebElement bookingsBox;

    @FindBy(id = "firstname")
    private WebElement firstNameField;

    @FindBy(id = "lastname")
    private WebElement lastNameField;

    @FindBy(id = "totalprice")
    private WebElement priceField;

    @FindBy(id = "depositpaid")
    private WebElement depositDropdown;

    @FindBy(id = "checkin")
    private WebElement checkinCalendar;

    @FindBy(id = "checkout")
    private WebElement checkoutCalendar;

    @FindBy(css = "input[value=\" Save \"]")
    private WebElement saveButton;

    private By deleteButton = By.cssSelector("input[value=\"Delete\"]");
    private static final By BOOKINGS = By.cssSelector("#bookings .row");
    private static final String BOOKING_ROW = ".//*[@id='%s']/div";

    public BookingsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isBookingsTablePresent(){
        return bookingsBox.isDisplayed();
    }

    public void enterFirstName(String text){
        type(firstNameField, text);
    }

    public void enterLastName(String text){
        type(lastNameField, text);
    }

    public void enterTotalPrice(String text){
        type(priceField, text);
    }

    public void selectDeposit(String text){
        select(depositDropdown, text);
    }

    public void enterCheckinDate(String text){
        type(checkinCalendar, text);
    }

    public void enterCheckOutDate(String text){
        type(checkoutCalendar, text);
    }

    public void saveBooking(){
        clickAndWait(saveButton, 5);
    }

    public void deleteBooking(){
        List<WebElement> deleteButtons  =  driver.findElements(deleteButton);
        clickAndWait(deleteButtons.get(deleteButtons.size() - 1), 2);
    }

    private String getLatestBookingRow(){
        List<WebElement> deleteButtons = driver.findElements(deleteButton);
        return deleteButtons.get(deleteButtons.size() - 1).getAttribute("onclick").substring(14,19);
    }

    public String getBookingData(int key){
        return driver.findElements(By.xpath(String.format(BOOKING_ROW, getLatestBookingRow()))).get(key).getText();
    }

    public void isLatestBookingVisible() {
        boolean visible = false;
        List<WebElement> deleteButtons = null;
        while (!visible){
            deleteButtons = driver.findElements(deleteButton);
            if (deleteButtons.size() != 0){
                visible = true;
            }
        }
        waitForElementToDisplay(deleteButtons.get(deleteButtons.size() - 1), 5);
    }

    public int getBookings(){
        return driver.findElements(BOOKINGS).size() - 1;
    }
}