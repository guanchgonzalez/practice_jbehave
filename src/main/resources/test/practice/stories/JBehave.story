
Scenario: I have the Google home page in a Chrome browser and search for "JBehave"

Given A Chrome browser with the Google home page
And I search for "JBehave"
When A response includes "What is JBehave?"
Then Status is Ok
When A response not includes "What is JBehave?"
Then Status is NOK