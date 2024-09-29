Feature: Validate user task completion

  Scenario: Validate if all FanCode city users have completed more than 50% of their todo tasks
    Given the user list is fetched from the API
    And user belongs to the city FanCode
    Then the user's completed task percentage should be greater than 50%
