Feature: Obtain list campaign

  Background:
    * url baseUrl
    * def obtainBase = 'employee/campaignList/'

  Scenario Outline: Obtain list campaign by employee id <employee> with status <statusCode>

    Given path obtainBase
    And param idEmployee = <employee>
    And request '{}'
    When method POST
    Then status <statusCode>
    And match response == <expected>

    Examples:
      | employee | statusCode | expected |
      | 1        | 200        | { "campaignList": [{ "idCampaign": 1, "idType": 1,"name": "RIO CUARTO" },{"idCampaign": 2,"idType": 2,"name": "VILLA ALLENDE"},{"idCampaign": 3,"idType": 1,"name": "MERLO"}],"errorMessage": null} |
      | 4        | 204        | '' |
      | -1       | 400        | { "campaignList": null, "errorMessage": " el id ingresado es incorrecto -1"} |