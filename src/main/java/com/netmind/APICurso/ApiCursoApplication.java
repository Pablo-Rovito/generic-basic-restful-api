package com.netmind.APICurso;

import com.netmind.APICurso.models.CompanyModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ApiCursoApplication {

	public static List<CompanyModel> companyModels = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(ApiCursoApplication.class, args);
		ApiCursoApplication.companyModels = ApiCursoApplication.generateModels();
	}

	private static ArrayList<CompanyModel> generateModels() {
		System.out.println("[ApiCursoApplication - generateModels");

		ArrayList<CompanyModel> companyModels = new ArrayList<>();

		companyModels.add(new CompanyModel("1", "Compañía 1", 100));
		companyModels.add(new CompanyModel("2", "Compañía 2", 200));
		companyModels.add(new CompanyModel("3", "Compañía 3", 300));

		return companyModels;
	}
}