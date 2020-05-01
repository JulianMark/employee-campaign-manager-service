package com.fundraising.employee.mapper;


import com.fundraising.employee.model.Campaign;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CampaignsEmployeeMapper {

    List<Campaign> obtainCampaignList(@Param("idEmployee") Integer idEmployee);
}
