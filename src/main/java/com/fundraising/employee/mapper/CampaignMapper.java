package com.fundraising.employee.mapper;

import com.fundraising.employee.service.http.CampaignResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CampaignMapper {

    CampaignResponse obtainCampaign(@Param("idCampaign") Integer idCampaign );
}
