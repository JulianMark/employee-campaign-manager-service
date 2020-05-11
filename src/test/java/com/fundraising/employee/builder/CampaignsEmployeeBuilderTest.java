package com.fundraising.employee.builder;

import com.fundraising.employee.mapper.CampaignsEmployeeMapper;
import com.fundraising.employee.model.Campaign;
import com.fundraising.employee.service.http.CampaignsEmployeeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@DisplayName("Campaigns Employee Builder")
class CampaignsEmployeeBuilderTest {

    private final Integer VALID_ID_EMPLOYEE_WITH_CAMPAIGNS = 1;
    private final Integer VALID_ID_EMPLOYEE_WITHOUT_CAMPAIGNS = 4;

    @Mock
    private CampaignsEmployeeMapper campaignsEmployeeMapper;

    @InjectMocks
    private CampaignsEmployeeBuilder sut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("When mapper returns empty list should return status code 204 (No Content)")
    public void apply_MapperReturnsEmptyList_ReturnsNoContent () {

        when(campaignsEmployeeMapper.obtainCampaignList(VALID_ID_EMPLOYEE_WITHOUT_CAMPAIGNS))
                .thenReturn(Collections.EMPTY_LIST);

        ResponseEntity<CampaignsEmployeeResponse> responseEntity = sut.apply(VALID_ID_EMPLOYEE_WITHOUT_CAMPAIGNS);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NO_CONTENT));
    }

    @Test
    @DisplayName("When mapper returns list with campaigns should return status code 200 (OK)")
    public void apply_MapperReturnsListWithCampaigns_ReturnsOk () {

        when(campaignsEmployeeMapper.obtainCampaignList(VALID_ID_EMPLOYEE_WITH_CAMPAIGNS))
                .thenReturn(Arrays.asList(new Campaign()));

        ResponseEntity<CampaignsEmployeeResponse> responseEntity = sut.apply(VALID_ID_EMPLOYEE_WITH_CAMPAIGNS);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }
}