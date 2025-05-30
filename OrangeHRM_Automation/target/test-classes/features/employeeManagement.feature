
Feature:
To perform test Scenarios for OrangeHRM website.

Background:
    Given User launches the OrangeHRM application
    And User logs in with valid credentials

  Scenario: Navigate to the PIM Module
    When User hovers over the PIM tab
    And User clicks on the PIM tab
    Then PIM dashboard should be visible


Scenario Outline: Add a new employee
    When User navigates to Add Employee section
    And User adds employee with first name "<FirstName>", middle name "<MiddleName>", last name "<LastName>" and employee id "<EmpId>"
    Then Employee should be added successfully

    Examples:
      | FirstName | MiddleName 	| LastName	| EmpId		|
      | Ganga			|            	| Unni			| 5642942 |
      | Fedrick		| Phil       	| Amalraj   | 5642943	|
      | Gowtham		| S      			| Prasad    | 5642944	|
      | Rob				| Van					| Dam  			| 5642945	|

@Regression
Scenario: Verify employees in the Employee List
    When User navigates to Employee List
    Then The following employees should be present in the list:
      | Ganga Unni				|
      | Fedrick Amalraj		|
      | Gowtham Prasad		|
      | Rob Dam						|