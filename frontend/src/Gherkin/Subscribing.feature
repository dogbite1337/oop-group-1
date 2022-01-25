Scenario Outline: Subscribing to another User

Background: As a User, i would like to be able to subscribe to other Users

Scenario: Subscribing to another User
Given that i am on the Videos Page
And i am logged in as a User
And i am not the same User as the one displayed
When i press the Subscribe button
Then i should become subscribed to the person
And the relevant amount of subscribers should be updated

Scenario: Unsubscribing from another User
Given that i am on the Videos Page
And i am logged in as a User
And i am not the same User as the one displayed
And i am subscribed to that User already
When i press the unsubscribe button
Then i should unsubscribe from that User