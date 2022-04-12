package com.mypractice.sevices.impl;

import com.mypractice.model.dto.branches.OpenBankingBranch;
import com.mypractice.sevices.BranchService;
import com.mypractice.util.FileToDtoConvertorUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class BranchServiceImpl implements BranchService {


    @Value("${resources.branchfilename}")
    private String branchFilePath;

    private final  ResourceLoader resourceLoader;

    /**
     * @param resourceLoader
     */
    @Autowired
    public BranchServiceImpl(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * @return
     */
    @Override
    public OpenBankingBranch getAllBranches() {



        return FileToDtoConvertorUtil.loadFile(resourceLoader, OpenBankingBranch.class, branchFilePath);
    }





}
