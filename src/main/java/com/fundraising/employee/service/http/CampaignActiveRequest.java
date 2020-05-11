package com.fundraising.employee.service.http;

import io.swagger.annotations.ApiModelProperty;
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
public class CampaignActiveRequest {

    @ApiModelProperty(notes = "Id del empleado", required = true, example = "1")
    private Integer idEmployee;
    @ApiModelProperty(notes = "Id de la campania", required = true, example = "1")
    private Integer idCampaign;
    @ApiModelProperty(notes = "Id del tipo de empleado", required = true, example = "1")
    private Integer idType;
}
