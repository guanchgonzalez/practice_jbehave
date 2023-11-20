
Scenario: I have the Google home page in a Chrome browser and search for "JBehave"

Given A Chrome browser with Google Home Page searching for JBehave
When Response includes What is JBehave?
Then Status is OK
