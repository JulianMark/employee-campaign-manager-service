package com.fundraising.employee.builder;

import com.fundraising.employee.mapper.CampaignMapper;
import com.fundraising.employee.service.http.CampaignResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@DisplayName("Employee Campaign Builder")
class CampaignBuilderTest {

    private final Integer VALID__CAMPAIGN_ID = 1;
    private final Integer INVALID_CAMPAIGN_ID = 4;

    @Mock
    private CampaignMapper campaignMapper;

    @InjectMocks
    private CampaignBuilder sut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("When mapper not returns campaign should return status code 204 (No Content)")
    public void apply_MapperNotReturnsCampaign_ReturnsNoContent () {

        when(campaignMapper.obtainCampaign(INVALID_CAMPAIGN_ID))
                .thenReturn(null);

        ResponseEntity<CampaignResponse> responseEntity = sut.apply(INVALID_CAMPAIGN_ID);

        assertThat(responseEntity.getStatusCode(), is (HttpStatus.NO_CONTENT));
    }

    @Test
    @DisplayName("When mapper returns campaign should return status code 200 (OK)")
    public void apply_MapperReturnsCampaign_ReturnsOk () {

        when(campaignMapper.obtainCampaign(VALID__CAMPAIGN_ID))
                .thenReturn(new CampaignResponse());

        ResponseEntity<CampaignResponse> responseEntity = sut.apply(VALID__CAMPAIGN_ID);

        assertThat(responseEntity.getStatusCode(), is (HttpStatus.OK));
    }
}