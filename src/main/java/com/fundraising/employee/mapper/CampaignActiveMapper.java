package com.fundraising.employee.mapper;

import com.fundraising.employee.service.http.CampaignActiveRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CampaignActiveMapper {

    void activeCampaign(@Param("request") CampaignActiveRequest request);
}
