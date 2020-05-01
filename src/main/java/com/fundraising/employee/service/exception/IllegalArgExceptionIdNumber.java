package com.fundraising.employee.service.exception;

import java.util.Optional;

public class IllegalArgExceptionIdNumber {

    public static void validateIdNumber (Integer idNumber) {

        Optional.ofNullable(idNumber)
                .map(integer -> integer < 1 ? null : integer)
                .orElseThrow( () -> new IllegalArgumentException(String.format(" el id ingresado es incorrecto %s", idNumber)));
    }
}
