package com.mypractice.sevices.impl;

import com.mypractice.model.dto.atms.OpenBankingATM;
import com.mypractice.sevices.ATMService;
import com.mypractice.util.FileToDtoConvertorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;


@Service
public class ATMServiceImpl implements ATMService {

    @Autowired
    private final transient ResourceLoader resourceLoader;
    @Value("${resources.atmsfilename}")
    private String atmFilePath;

    public ATMServiceImpl(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public OpenBankingATM getAtmsDetails() {
        return FileToDtoConvertorUtil.loadFile(resourceLoader, OpenBankingATM.class, atmFilePath);

    }
}
