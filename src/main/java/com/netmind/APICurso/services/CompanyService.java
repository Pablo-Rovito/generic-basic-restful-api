package com.netmind.APICurso.services;

import com.netmind.APICurso.ApiCursoApplication;
import com.netmind.APICurso.models.CompanyModel;
import com.netmind.APICurso.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.netmind.APICurso.utils.Validators.isCompanyValid;
import static com.netmind.APICurso.utils.Validators.isNullOrEmpty;

@Service
public class CompanyService {
    public List<CompanyModel> getCompanies() {
        System.out.println("[CompanyService - getCompanies]");
        return ApiCursoApplication.companyModels;
    }

    public CompanyModel getCompany(String id) {
        System.out.println("[CompanyService - getCompany]");
        System.out.println("[CompanyService - getCompany] id: " + id);

        return ApiCursoApplication.companyModels.stream()
                .filter(c -> id.equals(c.getId()))
                .findFirst()
                .orElse(this.notSuccessResponse(id, Constants.NOT_FOUND));
    }

    public List<CompanyModel> createCompanies(List<CompanyModel> companyModels) {
        System.out.println("[CompanyService - createCompanies]");
        for(CompanyModel companyModel : companyModels) {
            System.out.println("[CompanyService - createCompanies] id: " + companyModel.getId());
            System.out.println("[CompanyService - createCompanies] name: " + companyModel.getName());
            System.out.println("[CompanyService - createCompanies] empleados: " + companyModel.getEmployeeNumber());

            if(!isCompanyValid(companyModel)) {
                return Collections.singletonList(this.notSuccessResponse(companyModel.getId(), Constants.BAD_REQUEST));
            }
        }

        ApiCursoApplication.companyModels.addAll(companyModels);
        System.out.println("[CompanyService - createCompanies] resources added with ids: " + this.extractIds(companyModels));

        return companyModels;
    }

    public CompanyModel updateCompany(String id, CompanyModel companyModel) {
        System.out.println("[CompanyService - updateCompany]");
        System.out.println("[CompanyService - updateCompany] id: " + id);
        System.out.println("[CompanyService - updateCompany] name: " + companyModel.getName());
        System.out.println("[CompanyService - updateCompany] empleados: " + companyModel.getEmployeeNumber());

        if(!isCompanyValid(companyModel)) {
            return this.notSuccessResponse(id, Constants.BAD_REQUEST);
        }

        CompanyModel companyToUpdate = this.getModel(id);
        if(Constants.NOT_FOUND_ID.equals(companyToUpdate.getId())) {
            return companyToUpdate;
        }

        this.deleteModels(id);
        ApiCursoApplication.companyModels.add(companyModel);
        System.out.println("[CompanyService - updateCompany] resource updated");

        return companyModel;
    }

    public CompanyModel patchCompany(String id, CompanyModel companyModel) {
        System.out.println("[CompanyService - patchCompany]");
        System.out.println("[CompanyService - patchCompany] id: " + id);
        System.out.println("[CompanyService - patchCompany] name: " + companyModel.getName());
        System.out.println("[CompanyService - patchCompany] empleados: " + companyModel.getEmployeeNumber());

        if(isNullOrEmpty(companyModel.getId()) && isNullOrEmpty(companyModel.getName()) && companyModel.getEmployeeNumber() <= 0) {
            return this.notSuccessResponse(id, Constants.BAD_REQUEST);
        }

        CompanyModel companyToPatch = this.getModel(id);
        if(Constants.NOT_FOUND_ID.equals(companyToPatch.getId())) {
            return companyToPatch;
        }

        companyModel.setId(id);

        if(!isNullOrEmpty(companyModel.getName())) {
            companyToPatch.setName(companyModel.getName());
        }

        if(companyModel.getEmployeeNumber() > 0) {
            companyToPatch.setEmployeeNumber(companyModel.getEmployeeNumber());
        }

        System.out.println("[CompanyService - patchCompany] resource patched");

        return companyModel;
    }

    public CompanyModel deleteCompany(String id) {
        System.out.println("[CompanyService - deleteCompany]");
        System.out.println("[CompanyService - deleteCompany] id: " + id);

        CompanyModel companyToDelete = this.getModel(id);
        if(Constants.NOT_FOUND_ID.equals(companyToDelete.getId())) {
            return companyToDelete;
        }

        this.deleteModels(id);
        System.out.println("[CompanyService - deleteCompany] resource deleted");

        return companyToDelete;
    }

    private CompanyModel notSuccessResponse(String id, String type) {
        System.out.println("[CompanyService - notSuccessResponse]");
        System.out.println("[CompanyService - notSuccessResponse] id: " + id);
        System.out.println("[CompanyService - notSuccessResponse] type: " + type);

        switch (type) {
            case Constants.BAD_REQUEST -> {
                System.out.println("[CompanyService - notSuccessResponse] error de parámetros");
                return new CompanyModel("400", Constants.BAD_REQUEST_MESSAGE, 400);
            }
            case Constants.NOT_FOUND -> {
                System.out.println("[CompanyService - notSuccessResponse] objeto no encontrado");
                return new CompanyModel("404", Constants.NOT_FOUND_MESSAGE, 404);
            }
            default -> {
                System.out.println("[CompanyService - notSuccessResponse] objeto vacío por defecto");
                return new CompanyModel();
            }
        }
    }

    private void deleteModels(String id) {
        System.out.println("[CompanyService - deleteModels]");
        System.out.println("[CompanyService - deleteModels] id: " + id);
        ApiCursoApplication.companyModels = ApiCursoApplication.companyModels.stream()
                .filter(c -> !id.equals(c.getId()))
                .collect(Collectors.toList());
    }

    private CompanyModel getModel(String id) {
        System.out.println("[CompanyService - getModel]");
        System.out.println("[CompanyService - getModel] id: " + id);
        return ApiCursoApplication.companyModels.stream()
                .filter(c -> id.equals(c.getId()))
                .findFirst()
                .orElse(this.notSuccessResponse(id, Constants.NOT_FOUND));
    }

    private String extractIds(List<CompanyModel> companies) {
        System.out.println("[CompanyService - extractIds]");
        StringBuilder ids = new StringBuilder();
        for(CompanyModel company : companies) {
            ids.append(" ").append(company.getId());
        }
        return ids.toString().trim();
    }
}
