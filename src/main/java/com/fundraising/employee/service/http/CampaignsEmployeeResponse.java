package com.fundraising.employee.service.http;

import com.fundraising.employee.model.Campaign;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CampaignsEmployeeResponse {

    List<Campaign> campaignList;
    private String errorMessage;

    public CampaignsEmployeeResponse(List<Campaign> campaignList) {
        this.campaignList = campaignList;
    }

    public CampaignsEmployeeResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
