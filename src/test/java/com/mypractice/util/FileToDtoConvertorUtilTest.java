package com.mypractice.util;

import com.mypractice.common.CommonUtil;
import com.mypractice.model.dto.atms.OpenBankingATM;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
public class FileToDtoConvertorUtilTest {
    @Mock
    ResourceLoader resourceLoader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }


    @Test
    public void loadFile() {
        var atms = CommonUtil.loadFile("atm.json", OpenBankingATM.class,  getClass().getClassLoader());
        Mockito.when(resourceLoader.getResource("classpath:" + "atm.json")).thenReturn(new DefaultResourceLoader().getResource("classpath:" + "atm.json"));
        var fileData = FileToDtoConvertorUtil.loadFile(resourceLoader, OpenBankingATM.class, "atm.json");
        Assert.assertEquals(atms, fileData);
    }


    @Test
    public void mapObject() {
    }
}