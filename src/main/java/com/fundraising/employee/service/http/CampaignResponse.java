package com.fundraising.employee.service.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CampaignResponse {

    private String name;
    private Integer idOSC;
    private String osc;
    private Integer idType;
    private String type;
    private String errorMessage;

    public CampaignResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
