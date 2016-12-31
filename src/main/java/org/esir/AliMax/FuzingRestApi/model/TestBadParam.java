package org.esir.AliMax.FuzingRestApi.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.swagger.models.Path;
import io.swagger.models.parameters.Parameter;

public class TestBadParam extends ModelTest {

	public TestBadParam(Map<String, Path> paths) {
		super("BAD PARAM ", paths);
		// TODO Auto-generated constructor stub
	}

	private List<String> selectUrl(int nbUrl){
		List<String> res = new ArrayList<String>();
		res.addAll(this.paths.keySet());
		int fromIndex = (int) (Math.random()*(res.size()/2));
		if(nbUrl > res.size()/2){
			nbUrl = res.size()/2;
		}
		int toIndex = fromIndex+nbUrl;
		res =res.subList(fromIndex, toIndex);
		return res;
	}

	public String testGetBadParam(int nbUrl,String baseUrl){
		String res = "";
		List<String> l = this.selectUrl(nbUrl);
		for(String url : l){
			Path p = this.paths.get(url);
			if(p.getGet() != null && p.getGet().getParameters() != null && p.getGet().getParameters().size()!=0){
				int index = (int) (Math.random()*(p.getGet().getParameters().size()-1));
				Parameter param = p.getGet().getParameters().get(index );
				String badParam = param.getName()+"bad"; //param.getPattern()
				param.setName(badParam);
				List<Parameter> params = new ArrayList<Parameter>();
				params .add(param);
				url = baseUrl + url;
				int statuCode = MyHttp.get(url, params).getCode();
				//String str = new String(m.getResponseBody());
				//					 JSONObject jsonObj = new JSONObject(str);
				//					 Object o = jsonObj.get("code");
				//					 Swagger swagger = new SwaggerParser().read("swagger1.json");
				//					 Model model = swagger.getDefinitions().get("PriceEstimate");
				//					System.out.println("$$$$$ "+jsonObj+" comparType : "+ComparateurType.verifierTypeRef(model, jsonObj)+jsonObj.getClass().getSimpleName());
				String resTest = ""; 
				String classColor = "";
				if(statuCode == 404){
					resTest = "OK";
					classColor = "class=\"hidden-xs bg-success\"";
				}
				else {
					resTest = "KO";
					classColor = "class=\"hidden-xs bg-danger\"";
				}
				res += "<tr "+classColor+">";
				res+="<td>"+this.getTestName()+" </td><td> GET "+"</td><td>  "+url+" Param = "+badParam+" </td><td>  "+resTest+"</td><td>"+statuCode+"</td></tr>\n";
			}
		}
		return res;

	}

	public String testPostBadParam(int nbUrl,String baseUrl){
		String res = "";
		List<String> l = this.selectUrl(nbUrl);
		for(String url : l){
			Path p = this.paths.get(url);
			if(p.getPost() != null && p.getPost().getParameters() != null && p.getPost().getParameters().size()!=0){
				int index = (int) (Math.random()*(p.getPost().getParameters().size()-1));
				Parameter param = p.getPost().getParameters().get(index );
				String badParam = param.getName()+"bad"; //param.getPattern()
				param.setName(badParam);
				List<Parameter> params = new ArrayList<Parameter>();
				params .add(param);
				url = baseUrl + url;
				int statuCode = MyHttp.post(url, params).getCode();
				String resTest = ""; 
				String classColor = "";
				if(statuCode == 404){
					resTest = "OK";
					classColor = "class=\"hidden-xs bg-success\"";
				}
				else {
					resTest = "KO";
					classColor = "class=\"hidden-xs bg-danger\"";
				}
				res += "<tr "+classColor+">";
				res+="<td>"+this.getTestName()+" </td><td> POST "+"</td><td>  "+url+" Param = "+badParam+" </td><td>  "+resTest+"</td><td>"+statuCode+"</td></tr>\n";	}
		}
		return res;

	}

	public String testPutBadParam(int nbUrl,String baseUrl){
		String res = "";
		List<String> l = this.selectUrl(nbUrl);
		for(String url : l){
			Path p = this.paths.get(url);
			if(p.getPut() != null && p.getPut() .getParameters() != null && p.getPut().getParameters().size()!=0){
				int index = (int) (Math.random()*(p.getPut().getParameters().size()-1));
				Parameter param = p.getPut().getParameters().get(index );
				String badParam = param.getName()+"bad"; //param.getPattern()
				param.setName(badParam);
				List<Parameter> params = new ArrayList<Parameter>();
				params .add(param);
				url = baseUrl + url;
				int statuCode = MyHttp.put(url, params).getCode();
				String resTest = ""; 
				String classColor = "";
				if(statuCode == 404){
					resTest = "OK";
					classColor = "class=\"hidden-xs bg-success\"";
				}
				else {
					resTest = "KO";
					classColor = "class=\"hidden-xs bg-danger\"";
				}
				res += "<tr "+classColor+">";
				res+="<td>"+this.getTestName()+" </td><td> PUT "+"</td><td>  "+url+" Param = "+badParam+" </td><td>  "+resTest+"</td><td>"+statuCode+"</td></tr>\n";	}
		}
		return res;

	}
	@Override
	public String generateReport(String url) {
		return this.testGetBadParam(5,url) + this.testPostBadParam(5, url)+this.testPutBadParam(5, url);
	}

}
