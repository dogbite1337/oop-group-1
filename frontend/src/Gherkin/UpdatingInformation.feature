Scenario Outline: Updating the information of a Video or my Profile

Background: As a Logged in User, i wish to be able to modify the information about my videos or my profile

Scenario: Updating my Profile information
Given that i am logged in as a User
And i am on the Profile Page
When i press the Edit Info button
Then i should be able to edit my information

Scenario: Saving the updated information
Given that i am logged in as a User
And i am on the Profile Page
And i have pressed the Edit Info button before
When i press the Save Info button
Then i should have all of my information saved and dynamically displayed

Scenario: Updating Video Information
Given that i am logged in as a User
And i am on the Profile Page
And i have navigated to the My Videos Section
When i press the edit button one of my videos
Then i should be able to update the information of my uploaded video

Scenario: Deleting videos
Given that i am logged in as a User
And i am on the Profile page
When i press the delete button on one of my videos
Then i should be able to remove that video
And remove all of the relevant comments along with it
And remove all of the likes/dislikes along with it