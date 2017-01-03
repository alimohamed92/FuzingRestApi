package org.esir.AliMax.FuzingRestApi;
import io.swagger.parser.SwaggerParser;

import java.util.Map;
import java.util.Scanner;

import org.esir.AliMax.FuzingRestApi.oracle.ModelTest;
import org.esir.AliMax.FuzingRestApi.oracle.TestBadParam;
import org.esir.AliMax.FuzingRestApi.oracle.TestBadUrl;
import org.esir.AliMax.FuzingRestApi.oracle.TestTypeRetour;
import org.esir.AliMax.FuzingRestApi.oracle.VerifyCode;

import io.swagger.models.Model;
import io.swagger.models.Path;
import io.swagger.models.Swagger;


public class TestSwagger {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		System.out.println(input);
		Swagger swagger = new SwaggerParser().read(input);
		Map<String, Model> defs = swagger.getDefinitions();
		String baseUrl = swagger.getBasePath();
		Map<String,Path> paths = swagger.getPaths();
		ModelTest test = null;
		String url ="http://localhost:8080";
		//test VerifCode
		url+=baseUrl;
		test = new VerifyCode(paths);
		String rapport = test.generateReport(url);
		
		//test BAD URL
		 test = new TestBadUrl(5, paths);
		 rapport+= test.generateReport(url);
		 
		 //test badPAram
		 test = new TestBadParam(paths);
		 rapport+=test.generateReport(url);
		 
		 //test Verif type
		 test = new TestTypeRetour(paths,defs);
		 rapport+=test.generateReport(url);
		 App.generatehtml(rapport, "rapport.html");
		 
		 
	}
}
