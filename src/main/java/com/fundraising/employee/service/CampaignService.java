package com.fundraising.employee.service;

import com.fundraising.employee.service.http.CampaignResponse;
import com.fundraising.employee.service.http.CampaignsEmployeeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

import static com.fundraising.employee.service.exception.IllegalArgExceptionIdNumber.validateIdNumber;

@RestController
public class CampaignService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignService.class);
    private final Function<Integer, ResponseEntity<CampaignResponse>> campaignBuilder;
    private final Function<Integer, ResponseEntity<CampaignsEmployeeResponse>> campaignListBuilder;

    @Autowired
    public CampaignService(Function<Integer, ResponseEntity<CampaignResponse>> campaignBuilder,
                           Function<Integer, ResponseEntity<CampaignsEmployeeResponse>> campaignListBuilder) {
        this.campaignBuilder = campaignBuilder;
        this.campaignListBuilder = campaignListBuilder;
    }

    @GetMapping(
            value = "employee/campaign/{idCampaign}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CampaignResponse> obtainCampaign(@PathVariable Integer idCampaign) {
        try {
            validateIdNumber(idCampaign);
            return campaignBuilder.apply(idCampaign);
        } catch (IllegalArgumentException iae) {
            LOGGER.warn("El parametro ingresado es invalido ", iae);
            return ResponseEntity.badRequest().body(new CampaignResponse(iae.getMessage()));
        } catch (Exception ex) {
            LOGGER.error("Ocurrio un error al intentar obtener la campania {}", idCampaign , ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CampaignResponse(ex.getMessage()));
        }
    }

    @GetMapping(
            value = "employee/campaignList/{idEmployee}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CampaignsEmployeeResponse> obtainCampaignList( @PathVariable Integer idEmployee) {
        try {
            validateIdNumber(idEmployee);
            return campaignListBuilder.apply(idEmployee);
        } catch (IllegalArgumentException iae) {
            LOGGER.warn("El parametro ingresado es invalido ", iae);
            return ResponseEntity.badRequest().build();
        } catch (Exception ex) {
            LOGGER.error("Ocurrio un error al intentar obtener la lista de campanias del empleado {}", idEmployee, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CampaignsEmployeeResponse(ex.getMessage()));
        }
    }
}
