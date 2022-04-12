package com.mypractice.controller;

import com.mypractice.common.CommonUtil;
import com.mypractice.model.dto.atms.OpenBankingATM;
import com.mypractice.sevices.ATMService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
class ATMControllerTest {

    @InjectMocks
    ATMController atmController;
    @Mock
    ATMService atmService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAtmsDetails() {

        var atms = CommonUtil.loadFile("atm.json", OpenBankingATM.class,  getClass().getClassLoader());
        Mockito.when(atmService.getAtmsDetails()).thenReturn(atms);
        var response = atmController.getAllAtmsDetails();
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(atms, response.getBody());
    }
}