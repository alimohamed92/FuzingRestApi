package org.esir.AliMax.FuzingRestApi;
import io.swagger.parser.SwaggerParser;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.methods.GetMethod;
import org.esir.AliMax.FuzingRestApi.model.ModelTest;
import org.esir.AliMax.FuzingRestApi.model.TestBadParam;
import org.esir.AliMax.FuzingRestApi.model.TestBadUrl;
import org.esir.AliMax.FuzingRestApi.model.VerifyCode;
import org.apache.commons.httpclient.*;
import io.swagger.models.Path;
import io.swagger.models.Response;
import io.swagger.models.Swagger;


public class TestSwagger {

	public static void main(String[] args) {
		//System.out.println("response "+get("http://www.w3schools.com/angular/customers_mysql.php")); http://petstore.swagger.io/v2/swagger.json
		Swagger swagger = new SwaggerParser().read("swagger.json");
		System.out.println("********** Bonjour ****** "+swagger.getBasePath()+swagger.getPaths().keySet());
		String baseUrl = swagger.getBasePath();
		Map<String,Path> paths = swagger.getPaths();
		ModelTest test = null;//new VerifyCode(paths);
		String url ="http://www.w3schools.com/angular/customers_mysql.php";
		//System.out.println(test.generateReport(/*baseUrl*/url));
		 test = new TestBadUrl(5, paths);
		System.out.println("############BadURL\n"+test.generateReport(/*baseUrl*/url));
		
		//test = new TestBadParam(paths);
		//System.out.println("!!!!!!!\n"+test.generateReport(/*baseUrl*/url));
		/*for(Map.Entry<String, Path> entr : paths.entrySet()){
			if(p.getGet() !=null)
				System.out.println(" $${ \n Opérations : "+p.getOperations()+
						", Param : "+p.getGet().getParameters()+",\n Summary :"+p.getGet().getSummary()+
						",\n Response : "+p.getGet().getResponses().get("200").getSchema().getType()+" --> "+ 
						"}");
			break;
			System.out.println(test.generateReport(baseUrl+entr.getKey()url,entr.getValue()));
 			Path p = entr.getValue();
 		if(p.getGet()!=null) {
 				Map<String, Response> resKey = p.getGet().getResponses();
 			System.out.println("$ "+resKey.containsKey("200")+" "+resKey.containsKey("404")+" "+resKey.containsKey("default")); 
 			}
		}*/

 		//HttpClient client = new HttpClient();
	    //String url1 = "http://www.w3schoolsss.com/angular/customers_mysql.php";
////		io.swagger.io.HttpClient clt = new io.swagger.io.HttpClient(url);
////		clt.execute();
// 		GetMethod method = new GetMethod(url1);
// 		try {
//			int statuCode = client.executeMethod(method);
//			System.out.println("**** Response "+new String(method.getResponseBody()) +" \n"+statuCode);
//		} catch (HttpException e) {
//			System.out.println("3 *****************404 "+e.getMessage());
//		} catch (IOException e) {
//			System.out.println("2 *****************404 "+e.getMessage());
//		}
//		System.out.println(statuCode);
		
	}
}
