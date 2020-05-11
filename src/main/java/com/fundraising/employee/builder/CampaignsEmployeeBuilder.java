package com.fundraising.employee.builder;

import com.fundraising.employee.mapper.CampaignsEmployeeMapper;
import com.fundraising.employee.service.http.CampaignsEmployeeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class CampaignsEmployeeBuilder implements Function<Integer , ResponseEntity<CampaignsEmployeeResponse>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignsEmployeeBuilder.class);
    private final CampaignsEmployeeMapper campaignsEmployeeMapper;

    public CampaignsEmployeeBuilder(CampaignsEmployeeMapper campaignsEmployeeMapper) {
        this.campaignsEmployeeMapper = campaignsEmployeeMapper;
    }

    @Override
    public ResponseEntity<CampaignsEmployeeResponse> apply(Integer idEmployee) {
        return Optional.ofNullable(campaignsEmployeeMapper.obtainCampaignList(idEmployee))
                .filter(campaigns -> !campaigns.isEmpty())
                .map(campaigns -> {
                    CampaignsEmployeeResponse campaignsEmployeeResponse = new CampaignsEmployeeResponse(campaigns);
                    LOGGER.info("Se obtuvo la lista de campanias para el empleado {}", idEmployee);
                    return ResponseEntity.ok(campaignsEmployeeResponse);
                })
                .orElseGet(() -> {
                    LOGGER.warn("No se encontro la lista de campanias para el empleado {}", idEmployee);
                    return ResponseEntity.noContent().build();
                });
    }
}
