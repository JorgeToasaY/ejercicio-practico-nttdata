Feature: Pruebas de Movement API

  Background:
    * url 'http://localhost:8085/api/movimientos'
    * header Content-Type = 'application/json'

# Escenario de setup: crea la cuenta antes de registrar movimientos
  Scenario: Crear cuenta para movimientos
    Given url 'http://localhost:8085/api/cuentas'
    And request { accountNumber: '123456002', accountType: 'Ahorros', initialBalance: 1000, state: true, customerId: 'CUST000013' }
    When method POST
    Then status 201

  Scenario: Registrar un retiro
    Given request { accountNumber: '123456002', movementType: 'RETIRO', amount: -100 }
    When method POST
    Then status 201
    And match response.movementType == 'RETIRO'

  Scenario: Registrar un depósito
    Given request { accountNumber: '123456002', movementType: 'DEPOSITO', amount: 500 }
    When method POST
    Then status 201
    And match response.amount == 500
