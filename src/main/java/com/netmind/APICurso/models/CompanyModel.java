package com.netmind.APICurso.models;

public class CompanyModel {
    private String id;
    private String name;
    private int employeeNumber;

    public CompanyModel() {
    }

    public CompanyModel(String id, String name, int employeeNumber) {
        this.id = id;
        this.name = name;
        this.employeeNumber = employeeNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
