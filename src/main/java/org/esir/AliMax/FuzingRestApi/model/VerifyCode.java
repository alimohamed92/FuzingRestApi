package org.esir.AliMax.FuzingRestApi.model;

import java.util.Map;


import io.swagger.models.Path;
import io.swagger.models.Response;

public class VerifyCode extends ModelTest{
	private final String CODEOK = "OK";
	private final String CODEKO = "KO";
	private final String GET ="GET";
	private final String POST ="POST";
	private final String PUT ="PUT";
	
	public VerifyCode(Map<String,Path> paths) {
		super("Verif code de retour", paths);
	}

	private String httpMethod (String url, Path p, String method) {
		String res ="";
		int statuCode = 0;
		Map<String, Response> responseCodes = null;
		String methodName ="";
		io.swagger.models.Operation operation = null;
		MyHttpResponse reponse = new MyHttpResponse();
		 if( method.equals(GET)){
			 operation = p.getGet();
			 responseCodes = p.getGet().getResponses();
			 reponse = MyHttp.get(url, p.getGet().getParameters());
			 methodName = GET;
		 }
		if(method.equals(POST)){
			operation = p.getPost();
			reponse = MyHttp.post(url, p.getPost().getParameters());
			responseCodes = p.getPost().getResponses();
			methodName = POST;
		}
		else if(method.equals(PUT)){
			operation = p.getPut();
			reponse = MyHttp.put(url, p.getPut().getParameters());
			methodName = PUT;
			responseCodes = p.getPut().getResponses();
		}
		String resTest="";
		String classColor = "";
		if( operation != null ){
			statuCode = reponse.getCode();
			String str = String.valueOf(statuCode);
			if(responseCodes !=null && responseCodes.containsKey(str)){
				resTest += CODEOK ;
				classColor = "class=\"hidden-xs bg-success\"";
			}
			else {
				resTest += CODEKO;
				classColor = "class=\"hidden-xs bg-danger\"";
			}
		}
		res += "<tr "+classColor+">";
		res+="<td>"+this.getTestName()+" </td><td> "+methodName+"</td><td>"+url+" </td><td>  "+resTest+"</td><td>"+statuCode+"</td></tr>\n";
	
		return res;
	}
	@Override
	public String generateReport(String url) {
		String res = "";
		String baseUrl = url;
		System.out.println("************* SIZE **************"+this.paths.keySet().size());
		for(Map.Entry<String, Path> entr : this.paths.entrySet()){
			Path p = entr.getValue();
			url = baseUrl + entr.getKey();
			if(p.getGet()!=null){
				res+=httpMethod(url,p,GET);
			}
			if(p.getPost() !=null){
				res+=httpMethod(url,p,POST);
			}
			if(p.getPut() !=null){
				res+=httpMethod(url,p,PUT);
			}
		}
		return res;
	}

}
