package org.esir.AliMax.FuzingRestApi;
import io.swagger.parser.SwaggerParser;
import io.swagger.models.Path;
import io.swagger.models.Swagger;

public class TestSwagger {
	 
	 public static void main(String[] args){
		 Swagger swagger = new SwaggerParser().read("swagger.json");
		 System.out.println("**********Bonjour "+swagger.getBasePath());
		 
		 for(Path p : swagger.getPaths().values()){
			 //System.out.print("$$ "+p.);
			 if(p.getGet() !=null)
			 System.out.println(" $${ Param : "+p.getGet().getParameters()+",\n Summary :"+p.getGet().getSummary()+",\n Response : "+p.getGet().getResponses()+"}");
		 }
	 }
}
