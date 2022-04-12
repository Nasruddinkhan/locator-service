package com.mypractice.controller;

import com.mypractice.model.dto.branches.OpenBankingBranch;
import com.mypractice.sevices.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/v1.0/open-data")
public class BranchController {

    /**
     *
     * @param branchService
     */
    private final transient BranchService branchService;

    /**
     *
     * @param branchService
     */
    @Autowired
    public BranchController(final BranchService branchService) {
        this.branchService = branchService;
    }

    /**
     *
     * @return
     */
    @GetMapping("/branches")
    public ResponseEntity<OpenBankingBranch> getAllBranches() {
        return new ResponseEntity<>(branchService.getAllBranches(), HttpStatus.OK);
    }
}
