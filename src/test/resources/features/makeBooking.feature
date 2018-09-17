Feature: Bookings Page
  Background: Visit the website and make sure the page is visible
    Given I should be see a bookings table

  #Happy Path
  @P1
  Scenario: Create a booking
    When I enter data into the booking form and save it
      | firstName | firstName  |
      | lastName  | lastName   |
      | totalPrice| 100        |
      | deposit   | false      |
      | checkin   | 2018-01-01 |
      | checkout  | 2018-02-01 |
    Then I should see my booking

  #Happy Path
  @P1
  Scenario: Delete a booking
    And I get the Bookings count
    When I enter data into the booking form and save it
      | firstName | firstName  |
      | lastName  | lastName   |
      | totalPrice| 100        |
      | deposit   | false      |
      | checkin   | 2018-01-01 |
      | checkout  | 2018-02-01 |
    Then I should see my booking
    And I click delete my Booking
    Then I should not see my booking

  #Happy Path - Scenario to be completed once validation is in place, usually a ticket would be followed to track this implementation
  @Pending
  Scenario: Saving a booking without data
    When I click save
    Then I should see an error message