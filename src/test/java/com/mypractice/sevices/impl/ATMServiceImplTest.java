package com.mypractice.sevices.impl;

import com.mypractice.common.CommonUtil;
import com.mypractice.model.dto.atms.OpenBankingATM;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringRunner.class)
class ATMServiceImplTest {

    @InjectMocks
    ATMServiceImpl atmService;
    @Mock
    ResourceLoader resourceLoader;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(atmService, "atmFilePath", "atm.json");

    }

    @Test
    void getAtmsDetails() {

        var atms = CommonUtil.loadFile("atm.json", OpenBankingATM.class,  getClass().getClassLoader());
        Mockito.when(resourceLoader.getResource("classpath:" + "atm.json")).thenReturn(new DefaultResourceLoader().getResource("classpath:" + "atm.json"));
        var atmsDetails = atmService.getAtmsDetails();
        Assert.assertEquals(atms, atmsDetails);

    }

}