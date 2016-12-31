package org.esir.AliMax.FuzingRestApi;
import io.swagger.parser.SwaggerParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.httpclient.methods.GetMethod;
import org.esir.AliMax.FuzingRestApi.model.ModelTest;
import org.esir.AliMax.FuzingRestApi.model.TestBadParam;
import org.esir.AliMax.FuzingRestApi.model.TestBadUrl;
import org.esir.AliMax.FuzingRestApi.model.TestTypeRetour;
import org.esir.AliMax.FuzingRestApi.model.VerifyCode;
import org.json.JSONObject;
import org.apache.commons.httpclient.*;

import io.swagger.models.Model;
import io.swagger.models.Path;
import io.swagger.models.Response;
import io.swagger.models.Swagger;
import io.swagger.models.properties.Property;


public class TestSwagger {

	public static void main(String[] args) {
		//System.out.println("response "+get("http://www.w3schools.com/angular/customers_mysql.php")); http://petstore.swagger.io/v2/swagger.json
		Swagger swagger = new SwaggerParser().read("swagger1.json");
		Map<String, Model> defs = swagger.getDefinitions();
		//System.out.println("********** ! Bonjour ****** "+swagger.getDefinitions().get("PriceEstimate").getProperties()+"\n"+swagger.getPaths());
		//Model m = swagger.getDefinitions().get("PriceEstimate");
//		for( Entry<String, Property> entr : m .getProperties().entrySet()){
//			System.out.println( entr.getKey()+" : "+entr.getValue().getType());
//			//Object x = 2;
//			//if(x instanceof Number)System.out.println("!!!!!!!!!!!!");
//		}
		String baseUrl = swagger.getBasePath();
		Map<String,Path> paths = swagger.getPaths();
		ModelTest test = null;
		String url ="http://localhost:8080";//"http://www.w3schools.com/angular/customers_mysql.php";
		//test VerifCode
		url+=baseUrl;
		test = new VerifyCode(paths);
		String rapport = test.generateReport(/*baseUrl*/url);
		
		//test BAD URL
		 test = new TestBadUrl(5, paths);
		 rapport+= test.generateReport(/*baseUrl*/url);
		 
		 //test badPAram
		 test = new TestBadParam(paths);
		 rapport+=test.generateReport(/*baseUrl*/url);
		 
		 //test Verif type
		 test = new TestTypeRetour(paths,defs);
		 rapport+=test.generateReport(url);
		 App.generatehtml(rapport, "rapport3.html");
		
		//test = new TestBadParam(paths);
		//System.out.println("!!!!!!!\n"+test.generateReport(/*baseUrl*/url));
//		for(Map.Entry<String, Path> entr : paths.entrySet()){
//			Path p= entr.getValue();
//			if(p.getGet()!=null && p.getGet().getResponses().size()>0 && p.getGet().getResponses().get("200")!=null){
//				String str = p.getGet().getResponses().get("200").getSchema().getType();
//				String name = p.getGet().getResponses().get("200").getSchema().getName();
//				System.out.println("TYPE RESPONSE : "+str+" ");
//				if(str.equals("ref")){
//					Property sh = p.getGet().getResponses().get("200").getSchema();
//					System.out.println("!"+sh.getAccess()+" "+sh.getDescription()+" "+sh.getFormat()
//					+" "+sh.getName()+" "+sh.getTitle()+" "+sh.getType()+" "+sh.getExample()
//					+" "+sh.hashCode());
//				}
//			}
//		}
//		for(Map.Entry<String, Model> def : swagger.getDefinitions().entrySet()){
//			System.out.println("## "+def.getKey()+", "+def.getValue().getProperties().size());
//		}
//		
//		for(Map.Entry<String, Path> entr : paths.entrySet()){
//			if(p.getGet() !=null)
//				System.out.println(" $${ \n Opérations : "+p.getOperations()+
//						", Param : "+p.getGet().getParameters()+",\n Summary :"+p.getGet().getSummary()+
//						",\n Response : "+p.getGet().getResponses().get("200").getSchema().getType()+" --> "+ 
//						"}");
//			break;
//			System.out.println(test.generateReport(baseUrl+entr.getKey()url,entr.getValue()));
// 			Path p = entr.getValue();
// 		if(p.getGet()!=null) {
// 				Map<String, Response> resKey = p.getGet().getResponses();
// 			System.out.println("$ "+resKey.containsKey("200")+" "+resKey.containsKey("404")+" "+resKey.containsKey("default")); 
// 			}
//		}

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
//		 Map<String, Double> map = new HashMap<String, Double>();
//		 map.put("val1", 12.2);
//		 String str = "{\"code\":4,\"type\":\"ok\",\"message\":\"magic!\",\"tab\":[1,2,3]}";
//		 JSONObject jsonObj = new JSONObject(str);
//		 System.out.println("=========================="+jsonObj.get("tab").getClass().getSimpleName()+" : "+jsonObj.get("tab"));
//		jsonObj = new JSONObject("{\"test\":2}");
//		System.out.println(jsonObj.names());
//		boolean x = Boolean.parseBoolean("bgg");
//		try {
//			HttpClient client = new HttpClient();
//			GetMethod method = new GetMethod("http://localhost:9090/resto/services/menus");
//			System.out.println(" res "+client.executeMethod(method)+" "+method.getResponseBodyAsString()+"\n"+map.get("val"));
//			String str2 = new String(method.getResponseBody());
//			//jsonObj = new JSONObject(str2);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 String[] tab ={"Ali","test","jc"};
		 JSONObject jsonObj = new JSONObject(tab);
		 System.out.println("JSON TYPE "+jsonObj.toString());
		 
	}
}
