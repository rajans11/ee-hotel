package com.ee.cucumber.steps;

import com.ee.cucumber.pageobjects.BookingsPage;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java8.En;

import java.util.Map;

import static com.ee.cucumber.utils.enums.Booking.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookingsSteps extends BaseSteps implements En {

    /**
     * Making use of the new Cucumber Java 8 version using lamba's instead of the standard way, more for readability.
     */
    public BookingsSteps() {
        Then("^I should be see a bookings table$", () -> {
            BookingsPage bookingsPage = new BookingsPage(driver);
            assertTrue("Failed to see the bookings table", bookingsPage.isBookingsTablePresent());
        });

        When("^I enter in a first name (.*)$", (String firstName) -> {
            BookingsPage bookingsPage = new BookingsPage(driver);
            bookingsPage.enterFirstName(firstName);
            scenarioSession.putData(FIRST_NAME.getValue(), firstName);
        });

        When("^I enter in a last name (.*)$", (String lastName) -> {
            BookingsPage bookingsPage = new BookingsPage(driver);
            bookingsPage.enterLastName(lastName);
            scenarioSession.putData(LAST_NAME.getValue(), lastName);
        });

        When("^I enter in a total price (.*)$", (String totalPrice) -> {
            BookingsPage bookingsPage = new BookingsPage(driver);
            bookingsPage.enterTotalPrice(totalPrice);
            scenarioSession.putData(TOTALPRICE.getValue(), totalPrice);
        });

        When("^I enter in a deposit (.*)$", (String deposit) -> {
            BookingsPage bookingsPage = new BookingsPage(driver);
            bookingsPage.selectDeposit(deposit);
            scenarioSession.putData(DEPOSIT.getValue(), deposit);
        });

        When("^I enter in a check-in date (.*)$", (String checkinDate) -> {
            BookingsPage bookingsPage = new BookingsPage(driver);
            bookingsPage.enterCheckinDate(checkinDate);
            scenarioSession.putData(CHECKIN.getValue(), checkinDate);
        });

        When("^I enter in a check-out date (.*)$", (String checkoutDate) -> {
            BookingsPage bookingsPage = new BookingsPage(driver);
            bookingsPage.enterCheckOutDate(checkoutDate);
            scenarioSession.putData(CHECKOUT.getValue(), checkoutDate);
        });

        When("^I enter data into the booking form and save it$", (DataTable table) -> {
            Map<String, String> data = table.asMap(String.class, String.class);
            String firstName = data.get("firstName");
            String lastName = data.get("lastName");
            String totalPrice = data.get("totalPrice");
            Boolean depositPaid = Boolean.getBoolean(data.get("deposit"));
            String checkin = data.get("checkin");
            String checkout = data.get("checkout");

            scenarioSession.putData(FIRST_NAME.getValue(), firstName);
            scenarioSession.putData(LAST_NAME.getValue(), lastName);
            scenarioSession.putData(TOTALPRICE.getValue(), totalPrice);
            scenarioSession.putData(DEPOSIT.getValue(), depositPaid);
            scenarioSession.putData(CHECKIN.getValue(), checkin);
            scenarioSession.putData(CHECKOUT.getValue(), checkout);

            BookingsPage bookingsPage = new BookingsPage(driver);

            bookingsPage.enterFirstName(firstName);
            bookingsPage.enterLastName(lastName);
            bookingsPage.enterTotalPrice(totalPrice);
            bookingsPage.selectDeposit(String.valueOf(depositPaid));
            bookingsPage.enterCheckinDate(checkin);
            bookingsPage.enterCheckOutDate(checkout);
            bookingsPage.saveBooking();
        });

        Then("^I should see my booking$", () -> {
            BookingsPage bookingsPage = new BookingsPage(driver);
            bookingsPage.isLatestBookingVisible();
            assertEquals(scenarioSession.getData(FIRST_NAME.getValue()), bookingsPage.getBookingData(0));
            assertEquals(scenarioSession.getData(LAST_NAME.getValue()), bookingsPage.getBookingData(1));
            assertEquals(scenarioSession.getData(TOTALPRICE.getValue()), bookingsPage.getBookingData(2));
            assertEquals(scenarioSession.getData(DEPOSIT.getValue()), Boolean.getBoolean(bookingsPage.getBookingData(3)));
            assertEquals(scenarioSession.getData(CHECKIN.getValue()), bookingsPage.getBookingData(4));
            assertEquals(scenarioSession.getData(CHECKOUT.getValue()), bookingsPage.getBookingData(5));
        });

        When("^I click save", () ->{
            BookingsPage bookingsPage = new BookingsPage(driver);
            bookingsPage.saveBooking();
        });

        Then("^I should see an error message$", () ->{
            throw new PendingException("Write code here that turns the phrase above into concrete actions");
        });

        And("^I click delete my Booking$", () -> {
            BookingsPage bookingsPage = new BookingsPage(driver);
            bookingsPage.deleteBooking();
        });

        And("^I get the Bookings count$", () -> {
            BookingsPage bookingsPage = new BookingsPage(driver);
            int bookings = bookingsPage.getBookings();
            scenarioSession.putData(BOOKINGS.getValue(), String.valueOf(bookings));
        });

        And("^I should not see my booking$", () -> {
            BookingsPage bookingsPage = new BookingsPage(driver);
            assertEquals(Integer.parseInt(String.valueOf(scenarioSession.getData(BOOKINGS.getValue()))), bookingsPage.getBookings());
        });
    }
}