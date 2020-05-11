package com.fundraising.employee.service;

import com.fundraising.employee.service.http.CampaignActiveRequest;
import com.fundraising.employee.service.http.CampaignActiveResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Active Campaign Service")
class ActiveCampaignServiceTest {

    private CampaignActiveRequest VALID_REQUEST = new CampaignActiveRequest(1,1,1);

    static class ParametersArgumentSource implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext){
            return Stream.of(
                    null,
                    new CampaignActiveRequest(null, null, null),
                    new CampaignActiveRequest(1, null, null),
                    new CampaignActiveRequest(1, 2, null)
            ).map(Arguments::of);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ParametersArgumentSource.class)
    @DisplayName("When parameter is null or less zero should return 400 (Bad Request)")
    public void activeCampaign_RequestIsNullOrNullsParam_ReturnsBadRequest (CampaignActiveRequest request){
        CampaignService sut = new CampaignService(null, null , null);
        ResponseEntity<CampaignActiveResponse> responseEntity = sut.activeCampaign(request);
        assertThat(responseEntity.getStatusCode() , is(HttpStatus.BAD_REQUEST));
    }

    @Test
    @DisplayName("When mapper throws Exception should return 500 (Internal Server Error)")
    public void activeCampaign_CampaignActiveMapperThrowException_ReturnsInternalServerError() {
        Function<CampaignActiveRequest, ResponseEntity<CampaignActiveResponse>> builder = param
                -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        CampaignService sut = new CampaignService(null, null, builder);

        ResponseEntity<CampaignActiveResponse> responseEntity = sut.activeCampaign(VALID_REQUEST);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    @DisplayName("When No Exception is caught should return 200 (OK)")
    public void activeCampaign_NoExceptionCaught_ReturnsOk() {
        Function<CampaignActiveRequest, ResponseEntity<CampaignActiveResponse>> builder = param
                -> ResponseEntity.ok(new CampaignActiveResponse(0));
        CampaignService sut = new CampaignService(null, null, builder);

        ResponseEntity<CampaignActiveResponse> responseEntity = sut.activeCampaign(VALID_REQUEST);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getResult(), is (0));
    }
}