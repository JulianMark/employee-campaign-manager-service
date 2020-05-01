package com.fundraising.employee.service;

import com.fundraising.employee.model.Campaign;
import com.fundraising.employee.service.http.CampaignsEmployeeResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ObtainCampaignListServiceTest {

    private final Integer VALID_EMPLOYEE_ID = 1;

    static class ParamsArgsSrcIllegalArgsException implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(null, -1, 0).map(Arguments::of);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ParamsArgsSrcIllegalArgsException.class)
    @DisplayName("When parameter is null or less zero should return 400 (Bad Request)")
    public void obtainCampaignList_IdEmployeeIsNullOrLessZero_ReturnsBadRequest(Integer invalidEmployeeId) {
        CampaignService sut = new CampaignService(null, null);

        ResponseEntity<CampaignsEmployeeResponse> responseEntity = sut.obtainCampaignList(invalidEmployeeId);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    @DisplayName("When mapper throws Exception should return 500 (Internal Server Error)")
    public void obtainCampaignList_CampaignsMapperThrowException_ReturnsInternalServerError() {
        Function<Integer, ResponseEntity<CampaignsEmployeeResponse>> builder = param -> ResponseEntity.status(500).build();
        CampaignService sut = new CampaignService(null, builder);

        ResponseEntity<CampaignsEmployeeResponse> responseEntity = sut.obtainCampaignList(VALID_EMPLOYEE_ID);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    @DisplayName("When response is null should return 204 (No Content)")
    public void obtainCampaignList_CampaignsEmployeeResponseIsNull_ReturnsNoContent() {
        Function<Integer, ResponseEntity<CampaignsEmployeeResponse>> builder = param -> ResponseEntity.noContent().build();
        CampaignService sut = new CampaignService(null, builder);

        ResponseEntity<CampaignsEmployeeResponse> responseEntity = sut.obtainCampaignList(VALID_EMPLOYEE_ID);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NO_CONTENT));
    }

    @Test
    @DisplayName("When No Exception is caught should return 200 (OK)")
    public void obtainCampaignList_NoExceptionCaught_ReturnsOk() {
        List<Campaign> campaignList = Arrays.asList(new Campaign(1,1,"City name"));
        Function<Integer, ResponseEntity<CampaignsEmployeeResponse>> builder = param
                -> ResponseEntity.ok(new CampaignsEmployeeResponse(campaignList));
        CampaignService sut = new CampaignService(null, builder);

        ResponseEntity<CampaignsEmployeeResponse> responseEntity = sut.obtainCampaignList(VALID_EMPLOYEE_ID);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getCampaignList().get(0).toString(), is(campaignList.get(0).toString()));
    }
}
