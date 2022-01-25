Scenario Outline: Navigating the Site

Background: As a User, i wish to be able to navigate around the site using clickable items

Scenario: Navigating to a Related Video
Given that i am on the video page
And there are related uploaded videos
When i click on the related Video link
Then i should be directed to that Videos Page instead

Scenario: Navigating to my Profile Site
Given that i am a logged in User
And i click on my Profile Image
When i choose the my profile option
Then i should be directed to my Profile Page

Scenario: Navigating to My Videos
Given that i am a logged in User
And i am on the Profile page
When i choose my videos 
Then i should get a list of all of my uploaded videos

Scenario: Navigating to My Subscriptions
Given that i am a logged in User
And i am on the Profile page
When i choose my subscriptions
Then i should get a list of all of the people i am subscribed to

Scenario: Navigating to my Profile Information
Given that i am a Logged in User
And i am on the Profile Page
When i choose Profile Info 
Then i should get information about my profile

Scenario: Navigating to the Register Page
Given that i am on the Login Page
When i press the Register button
Then i should be taken to the Registration page