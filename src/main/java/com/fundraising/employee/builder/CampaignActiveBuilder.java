package com.fundraising.employee.builder;

import com.fundraising.employee.mapper.CampaignActiveMapper;
import com.fundraising.employee.service.http.CampaignActiveRequest;
import com.fundraising.employee.service.http.CampaignActiveResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CampaignActiveBuilder implements Function <CampaignActiveRequest, ResponseEntity<CampaignActiveResponse>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignActiveBuilder.class);
    private final CampaignActiveMapper campaignActiveMapper;

    @Autowired
    public CampaignActiveBuilder(CampaignActiveMapper campaignActiveMapper) {
        this.campaignActiveMapper = campaignActiveMapper;
    }

    @Override
    public ResponseEntity<CampaignActiveResponse> apply(CampaignActiveRequest request) {
        campaignActiveMapper.activeCampaign(request);
        LOGGER.info("Se activo la campania id {} con exito para el empleado {}, de tipo {}"
        , request.getIdCampaign()
        , request.getIdEmployee()
        , request.getIdType());
        return ResponseEntity.ok(new CampaignActiveResponse(0));
    }
}
