Feature: Obtain campaign

  Background:
    * url baseUrl
    * def obtainBase = 'employee/campaign/'

  Scenario Outline: Obtain campaign by id <campaign> with status <statusCode>

    Given path obtainBase
    And param idCampaign = <campaign>
    And request '{}'
    When method POST
    Then status <statusCode>
    And match response == <expected>

    Examples:
      | campaign | statusCode | expected |
      | 1        | 200        | {"name": "RIO CUARTO","idOSC": 1,"osc": "AFULIC","idType": 1,"type": "ITINERANCIA","errorMessage": null} |
      | 4        | 204        | '' |
      | ' '      | 400        | {"idOSC":null,"idType":null,"name":null,"errorMessage":" el id ingresado es incorrecto null","type":null,"osc":null} |