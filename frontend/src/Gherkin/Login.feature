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

Scenario: Logging out
When i am logged in as a User
And i wish to log out
When i press the Profile picture
And i then press the logout option
Then i should be logged out
And the page should forget about my settings

Scenario: Remembering my settings
When i log in as a User
Given that i have successfully logged in
Then the site should remember my information such as likes, dislikes etc. 