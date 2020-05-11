package com.fundraising.employee.service;

import com.fundraising.employee.service.http.CampaignResponse;
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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Obtain Campaign Service")
class ObtainCampaignServiceTest {

    private Integer VALID_CAMPAIGN_ID = 1;

    static class ParametersArgumentsSource implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(null, 0, -1).map(Arguments::of);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ParametersArgumentsSource.class)
    @DisplayName("When parameter is null or less zero should return 400 (Bad Request)")
    public void obtainCampaign_IdCampaignIsNullOrLessZero_ReturnsBadRequest(Integer idCampaign) {
        CampaignService sut = new CampaignService(null,null, null);

        ResponseEntity<CampaignResponse> responseEntity = sut.obtainCampaign(idCampaign);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    @DisplayName("When mapper throws Exception should return 500 (Internal Server Error)")
    public void obtainCampaign_CampaignMapperThrowException_ReturnsInternalServerError() {
        Function<Integer, ResponseEntity<CampaignResponse>> builder = param
                -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CampaignResponse());
        CampaignService sut = new CampaignService(builder,null, null);

        ResponseEntity<CampaignResponse> responseEntity = sut.obtainCampaign(VALID_CAMPAIGN_ID);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    @DisplayName("When response is null should return 204 (No Content)")
    public void obtainCampaign_CampaignResponseIsNull_ReturnsNoContent() {
        Function<Integer, ResponseEntity<CampaignResponse>> builder = param -> ResponseEntity.noContent().build();
        CampaignService sut = new CampaignService(builder, null, null);

        ResponseEntity<CampaignResponse> responseEntity = sut.obtainCampaign(VALID_CAMPAIGN_ID);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NO_CONTENT));
    }

    @Test
    @DisplayName("When No Exception is caught should return 200 (OK)")
    public void obtainCampaign_NoExceptionCaught_ReturnsOk() {
        CampaignResponse responseExpected = new CampaignResponse("City name", 1,
                "OSC name", 1, "Type campaign", null);
        Function<Integer, ResponseEntity<CampaignResponse>> builder = param -> ResponseEntity.ok(responseExpected);
        CampaignService sut = new CampaignService(builder, null, null);

        ResponseEntity<CampaignResponse> responseEntity = sut.obtainCampaign(VALID_CAMPAIGN_ID);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().toString(), is(responseExpected.toString()));
    }
}