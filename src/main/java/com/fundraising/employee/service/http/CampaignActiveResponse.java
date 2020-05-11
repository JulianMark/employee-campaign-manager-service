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
public class CampaignActiveResponse {

    @ApiModelProperty(notes = "Resultado de la consulta ejecutada exito = camapaña activa")
    private Integer result;
    @ApiModelProperty(notes = "Mensaje de error, en caso de que falle el WS")
    private String errorMessage;

    public CampaignActiveResponse(Integer result) {
        this.result = result;
    }

    public CampaignActiveResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
