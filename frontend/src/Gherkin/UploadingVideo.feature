Scenario Outline: Uploading a Video

Background: When i am logged in as a User, i wish to be able to upload videos through a URL input

Scenario: Putting in Information
Given that i am putting in information in the Upload video page
When i write the information
Then the information should be displayed dynamically based on what i wrote

Scenario: Uploading the Video
Given that i have put in all the required fields of information in the Upload video page
When i press the Upload button
Then all other Users should be able to see and interact with my uploaded video

Scenario: Canceling upload of a Video
Given that i am on the Upload video page 
And i wish to cancel my upload
When i press the back home Arrow button
Then i should be directed back to the Home page