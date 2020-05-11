Feature: Active campaign

  Background:
    * url baseUrl
    * def activeBase = 'employee/campaign/active'

  Scenario Outline: Active campaign by employee id with status <statusCode>

    Given path activeBase
    And request <requestBody>
    When method PUT
    Then status <statusCode>
    And match response == <expected>

   Examples:
     | requestBody                                  | statusCode | expected |
     | {idEmployee: 1, idCampaign: 2, idType: 3 }   | 200        | { "result": 0, "errorMessage": null } |
     | {idEmployee: 1, idCampaign: 2, idType: ' ' } | 400        | { "result": null, "errorMessage": " el id ingresado es incorrecto null"} |