package com.fundraising.employee.service;

import com.fundraising.employee.service.http.CampaignActiveRequest;
import com.fundraising.employee.service.http.CampaignActiveResponse;
import com.fundraising.employee.service.http.CampaignResponse;
import com.fundraising.employee.service.http.CampaignsEmployeeResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

import static com.fundraising.employee.service.exception.IllegalArgExceptionRequest.validateActiveCampaignRequest;
import static com.fundraising.employee.service.exception.IllegalArgExceptionRequest.validateIdNumber;

@RestController
public class CampaignService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignService.class);
    private final Function<Integer, ResponseEntity<CampaignResponse>> campaignBuilder;
    private final Function<Integer, ResponseEntity<CampaignsEmployeeResponse>> campaignListBuilder;
    private final Function <CampaignActiveRequest, ResponseEntity<CampaignActiveResponse>> campaignActiveBuilder;

    @Autowired
    public CampaignService(Function<Integer, ResponseEntity<CampaignResponse>> campaignBuilder,
                           Function<Integer, ResponseEntity<CampaignsEmployeeResponse>> campaignListBuilder,
                           Function<CampaignActiveRequest, ResponseEntity<CampaignActiveResponse>> campaignActiveBuilder) {
        this.campaignBuilder = campaignBuilder;
        this.campaignListBuilder = campaignListBuilder;
        this.campaignActiveBuilder = campaignActiveBuilder;
    }

    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PostMapping(
            value = "employee/campaign/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se obtienen los datos de la campania para el empleado", response = CampaignResponse.class),
            @ApiResponse(code = 204, message = "No se obtienen los datos para la campania", response = CampaignResponse.class),
            @ApiResponse(code = 400, message = "Argumentos inválidos", response = CampaignResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del servicio web", response = CampaignResponse.class)
    })
    public ResponseEntity<CampaignResponse> obtainCampaign(@RequestParam Integer idCampaign) {
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

    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PostMapping(
            value = "employee/campaignList/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se obtiene la lista de campanias para el empleado", response = CampaignsEmployeeResponse.class),
            @ApiResponse(code = 204, message = "No se obtiene la lista de campanias para el empleado", response = CampaignsEmployeeResponse.class),
            @ApiResponse(code = 400, message = "Argumentos inválidos", response = CampaignsEmployeeResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del servicio web", response = CampaignsEmployeeResponse.class)
    })
    public ResponseEntity<CampaignsEmployeeResponse> obtainCampaignList( @RequestParam Integer idEmployee) {
        try {
            validateIdNumber(idEmployee);
            return campaignListBuilder.apply(idEmployee);
        } catch (IllegalArgumentException iae) {
            LOGGER.warn("El parametro ingresado es invalido ", iae);
            return ResponseEntity.badRequest().body(new CampaignsEmployeeResponse(iae.getMessage()));
        } catch (Exception ex) {
            LOGGER.error("Ocurrio un error al intentar obtener la lista de campanias del empleado {}", idEmployee, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CampaignsEmployeeResponse(ex.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PutMapping(
            value = "employee/campaign/active",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "La actualizacion se realizo con exito", response = CampaignActiveResponse.class),
            @ApiResponse(code = 400, message = "Argumentos inválidos", response = CampaignActiveResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del servicio web", response = CampaignActiveResponse.class)
    })
    public ResponseEntity<CampaignActiveResponse> activeCampaign (@RequestBody CampaignActiveRequest request) {
        try {
            validateActiveCampaignRequest(request);
            return campaignActiveBuilder.apply(request);
        } catch (IllegalArgumentException iae) {
            LOGGER.warn("Los parametros ingresados son invalidos", iae);
            return ResponseEntity.badRequest().body(new CampaignActiveResponse(iae.getMessage()));
        } catch (Exception ex) {
            LOGGER.error("Ocurrió un error al intentar actualizar la campania activa para el empleado id {}, campania id {}, tipo id {}"
                    , request.getIdEmployee()
                    , request.getIdCampaign()
                    , request.getIdType()
                    , ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CampaignActiveResponse(ex.getMessage()));
        }
    }
}
