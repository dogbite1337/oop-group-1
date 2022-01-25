Scenario Outline: Registering as a User

Background: As a User i have navigated to the Register page and i wish to register a new account

  Scenario: Registering with all the information
    Given that i am on the Registration page
    And that i am filling out my information
    When i have filled out all of my information
    And i press on the Register button
    Then i should become registered
    And be redirected to the Home page

  Scenario: Uploading a profile image
    Given that i am on the Registration page
    When i fill out the Image URL input
    Then i should see a preview of my uploaded Profile Image

  Scenario: Registering without a Image
    Given that i am on the Registration Page
    And that i filled out my information
    And i did not fill out the Image URL
    When i press the Register button
    Then i should become registered without a profile image URL