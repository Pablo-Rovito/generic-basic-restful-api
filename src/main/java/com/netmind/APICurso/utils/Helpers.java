package com.netmind.APICurso.utils;

import com.netmind.APICurso.models.CompanyModel;
import org.springframework.http.HttpStatus;

public class Helpers {
    public static HttpStatus solveHttpStatus(CompanyModel companyModel) {
        System.out.println("[Helpers - solveHttpStatus]");
        return switch (companyModel.getId()) {
            case "404" -> HttpStatus.NOT_FOUND;
            case "400" -> HttpStatus.BAD_REQUEST;
            case null -> HttpStatus.INTERNAL_SERVER_ERROR;
            default -> HttpStatus.OK;
        };
    }
}
