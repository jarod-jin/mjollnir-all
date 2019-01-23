@smokeTest
Feature: user login
  Scenario: login failed
    Given open sign in page, input user "99999"
    And input password "123456"
    When click sign in
    Then login failed
    And see the word "登录失败" in the page

  Scenario: login successful
    Given open sign in page, input user "45216"
    And input password "Jyb1983125"
    When click sign in
    Then login successful
    And see user name of "金亦冰" in the page


