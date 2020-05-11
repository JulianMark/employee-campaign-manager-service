package com.fundraising.employee.service.exception;

import com.fundraising.employee.service.http.CampaignActiveRequest;

import java.util.Optional;

public class IllegalArgExceptionRequest {

    public static void validateIdNumber (Integer idNumber) {

        Optional.ofNullable(idNumber)
                .map(integer -> integer < 1 ? null : integer)
                .orElseThrow( () -> new IllegalArgumentException(String.format(" el id ingresado es incorrecto %s", idNumber)));
    }

    public static void validateActiveCampaignRequest (CampaignActiveRequest request) {
        Optional.ofNullable(request)
                .orElseThrow(() -> new IllegalArgumentException(" el request no debe ser nulo"));
        validateIdNumber(request.getIdEmployee());
        validateIdNumber(request.getIdCampaign());
        validateIdNumber(request.getIdType());
    }
}
