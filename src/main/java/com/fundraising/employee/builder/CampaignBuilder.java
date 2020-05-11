package com.fundraising.employee.builder;

import com.fundraising.employee.mapper.CampaignMapper;
import com.fundraising.employee.service.http.CampaignResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class CampaignBuilder implements Function<Integer, ResponseEntity<CampaignResponse>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignBuilder.class);
    private final CampaignMapper campaignMapper;

    @Autowired
    public CampaignBuilder(CampaignMapper campaignMapper) {
        this.campaignMapper = campaignMapper;
    }

    @Override
    public ResponseEntity<CampaignResponse> apply(Integer idCampaign) {

        return Optional.ofNullable(campaignMapper.obtainCampaign(idCampaign))
                .map(campaignResponse -> {
                    LOGGER.info("Se obtuvieron los datos para la campania {}", campaignResponse.getName());
                    return ResponseEntity.ok(campaignResponse);
                }).orElseGet(() -> {
                    LOGGER.warn("No se encontraron los datos para la campania {}", idCampaign);
                    return ResponseEntity.noContent().build();
                });
    }
}
