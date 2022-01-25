Scenario Outline: Logging in as a User

Background: As a User I have registered a Account on the Website

Scenario: Logging in on the Login Page
When i have navigated to the Login Page and i fill in my Credentials
Given that the provided credentials are correct
And i press the Login button
Then i should be logged into the system
And be redirected to the Home page

Scenario: Providing Incorrect Credentials
When i have navigated to the Login Page and i fill in my Credentials
Given that the provided credentials are not correct
And i press the Login button
Then i should get a message that tells me that i failed to login