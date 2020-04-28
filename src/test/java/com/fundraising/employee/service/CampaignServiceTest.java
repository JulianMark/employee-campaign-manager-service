package com.fundraising.employee.service;

import com.fundraising.employee.mapper.CampaignMapper;
import com.fundraising.employee.service.http.CampaignResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class CampaignServiceTest {

    @Mock
    private CampaignMapper campaignMapper;

    @InjectMocks
    private CampaignService sut;

    private Integer VALID_CAMPAIGN_ID = 1;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    static class ParametersArgumentsSource implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(null, 0, -1).map(Arguments::of);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ParametersArgumentsSource.class)
    public void obtainCampaign_IdCampaignIsNullOrLessZero_ReturnsBadRequest(Integer idCampaign) {
        ResponseEntity<CampaignResponse> responseEntity = sut.obtainCampaign(idCampaign);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void obtainCampaign_CampaignMapperThrowException_ReturnsInternalServerError() {
        doThrow(RuntimeException.class).when(campaignMapper).obtainCampaign(VALID_CAMPAIGN_ID);
        ResponseEntity<CampaignResponse> responseEntity = sut.obtainCampaign(VALID_CAMPAIGN_ID);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    public void obtainCampaign_CampaignResponseIsNull_ReturnsNoContent() {
        when(campaignMapper.obtainCampaign(VALID_CAMPAIGN_ID)).thenReturn(null);
        ResponseEntity<CampaignResponse> responseEntity = sut.obtainCampaign(VALID_CAMPAIGN_ID);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NO_CONTENT));
    }
    @Test
    public void obtainCampaign_NoExceptionCaught_ReturnsOk() {
        CampaignResponse responseExpected = new CampaignResponse("City name", 1, "OSC name", 1, "Type campaign", null);
        when(campaignMapper.obtainCampaign(VALID_CAMPAIGN_ID)).thenReturn(responseExpected);
        ResponseEntity<CampaignResponse> responseEntity = sut.obtainCampaign(VALID_CAMPAIGN_ID);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().toString(), is(responseExpected.toString()));
    }
}