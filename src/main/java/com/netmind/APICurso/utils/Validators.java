package com.netmind.APICurso.utils;

import com.netmind.APICurso.models.CompanyModel;

public class Validators {
    public static boolean isNullOrEmpty(String str) {
        System.out.println("[Validators - isNullOrEmpty]");
        return str == null || str.isEmpty();
    }

    public static boolean isCompanyValid(CompanyModel company) {
        System.out.println("[Validators - isCompanyValid]");
        return !isNullOrEmpty(company.getId()) && !isNullOrEmpty(company.getName()) && company.getEmployeeNumber() > 0;
    }
}
