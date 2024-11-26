Feature: Validate Fitpeo Revenue Functionality

  @Task
  Scenario: Validate Revenue Calculator Page Functionality
    Given the user opens the web browser and navigates to the Fitpeo homepage
    When the user navigates to the Revenue Calculator page
    And the user scrolls down to the slider section
    And the user adjusts the slider
    And the user updates the text field as "560"
    Then the slider value is validated
    When the user selects CPT codes
    Then the total recurring reimbursement is validated
    And the header displaying the total reimbursement for all patients per month is verified
