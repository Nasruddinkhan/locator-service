package com.mypractice.sevices.impl;

import com.mypractice.common.CommonUtil;
import com.mypractice.model.dto.branches.OpenBankingBranch;
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
class BranchServiceImplTest {

    @InjectMocks
    BranchServiceImpl branchService;

    @Mock
    ResourceLoader resourceLoader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(branchService, "branchFilePath", "branches.json");

    }


    @Test
     void getAllBranches() {
        var fileName = "branches.json";
        var branches = CommonUtil.loadFile(fileName, OpenBankingBranch.class,  getClass().getClassLoader());
        var classpath ="classpath:" + fileName;
        Mockito.when(resourceLoader.getResource(classpath)).thenReturn(new DefaultResourceLoader().getResource(classpath));
        var branchesDetals = branchService.getAllBranches();
        Assert.assertEquals(branchesDetals, branches);
    }


}