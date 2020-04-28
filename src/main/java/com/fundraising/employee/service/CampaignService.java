package com.fundraising.employee.service;

import com.fundraising.employee.mapper.CampaignMapper;
import com.fundraising.employee.service.http.CampaignResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CampaignService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignService.class);
    private final CampaignMapper campaignMapper;

    @Autowired
    public CampaignService(CampaignMapper campaignMapper) {
        this.campaignMapper = campaignMapper;
    }

    @GetMapping(
            value = "employee/campaign/{idCampaign}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CampaignResponse> obtainCampaign(@PathVariable Integer idCampaign) {
        try {
            Optional.ofNullable(idCampaign)
                    .map(integer -> integer < 1 ? null : integer)
                    .orElseThrow(IllegalArgumentException::new);
            return Optional.ofNullable(campaignMapper.obtainCampaign(idCampaign))
                    .map(campaignResponse -> {
                        LOGGER.info("Se obtuvieron los datos para la campania {}", campaignResponse.getName());
                        return ResponseEntity.ok(campaignResponse);
                    }).orElseGet(() -> {
                        LOGGER.warn("No se encontraron los datos para la campania {}", idCampaign);
                        return ResponseEntity.noContent().build();
                    });
        } catch (IllegalArgumentException iae) {
            LOGGER.warn("El parametro ingresado es invalido ", iae);
            return ResponseEntity.badRequest().body(new CampaignResponse(iae.getMessage()));
        } catch (Exception ex) {
            LOGGER.error("Ocurrio un error al intentar obtener la campania {}", idCampaign , ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CampaignResponse(ex.getMessage()));
        }
    }
}
