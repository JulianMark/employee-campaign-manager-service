package com.fundraising.employee.service.http;

import com.fundraising.employee.model.Campaign;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(notes = "Resultado de la consulta ejecutada exito = lista de empleados")
    List<Campaign> campaignList;
    @ApiModelProperty(notes = "Resultado de la consulta ejecutada exito = mensaje de error")
    private String errorMessage;

    public CampaignsEmployeeResponse(List<Campaign> campaignList) {
        this.campaignList = campaignList;
    }

    public CampaignsEmployeeResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
