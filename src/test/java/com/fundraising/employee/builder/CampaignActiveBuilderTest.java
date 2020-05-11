package com.fundraising.employee.builder;

import com.fundraising.employee.mapper.CampaignActiveMapper;
import com.fundraising.employee.service.http.CampaignActiveRequest;
import com.fundraising.employee.service.http.CampaignActiveResponse;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@DisplayName("Active Campaign Employee Builder")
class CampaignActiveBuilderTest {

    @Mock
    private CampaignActiveMapper campaignActiveMapper;

    @InjectMocks
    private CampaignActiveBuilder sut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("When mapper active campaign should return status code 200 (OK)")
    public void apply_MapperActiveCampaign_ReturnsOk () {

        doNothing().when(campaignActiveMapper).activeCampaign(any());

        ResponseEntity<CampaignActiveResponse> responseEntity = sut.apply(new CampaignActiveRequest(1, 1, 1));

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getResult(), is(0));
    }
}