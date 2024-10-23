package com.netmind.APICurso.controllers;

import com.netmind.APICurso.models.CompanyModel;
import com.netmind.APICurso.services.CompanyService;
import com.netmind.APICurso.utils.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apicurso")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyModel>> getCompanies() {
        System.out.println("[CompanyController - getCompanies]");
        return new ResponseEntity<>(companyService.getCompanies(), HttpStatus.OK);
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyModel> getCompanyById(@PathVariable String id) {
        System.out.println("[CompanyController - getCompanyById]");
        System.out.println("[CompanyController - getCompanyById] id: " + id);

        CompanyModel responseBody = companyService.getCompany(id);
        HttpStatus status = Helpers.solveHttpStatus(responseBody);

        return new ResponseEntity<>(
                HttpStatus.OK != status ? null : responseBody,
                status
        );
    }

    @PostMapping("/companies")
    public ResponseEntity<List<CompanyModel>> createCompanies(@RequestBody List<CompanyModel> companyModels) {
        System.out.println("[CompanyController - createCompanies]");

        List<CompanyModel> responseBody = companyService.createCompanies(companyModels);
        HttpStatus status = Helpers.solveHttpStatus(responseBody.get(0));

        return new ResponseEntity<>(
                HttpStatus.OK != status ? null : responseBody,
                HttpStatus.OK != status ? status : HttpStatus.CREATED
        );
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<CompanyModel> updateCompany(
            @PathVariable String id,
            @RequestBody CompanyModel companyModel
    ) {
        System.out.println("[CompanyController - updateCompany]");

        CompanyModel responseBody = companyService.updateCompany(id, companyModel);
        HttpStatus status = Helpers.solveHttpStatus(responseBody);

        return new ResponseEntity<>(
                HttpStatus.OK != status ? null : responseBody,
                status
        );
    }

    @PatchMapping("/companies/{id}")
    public ResponseEntity<CompanyModel> patchCompany(
            @PathVariable String id,
            @RequestBody CompanyModel companyModel
    ) {
        System.out.println("[CompanyController - patchCompany]");

        CompanyModel responseBody = companyService.patchCompany(id, companyModel);
        HttpStatus status = Helpers.solveHttpStatus(responseBody);

        return new ResponseEntity<>(
                HttpStatus.OK != status ? null : responseBody,
                status
        );
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<CompanyModel> deleteCompany(@PathVariable String id) {
        System.out.println("[CompanyController - deleteCompany]");
        System.out.println("[CompanyController - deleteCompany] id: " + id);

        CompanyModel responseBody = companyService.deleteCompany(id);
        HttpStatus status = Helpers.solveHttpStatus(responseBody);

        return new ResponseEntity<>(
                HttpStatus.OK != status ? null : responseBody,
                status
        );
    }
}